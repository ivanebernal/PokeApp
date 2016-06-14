package ldurazo.github.pokeapi.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ldurazo.github.pokeapi.R;

/**
 * Created by Iván on 12/06/2016.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextViewName;
    private ImageView mImageView;

    ViewHolder(View itemView){
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.grid_poke_image);
        mTextViewName = (TextView)itemView.findViewById(R.id.pokemon_name);
    }

    public void setData(String pokemonName, String sprite){
        mTextViewName.setText(pokemonName);
        if(sprite != null) Picasso.with(itemView.getContext()).load("https://pokeapi.co/" + sprite).into(mImageView);
    }
}
