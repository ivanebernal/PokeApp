package ldurazo.github.pokeapi.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import ldurazo.github.pokeapi.Models.Pokedex;
import ldurazo.github.pokeapi.Models.Pokemon;
import ldurazo.github.pokeapi.Models.PokemonUri;
import ldurazo.github.pokeapi.Models.Sprite;
import ldurazo.github.pokeapi.Models.SpriteUri;
import ldurazo.github.pokeapi.PokeApiService.PokeApiService;
import ldurazo.github.pokeapi.PokeListFragment;
import ldurazo.github.pokeapi.R;
import ldurazo.github.pokeapi.Transport.PokeApiTransport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Iván on 11/06/2016.
 */
public class PokemonAdapter extends RecyclerView.Adapter<ViewHolder>{
    private final PokeApiTransport mPokeApiTransport = new PokeApiTransport();
    final Retrofit retrofit = mPokeApiTransport.getRetrofit();
    final PokeApiService pokeApiService = retrofit.create(PokeApiService.class);
    private final PokeListFragment.OnPokemonSelected mListener;
    private List<PokemonUri> mPokemonList;
    private Context mContext;

    public PokemonAdapter(Context context, List<PokemonUri> pokemonUri){
        mPokemonList = pokemonUri;
        mContext = context;
        mListener = (PokeListFragment.OnPokemonSelected) context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new ViewHolder(inflater.inflate(R.layout.row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int pokeNum = holder.getAdapterPosition()+1;
        final Call<Pokemon> pokeCall = pokeApiService.getPokemon(pokeNum);
        pokeCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                final Pokemon pokemon = response.body();
                            holder.setData(pokemon.getName(), response.body().getNationalId(), mContext);
                        }
            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                t.getCause();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPokemonClicked(pokeNum);
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return R.layout.row_layout;
    }

    public void onPokemonClicked(int pokeNum){
        final Call<Pokemon> pokeCall = pokeApiService.getPokemon(pokeNum);
        pokeCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                final Pokemon pokemon = response.body();
                            mListener.onPokemonSelected(pokemon);
            }
            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                t.getCause();
            }
        });
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.setData("", 0, mContext);
    }
}
