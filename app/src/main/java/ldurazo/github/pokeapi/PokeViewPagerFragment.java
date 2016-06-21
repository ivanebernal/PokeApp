package ldurazo.github.pokeapi;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import ldurazo.github.pokeapi.Adapters.AbilityAdapter;
import ldurazo.github.pokeapi.Adapters.MovementAdapter;
import ldurazo.github.pokeapi.Adapters.PokemonSwipeAdapter;
import ldurazo.github.pokeapi.Models.Ability;
import ldurazo.github.pokeapi.Models.Move;
import ldurazo.github.pokeapi.Models.Pokemon;
import ldurazo.github.pokeapi.Models.Type;
import ldurazo.github.pokeapi.PokeApiService.PokeApiService;
import ldurazo.github.pokeapi.Transport.PokeApiTransport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PokeViewPagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PokeViewPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokeViewPagerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static Context mContext;
    private static int mPosition;
    private MediaPlayer mCryPlayer;
    private String mPokemonNumber;
    private String mPokemonNumberCry;

    private OnFragmentInteractionListener mListener;

    public PokeViewPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Pokemon.
     * @return A new instance of fragment PokeViewPagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PokeViewPagerFragment newInstance(int position, Context context) {
        PokeViewPagerFragment fragment = new PokeViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        mContext = context;
        mPosition = position;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mCryPlayer!=null) {
            mCryPlayer.reset();
            mCryPlayer.release();
            mCryPlayer = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View pokeDetailsView = inflater.inflate(R.layout.fragment_poke_view_pager, container, false);

        final TextView nameTextView = (TextView) pokeDetailsView.findViewById(R.id.pokemon_name_detail);
        final ListView abilitiesListView = (ListView) pokeDetailsView.findViewById(R.id.abilities_list);
        final ListView movementsListView = (ListView) pokeDetailsView.findViewById(R.id.movements_list);
        final LinearLayout typesListView = (LinearLayout) pokeDetailsView.findViewById(R.id.types_view);
        final ImageView pokeSpriteView = (ImageView) pokeDetailsView.findViewById(R.id.sprite);

        mPokemonNumber = String.valueOf(mPosition);
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
                    mCryPlayer = MediaPlayer.create(getContext(), cryResId);
                    mCryPlayer.start();
                }
            }
        });

        Bitmap pokeImage = getPokeImage(mPokemonNumber + ".png");
        if(pokeImage == null){
            Picasso.with(pokeDetailsView.getContext()).load(R.drawable.no_poke_symbol).resize(240,240).into(pokeSpriteView);
        }else{
            pokeSpriteView.setImageBitmap(Bitmap.createScaledBitmap(pokeImage, 360, 360, false));
        }
        PokeApiTransport pokeApiTransport = new PokeApiTransport();
        PokeApiService pokeApiService = pokeApiTransport.getRetrofit().create(PokeApiService.class);
        Call<Pokemon> pokemonCall = pokeApiService.getPokemon((mPosition));
        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                String mPokemonName = pokemon.getName();
                List<Type> mPokemonTypes = pokemon.getTypes();
                List<Ability> mPokemonAbilities = pokemon.getAbilities();
                List<Move> mPokemonMoves = pokemon.getMoves();
                for(Type type : mPokemonTypes){
                    TextView typeTextView = new TextView(getContext());
                    typeTextView.setText(type.getName());
                    typeTextView.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                    typeTextView.setTextSize(20);
                    typeTextView.setGravity(Gravity.CENTER);
                    typesListView.addView(typeTextView);
                }
                nameTextView.setText(mPokemonName);
                Context context = getContext();
                abilitiesListView.setAdapter(new AbilityAdapter(mPokemonAbilities, context));
                movementsListView.setAdapter(new MovementAdapter(mPokemonMoves, context));
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
            }
        });
        return pokeDetailsView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
