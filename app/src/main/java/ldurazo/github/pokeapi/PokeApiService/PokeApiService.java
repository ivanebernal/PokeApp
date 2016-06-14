package ldurazo.github.pokeapi.PokeApiService;//Created by ldurazo on 6/11/16

import android.graphics.Bitmap;
import android.media.Image;

import ldurazo.github.pokeapi.Models.Pokedex;
import ldurazo.github.pokeapi.Models.Pokemon;
import ldurazo.github.pokeapi.Models.Sprite;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApiService {
    @GET("api/v1/pokemon/{id}/")
    Call<Pokemon> getPokemon(@Path("id") int pokemonId);

    @GET("{id}")
    Call<Sprite> getSprite(@Path("id") String spriteId);

    @GET("api/v1/pokedex/1/")
    Call<Pokedex> getPokedex();
}
