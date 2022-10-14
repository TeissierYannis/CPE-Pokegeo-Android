package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.activities.SplashScreenActivity;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.UserProfileBinding;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.UserLogoutFetcher;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.UserViewModel;

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
        userViewModel.setPokedexCount(Datastore.getInstance().getCaughtInventory().getCaughtInventoryList().size() + "/" + (Datastore.getInstance().getPokemons().size() - 1));

        binding.setUserViewModel(userViewModel);
        binding.progressBarLevel.setMax(100);
        binding.progressBarLevel.setProgress(userViewModel.getUserExperience());

        binding.chevronBack.setOnClickListener(v -> requireActivity().onBackPressed());

        // Get logout button without id
        binding.logoutButton.setOnClickListener(v -> {

            binding.logoutButton.setEnabled(false);

            (new Thread(() -> {
                // display a spinner to wait
                requireActivity().runOnUiThread(() -> {
                    if (container != null) {
                        View loader = inflater.inflate(R.layout.loader_center, container, false);
                        loader.setVisibility(View.GONE);
                        container.addView(loader);
                        loader.setVisibility(View.VISIBLE);
                    }
                });
            })).start();

            (new Thread(() -> {
                (new UserLogoutFetcher(requireContext())).logoutAndClearCache(Datastore.getInstance().getUser());
                requireActivity().runOnUiThread(() -> {
                    // Open SpashScreen activity
                    Intent intent;
                    intent = new Intent(requireContext(), SplashScreenActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                });
            })).start();
        });
        return binding.getRoot();
    }
}

