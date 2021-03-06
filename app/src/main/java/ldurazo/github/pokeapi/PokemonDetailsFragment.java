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
import android.support.annotation.RawRes;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import ldurazo.github.pokeapi.Adapters.AbilityAdapter;
import ldurazo.github.pokeapi.Adapters.MovementAdapter;
import ldurazo.github.pokeapi.Models.Ability;
import ldurazo.github.pokeapi.Models.Move;
import ldurazo.github.pokeapi.Models.Pokemon;
import ldurazo.github.pokeapi.Models.Type;
import ldurazo.github.pokeapi.PokeApiService.PokeApiService;
import ldurazo.github.pokeapi.Transport.PokeApiTransport;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PokemonDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PokemonDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static String mPokemonName;
    private static List<Ability> mPokemonAbilities;
    private static List<Type> mPokemonTypes;
    private static List<Move> mPokemonMoves;
    private static Context mContext;
    private static String mPokemonNumber;
    private static MediaPlayer mCryPlayer;
    private static String mPokemonNumberCry;

    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    public PokemonDetailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PokemonDetailsFragment newInstance(Pokemon pokemon, Context context) {
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        mPokemonName = pokemon.getName();
        mPokemonAbilities = pokemon.getAbilities();
        mPokemonTypes = pokemon.getTypes();
        mPokemonMoves = pokemon.getMoves();
        mContext = context;
        mPokemonNumber = pokemon.getNationalId().toString();
        if(mPokemonNumber.length() == 2) mPokemonNumberCry = "0" + mPokemonNumber;
        if(mPokemonNumber.length() == 1) mPokemonNumberCry = "00" + mPokemonNumber;
        if(mPokemonNumber.length() == 3) mPokemonNumberCry = mPokemonNumber;

        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pokemon_details, container, false);
        TextView nameTextView = (TextView) view.findViewById(R.id.pokemon_name_detail);
        ListView abilitiesListView = (ListView) view.findViewById(R.id.abilities_list);
        ListView movementsListView = (ListView) view.findViewById(R.id.movements_list);
        LinearLayout typesListView = (LinearLayout) view.findViewById(R.id.types_view);
        ImageView pokeSpriteView = (ImageView) view.findViewById(R.id.sprite);
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
                    mCryPlayer = MediaPlayer.create(view.getContext(), cryResId);
                    mCryPlayer.start();
                }
            }
        });
        for(Type type : mPokemonTypes){
            TextView typeTextView = new TextView(view.getContext());
            typeTextView.setText(type.getName());
            typeTextView.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            typeTextView.setTextSize(20);
            typeTextView.setGravity(Gravity.CENTER);
            typesListView.addView(typeTextView);
        }
        Bitmap pokeImage = getPokeImage(mPokemonNumber + ".png");
        if(pokeImage == null){
            Picasso.with(view.getContext()).load(R.drawable.no_poke_symbol).resize(240,240).into(pokeSpriteView);
        }else{
            pokeSpriteView.setImageBitmap(pokeImage);
        }

        nameTextView.setText(mPokemonName);
        Context context = view.getContext();
        abilitiesListView.setAdapter(new AbilityAdapter(mPokemonAbilities, context));
        movementsListView.setAdapter(new MovementAdapter(mPokemonMoves, context));
        return view;
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
        if(mCryPlayer!=null) {
            mCryPlayer.reset();
            mCryPlayer.release();
            mCryPlayer = null;
        }
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
