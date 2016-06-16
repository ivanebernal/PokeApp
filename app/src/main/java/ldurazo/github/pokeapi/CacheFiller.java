package ldurazo.github.pokeapi;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import ldurazo.github.pokeapi.Models.Pokedex;
import ldurazo.github.pokeapi.Models.Pokemon;
import ldurazo.github.pokeapi.Models.PokemonUri;

/**
 * Created by ivan on 15/06/16.
 */
public class CacheFiller {

    public static class PokemonResourcesDownloader extends AsyncTask<List<PokemonUri>, Integer, Void>{

        private Context mContext;

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(mContext, "Files saved", Toast.LENGTH_SHORT).show();
        }

        public PokemonResourcesDownloader(Context context){
            mContext = context;
        }

        @Override
        protected Void doInBackground(List<PokemonUri>... pokeUri) {
        for(int i = 0; i < 151; i++) {
            try {
                URL url = new URL("http://pokeapi.co/media/sprites/pokemon/" + (i+1) + ".png");
                Bitmap pokeImage = getBitmap(url);
                saveToInternalStorage(pokeImage, mContext, ((i+1)+".png"));
                Log.d("File", "File saved");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return null;
        }

        protected Bitmap getBitmap(URL url) throws IOException {
            return Bitmap.createScaledBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()), 480, 480, true);
        }

        protected String saveToInternalStorage(Bitmap pokeImage, Context context, String name) throws IOException {
            ContextWrapper cw = new ContextWrapper(context);
            File directory = cw.getFilesDir();
            File path = new File(directory, name);

            FileOutputStream fos = null;

            try{
                fos = cw.openFileOutput(path.getName(), Context.MODE_PRIVATE);
                pokeImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                fos.close();
            }
            return directory.getAbsolutePath();
        }
    }
}
