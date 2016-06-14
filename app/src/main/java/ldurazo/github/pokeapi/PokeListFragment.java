package ldurazo.github.pokeapi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ldurazo.github.pokeapi.Adapters.PokemonAdapter;
import ldurazo.github.pokeapi.Models.Pokemon;
import ldurazo.github.pokeapi.Models.PokemonUri;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnPokemonSelected} interface
 * to handle interaction events.
 * Use the {@link PokeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokeListFragment extends android.support.v4.app.Fragment {
    private static List<PokemonUri> mPokemonUris;
    // TODO: Rename parameter arguments, choose names that match

    private OnPokemonSelected mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PokeListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PokeListFragment newInstance(List<PokemonUri> pokemonUris) {
        PokeListFragment fragment = new PokeListFragment();
        mPokemonUris = pokemonUris;
        //Bundle args = new Bundle();
        //fragment.setArguments(args);
        return fragment;
    }

    public PokeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poke_list, container, false);
        Activity activity = (Activity) view.getContext();
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.list_view);
        listView.setAdapter(new PokemonAdapter(activity , mPokemonUris));
        listView.setLayoutManager(new GridLayoutManager(activity, 3));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnPokemonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnPokemonSelected");
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
    public interface OnPokemonSelected {
        // TODO: Update argument type and name
        void onPokemonSelected(Pokemon pokemon, String pokeSprite);
    }

}
