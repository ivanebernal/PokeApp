package ldurazo.github.pokeapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Pokedex pokedex = new Pokedex();
        final PokeApiService pokeApiService = mPokeApiTransport.getRetrofit().create(PokeApiService.class);
        Call<Pokedex> pokedexCall = pokeApiService.getPokedex();
        pokedexCall.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                pokedex.setCreated(response.body().getCreated());
                pokedex.setModified(response.body().getModified());
                pokedex.setName(response.body().getName());
                pokedex.setPokemonUri(response.body().getPokemonUri());
                pokedex.setResourceUri(response.body().getResourceUri());
                Collections.sort(pokedex.getPokemonUri());
                getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_layout, PokeListFragment.newInstance(pokedex.getPokemonUri()), "pokemonList" )
                            .commit();
                /*new AsyncTask<LruCache<Integer, Bitmap>, Void, Void>() {
                    @Override
                    protected Void doInBackground(LruCache<Integer, Bitmap>... params) {
                        for(PokemonUri pokeUri : pokedex.getPokemonUri()){
                            populatePokeImages(pokeUri, mPokeImages);
                        }
                        return null;
                    }
                }.execute();*/
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                t.getCause();
            }
        });

    }

    private void populatePokeImages(PokemonUri pokeUri, LruCache pokeImages) {
        final PokeApiService pokeApiService = mPokeApiTransport.getRetrofit().create(PokeApiService.class);
        final int pokeNum = pokeUri.getPokemonNum(pokeUri);
        Call<Pokemon> pokemonCall = pokeApiService.getPokemon(pokeNum);
        final Context context = this;
        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                final Pokemon pokemon = response.body();
                List<SpriteUri> spriteUris = pokemon.getSpriteUris();
                SpriteUri lastSpriteUri;
                if(spriteUris.size() != 0) {
                    lastSpriteUri = spriteUris.get(spriteUris.size() - 1);
                    Call<Sprite> pokeCallSprite = pokeApiService.getSprite(lastSpriteUri.getResourceUri().substring(1));
                    pokeCallSprite.enqueue(new Callback<Sprite>() {
                        @Override
                        public void onResponse(Call<Sprite> call, Response<Sprite> response) {
                            Sprite sprite = response.body();
                            String spriteUri = sprite.getImage();
                            try {
                                Bitmap pokemonImage = Picasso.with(context).load("https://pokeapi.co" + spriteUri).get();
                                mPokeImages.put(pokeNum, pokemonImage);
                            } catch (IOException ignored) {

                            }

                        }

                        @Override
                        public void onFailure(Call<Sprite> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }

    @Override
    public void onPokemonSelected(Pokemon pokemon, String pokeSprite) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, PokemonDetailsFragment.newInstance(pokemon, pokeSprite), "detailsFragment")
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
