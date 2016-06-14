package ldurazo.github.pokeapi.Transport;//Created by ldurazo on 6/11/16

import android.graphics.Bitmap;

import java.util.List;

import ldurazo.github.pokeapi.Models.Pokedex;
import ldurazo.github.pokeapi.Models.PokemonUri;
import ldurazo.github.pokeapi.PokeApiService.PokeApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeApiTransport {
    public static final String BASE_URL = "https://pokeapi.co/";

    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Pokedex getPokedex(){
        final Pokedex pokedex = new Pokedex();
        final PokeApiService pokeApiService = getRetrofit().create(PokeApiService.class);
        Call<Pokedex> pokedexCall = pokeApiService.getPokedex();
        pokedexCall.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                pokedex.setCreated(response.body().getCreated());
                pokedex.setModified(response.body().getModified());
                pokedex.setName(response.body().getName());
                pokedex.setPokemonUri(response.body().getPokemonUri());
                pokedex.setResourceUri(response.body().getResourceUri());
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                t.getCause();
            }
        });
        return pokedex;
    }

    public List<PokemonUri> getPokemonUri(){
        return getPokedex().getPokemonUri();
    }
}
