package be.ehb.nootjesdbapp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.ehb.nootjesdbapp.R;
import be.ehb.nootjesdbapp.model.Nut;
import be.ehb.nootjesdbapp.view.adapter.NutAdapter;
import be.ehb.nootjesdbapp.viewmodel.NutViewModel;

public class HomeFragment extends Fragment {

    private NutViewModel nutViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView nutRecylclerView = rootView.findViewById(R.id.home_rv);
        EditText nameEditText = rootView.findViewById(R.id.home_et_name);
        Button saveButton = rootView.findViewById(R.id.home_btn_save);

        // maak een linearlayout dat verticaal u cellen aantoont
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //nutRecylclerView.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        nutRecylclerView.setLayoutManager(layoutManager);

        // Het gaat er zo uit zien en zal deze data bevatten
        NutAdapter adapter = new NutAdapter();
        nutRecylclerView.setAdapter(adapter);

        // Observe als er iets gegroeit is in de array refresh en update het.
        nutViewModel = new ViewModelProvider(getActivity()).get(NutViewModel.class);

        // Kijk hoelang fragment lifecycle is, observeer hoe lang fragment leeft. OnDestroy is dus de stop. Observer op database mee destroy voor free resource.
        nutViewModel.getNuts().observe(getViewLifecycleOwner(), new Observer<List<Nut>>() { // kan ook met lambda geschreven worden
            @Override
            public void onChanged(List<Nut> nuts) {
                adapter.addNuts(nuts);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = nameEditText.getText().toString();
                Nut newNut = new Nut();
                newNut.setName(input);
                nutViewModel.insertNut(newNut);
            }
        });

        return rootView;
    }
}
