package ldurazo.github.pokeapi.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import ldurazo.github.pokeapi.Models.Move;
import ldurazo.github.pokeapi.R;

/**
 * Created by Iván on 12/06/2016.
 */
public class MovementAdapter implements ListAdapter {
    private static List<Move> mMovements;
    private static Context mContext;

    public MovementAdapter(List<Move> movements, Context context){
        mMovements = movements;
        mContext = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mMovements.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View rowView = inflater.inflate(R.layout.movement_row, parent, false);
        Move move = mMovements.get(position);
        TextView movementName = (TextView) rowView.findViewById(R.id.movement_name);
        TextView movementType = (TextView) rowView.findViewById(R.id.movement_type);
        String name = move.getName();
        movementName.setText(name);
        String learnType = move.getLearnType();
        movementType.setText(learnType);
        return rowView;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.movement_row;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return mMovements.isEmpty();
    }
}
