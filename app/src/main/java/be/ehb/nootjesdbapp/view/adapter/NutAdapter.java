package be.ehb.nootjesdbapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.ehb.nootjesdbapp.R;
import be.ehb.nootjesdbapp.model.Nut;
import be.ehb.nootjesdbapp.viewmodel.NutViewModel;

public class NutAdapter extends RecyclerView.Adapter<NutAdapter.NutViewModel> {

    class NutViewModel extends RecyclerView.ViewHolder {
        TextView nameTV;

        public NutViewModel(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.cell_tv_name);
        }
    }

    private ArrayList<Nut> items;

    public NutAdapter() {
        this.items = new ArrayList<>();
    }

    // Query op databse is enkel LIST dus kan niet ARRAY
    public void addNuts(List<Nut> newitems) {
            this.items.clear();
            this.items.addAll(newitems);
            this.notifyDataSetChanged();
    }

    // Layout inflater om xml vizueel te laten tonen eerst.
    @NonNull
    @Override
    public NutViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false);

        // NutViewModel nutViewModel = new NutViewModel(cellView);
        // return nutViewModel;
        return new NutViewModel(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull NutViewModel holder, int position) {
        Nut nut = items.get(position);
        holder.nameTV.setText(nut.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
