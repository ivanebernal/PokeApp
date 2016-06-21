package ldurazo.github.pokeapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ldurazo.github.pokeapi.Adapters.PokemonSwipeAdapter;
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

public class MainActivity extends AppCompatActivity implements PokeListFragment.OnPokemonSelected, PokemonDetailsFragment.OnFragmentInteractionListener, PokeViewPagerFragment.OnFragmentInteractionListener {

    private final PokeApiTransport mPokeApiTransport = new PokeApiTransport();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar spinner = (ProgressBar) findViewById(R.id.spinner);
        final PokeApiService pokeApiService = mPokeApiTransport.getRetrofit().create(PokeApiService.class);
        Call<Pokedex> pokedexCall = pokeApiService.getPokedex();
        final CacheFiller.PokemonResourcesDownloader pokeFileDownloader = new CacheFiller.PokemonResourcesDownloader(this);
        pokedexCall.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                Pokedex pokedex = new Pokedex();
                pokedex.setCreated(response.body().getCreated());
                pokedex.setModified(response.body().getModified());
                pokedex.setName(response.body().getName());
                pokedex.setPokemonUri(response.body().getPokemonUri());
                pokedex.setResourceUri(response.body().getResourceUri());
                Collections.sort(pokedex.getPokemonUri());
                pokeFileDownloader.execute(pokedex.getPokemonUri());
                spinner.setVisibility(View.GONE);
                getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_layout, PokeListFragment.newInstance(pokedex), "pokemonList" )
                            .commitAllowingStateLoss();
            }
            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                t.getCause();
            }
        });
    }

    @Override
    public void onPokemonSelected(int pokeNum) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, PokemonDetailsFragment.newInstance(this, pokeNum), "detailsFragment")
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
