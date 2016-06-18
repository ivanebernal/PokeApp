package ldurazo.github.pokeapi.Adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.List;

import ldurazo.github.pokeapi.Models.Ability;
import ldurazo.github.pokeapi.Models.Move;
import ldurazo.github.pokeapi.Models.Pokemon;
import ldurazo.github.pokeapi.Models.Type;
import ldurazo.github.pokeapi.PokeApiService.PokeApiService;
import ldurazo.github.pokeapi.PokemonDetailsFragment;
import ldurazo.github.pokeapi.R;
import ldurazo.github.pokeapi.Transport.PokeApiTransport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ivan on 16/06/16.
 */
public class PokemonSwipeAdapter extends PagerAdapter {
    private Context mContext;
    private MediaPlayer mCryPlayer;
    private String mPokemonNumber;
    private String mPokemonNumberCry;
    private View mView;

    public PokemonSwipeAdapter(FragmentManager fm, Context context, View view) {
        //super(fm);
        mContext = context;
        mView = view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(mCryPlayer!=null) {
            mCryPlayer.reset();
            mCryPlayer.release();
            mCryPlayer = null;
        }
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View pokeDetailsView = mView;
        final TextView nameTextView = (TextView) pokeDetailsView.findViewById(R.id.pokemon_name_detail);
        final ListView abilitiesListView = (ListView) pokeDetailsView.findViewById(R.id.abilities_list);
        final ListView movementsListView = (ListView) pokeDetailsView.findViewById(R.id.movements_list);
        final LinearLayout typesListView = (LinearLayout) pokeDetailsView.findViewById(R.id.types_view);
        final ImageView pokeSpriteView = (ImageView) pokeDetailsView.findViewById(R.id.sprite);

        mPokemonNumber = String.valueOf(position);
        if(mPokemonNumber.length() == 2) mPokemonNumberCry = "0" + mPokemonNumber;
        if(mPokemonNumber.length() == 1) mPokemonNumberCry = "00" + mPokemonNumber;
        if(mPokemonNumber.length() == 3) mPokemonNumberCry = mPokemonNumber;

        pokeSpriteView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                int cryResId = 0;
                try {
                    Field cryResField = R.raw.class.getDeclaredField("r"+ mPokemonNumberCry);
                    cryResId = cryResField.getInt(cryResField);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if(cryResId != 0){
                    mCryPlayer = MediaPlayer.create(pokeDetailsView.getContext(), cryResId);
                    mCryPlayer.start();
                }
            }
        });

        Bitmap pokeImage = getPokeImage(mPokemonNumber + ".png");
        if(pokeImage == null){
            Picasso.with(pokeDetailsView.getContext()).load(R.drawable.no_poke_symbol).resize(240,240).into(pokeSpriteView);
        }else{
            pokeSpriteView.setImageBitmap(pokeImage);
        }
        PokeApiTransport pokeApiTransport = new PokeApiTransport();
        PokeApiService pokeApiService = pokeApiTransport.getRetrofit().create(PokeApiService.class);
        Call<Pokemon> pokemonCall = pokeApiService.getPokemon(position);
        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                String mPokemonName = pokemon.getName();
                List<Type> mPokemonTypes = pokemon.getTypes();
                List<Ability> mPokemonAbilities = pokemon.getAbilities();
                List<Move> mPokemonMoves = pokemon.getMoves();
                for(Type type : mPokemonTypes){
                    TextView typeTextView = new TextView(pokeDetailsView.getContext());
                    typeTextView.setText(type.getName());
                    typeTextView.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                    typeTextView.setTextSize(20);
                    typeTextView.setGravity(Gravity.CENTER);
                    typesListView.addView(typeTextView);
                }

                nameTextView.setText(mPokemonName);
                Context context = pokeDetailsView.getContext();
                abilitiesListView.setAdapter(new AbilityAdapter(mPokemonAbilities, context));
                movementsListView.setAdapter(new MovementAdapter(mPokemonMoves, context));
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });

        return pokeDetailsView;
    }

    /*@Override
    public Fragment getItem(int position) {
        final PokemonDetailsFragment[] pokemonDetailsFragment = new PokemonDetailsFragment[1];
        PokeApiTransport pokeApiTransport = new PokeApiTransport();
        final PokeApiService pokeApiService = pokeApiTransport.getRetrofit().create(PokeApiService.class);
        Call<Pokemon> pokemonCall = pokeApiService.getPokemon(position);
        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                pokemonDetailsFragment[0] = PokemonDetailsFragment.newInstance(pokemon, mContext);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
        return pokemonDetailsFragment[0];
    }*/

    @Override
    public int getCount() {
        return 150;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    public Bitmap getPokeImage(String pokeFileName){
        ContextWrapper cw = new ContextWrapper(mContext);
        try{
            File f = new File(cw.getFilesDir(), pokeFileName);
            return BitmapFactory.decodeStream(new FileInputStream(f));
        }catch (Exception e){
            return null;
        }
    }
}
