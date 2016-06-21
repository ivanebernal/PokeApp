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
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    private static Context mContext;
    private OnFragmentInteractionListener mListener;
    private static int mPokeNum;

    public PokemonDetailsFragment() {
        // Required empty public constructor
    }

    public static PokemonDetailsFragment newInstance(Context context, int pokeNum) {
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        mContext = context;
        mPokeNum = pokeNum;
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pokemon_details, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pokemon_viewpager);
        viewPager.setAdapter(new PokemonSwipeAdapter(getChildFragmentManager(),mContext, mPokeNum));
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
}
