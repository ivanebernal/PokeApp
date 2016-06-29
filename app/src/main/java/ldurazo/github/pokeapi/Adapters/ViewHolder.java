package ldurazo.github.pokeapi.Adapters;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;

import ldurazo.github.pokeapi.MainActivity;
import ldurazo.github.pokeapi.PokemonDetailsFragment;
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

    public void setData(String pokemonName, int nationalId, Context context){
        mTextViewName.setText(pokemonName);
        String pokeFileName = nationalId + ".png";
        if(nationalId!= 0) {
            Bitmap pokeBitmap = getPokeImage(pokeFileName, context);
            mImageView.setImageBitmap(pokeBitmap);
        }else{
            mImageView.setImageResource(R.drawable.no_poke_symbol);
        }
        //TODO: load images from the internet if they don't exist in the files directory
    }

    public Bitmap getPokeImage(String pokeFileName, Context context){
        ContextWrapper cw = new ContextWrapper(context);
        try{
            File f = new File(cw.getFilesDir(), pokeFileName);
            return BitmapFactory.decodeStream(new FileInputStream(f));
        }catch (Exception e){
            return null;
        }
    }
}
