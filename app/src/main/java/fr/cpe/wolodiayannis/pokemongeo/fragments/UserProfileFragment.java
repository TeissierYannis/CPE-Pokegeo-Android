package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.UserProfileBinding;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.UserLogoutFetcher;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.UserViewModel;
;

public class UserProfileFragment extends Fragment {

    /**
     * onCreateView.
     *
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        UserProfileBinding binding = DataBindingUtil.inflate(inflater, R.layout.user_profile, container, false);
        // Set the layout
        UserViewModel userViewModel = new UserViewModel();

        userViewModel.setUser(Datastore.getInstance().getUser());
        userViewModel.setPokedexCount(Datastore.getInstance().getCaughtInventory().getCaughtInventoryList().size() + "/" + Datastore.getInstance().getPokemons().size());

        binding.setUserViewModel(userViewModel);

        binding.chevronBack.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });

        // Get logout button without id
        binding.logoutButton.setOnClickListener(v -> {
            (new Thread( () -> {
                (new UserLogoutFetcher(requireContext())).logoutAndClearCache(Datastore.getInstance().getUser());
                requireActivity().runOnUiThread(() -> {
                    // Go to SpashScreen activity
                    requireActivity().finish();
                });
            })).start();
        });

        return binding.getRoot();
    }

}
