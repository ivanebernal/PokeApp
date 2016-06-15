package ldurazo.github.pokeapi.AsyncTasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;

import java.util.List;

import ldurazo.github.pokeapi.Models.Pokedex;
import ldurazo.github.pokeapi.Models.Pokemon;
import ldurazo.github.pokeapi.Models.Sprite;
import ldurazo.github.pokeapi.Models.SpriteUri;
import ldurazo.github.pokeapi.PokeApiService.PokeApiService;
import ldurazo.github.pokeapi.Transport.PokeApiTransport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ivan on 14/06/16.
 */
public class CacheAsyncTask extends AsyncTask <Pokedex, Void, LruCache<String, Bitmap>> {
    public static LruCache mCache;
    public static Pokedex mPokedex;

    public CacheAsyncTask(Pokedex pokedex, LruCache cache){
        mCache = cache;
        mPokedex = pokedex;
    }

    @Override
    protected LruCache<String, Bitmap> doInBackground(Pokedex... pokedex) {
        /*PokeApiTransport mPokeApiTransport = new PokeApiTransport();
        final Retrofit retrofit = mPokeApiTransport.getRetrofit();
        final PokeApiService pokeApiService = retrofit.create(PokeApiService.class);

        final Call<Pokemon> pokeCall = pokeApiService.getPokemon(pokeNum);
        pokeCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                final Pokemon pokemon = response.body();
                List<SpriteUri> spriteUris = pokemon.getSpriteUris();
                SpriteUri lastSpriteUri;
                if (spriteUris.size() != 0) {
                    lastSpriteUri = spriteUris.get(spriteUris.size() - 1);
                    Call<Sprite> pokeCallSprite = pokeApiService.getSprite(lastSpriteUri.getResourceUri().substring(1));
                    pokeCallSprite.enqueue(new Callback<Sprite>() {
                        @Override
                        public void onResponse(Call<Sprite> call, Response<Sprite> response) {
                            String sprite = response.body().getImage();
                        }

                        @Override
                        public void onFailure(Call<Sprite> call, Throwable t) {
                            t.getCause();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
*/
        return null;
    }
}
