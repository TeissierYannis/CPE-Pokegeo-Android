package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import fr.cpe.wolodiayannis.pokemongeo.databinding.FightFragmentBinding;

import fr.cpe.wolodiayannis.pokemongeo.R;

public class FightFragment extends Fragment {

    private FightFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.binding = DataBindingUtil.inflate(inflater, R.layout.fight_popup, container, false);

        return binding.getRoot();
    }
}
