package ldurazo.github.pokeapi.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import ldurazo.github.pokeapi.Models.Ability;
import ldurazo.github.pokeapi.R;

/**
 * Created by Iván on 12/06/2016.
 */
public class AbilityAdapter implements ListAdapter {

    private List<Ability> mAbilities;
    private Context mContext;

    public AbilityAdapter(List<Ability> abilityList, Context context){
        mAbilities = abilityList;
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
        return mAbilities.size();
    }

    @Override
    public Object getItem(int position) {
        return mAbilities.get(position);
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
        View rowView = inflater.inflate(R.layout.ability_row, parent, false);
        TextView abilityRow = (TextView) rowView.findViewById(R.id.ability_row);
        abilityRow.setText(mAbilities.get(position).getName());
        return rowView;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.ability_row;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return mAbilities.isEmpty();
    }
}
