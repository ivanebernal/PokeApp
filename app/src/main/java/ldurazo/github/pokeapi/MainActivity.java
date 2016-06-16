package ldurazo.github.pokeapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ldurazo.github.pokeapi.Models.Pokedex;
import ldurazo.github.pokeapi.Models.Pokemon;
import ldurazo.github.pokeapi.Models.PokemonUri;
import ldurazo.github.pokeapi.Models.Sprite;
import ldurazo.github.pokeapi.Models.SpriteUri;
import ldurazo.github.pokeapi.PokeApiService.PokeApiService;
import ldurazo.github.pokeapi.Transport.PokeApiTransport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements PokeListFragment.OnPokemonSelected, PokemonDetailsFragment.OnFragmentInteractionListener {

    private final PokeApiTransport mPokeApiTransport = new PokeApiTransport();
    public static LruCache<Integer, Bitmap> mPokeImages;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        //TODO: Loading screen while images are being downloaded (use onProgressUpdate)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Pokedex pokedex = new Pokedex();
        final PokeApiService pokeApiService = mPokeApiTransport.getRetrofit().create(PokeApiService.class);
        Call<Pokedex> pokedexCall = pokeApiService.getPokedex();
        final CacheFiller.PokemonResourcesDownloader pokeFileDownloader = new CacheFiller.PokemonResourcesDownloader(this);
        pokedexCall.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                pokedex.setCreated(response.body().getCreated());
                pokedex.setModified(response.body().getModified());
                pokedex.setName(response.body().getName());
                pokedex.setPokemonUri(response.body().getPokemonUri());
                pokedex.setResourceUri(response.body().getResourceUri());
                Collections.sort(pokedex.getPokemonUri());
                pokeFileDownloader.execute(pokedex.getPokemonUri());
                getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_layout, PokeListFragment.newInstance(pokedex.getPokemonUri()), "pokemonList" )
                            .commit();
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                t.getCause();
            }
        });

    }
    @Override
    public void onPokemonSelected(Pokemon pokemon) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, PokemonDetailsFragment.newInstance(pokemon, this), "detailsFragment")
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
