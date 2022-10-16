package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import java.util.concurrent.atomic.AtomicReference;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.activities.SplashScreenActivity;
import fr.cpe.wolodiayannis.pokemongeo.adapters.FriendListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.adapters.FriendRequestListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.UserProfileBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.FriendList;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.FriendFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.UserLogoutFetcher;
import fr.cpe.wolodiayannis.pokemongeo.listeners.OnLoadFriendList;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.UserViewModel;

public class UserProfileFragment extends Fragment {

    UserProfileBinding binding;

    OnLoadFriendList onLoadFriendList = new OnLoadFriendList() {
        @Override
        public void onLoadFriendList(FriendList friendList) {
            FriendListAdapter adapter = new FriendListAdapter();
            adapter.setFriendList(friendList);
            binding.friendsList.setAdapter(adapter);
            binding.friendsCount.setText(friendList.getFriendList().size() + " friends");
            // reload the list
            binding.friendsList.getAdapter().notifyDataSetChanged();
        }
    };

    OnLoadFriendList onLoadPendingFriendList = new OnLoadFriendList() {
        @Override
        public void onLoadFriendList(FriendList friendList) {
            FriendRequestListAdapter adapter = new FriendRequestListAdapter();
            adapter.setFriendList(friendList);
            binding.friendsRequest.setAdapter(adapter);
            binding.friendsRequestCount.setText(friendList.getFriendList().size() + " friends requests");
            // reload the list
            binding.friendsRequest.getAdapter().notifyDataSetChanged();
        }
    };

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
        this.binding = DataBindingUtil.inflate(inflater, R.layout.user_profile, container, false);
        // Set the layout
        UserViewModel userViewModel = new UserViewModel();

        // Set mock adapter
        FriendListAdapter mockAdapter = new FriendListAdapter();
        mockAdapter.setFriendList(new FriendList());
        binding.friendsCount.setText("loading...");

        this.updateFriendListAdapter();

        userViewModel.setUser(Datastore.getInstance().getUser());
        userViewModel.setPokedexCount(Datastore.getInstance().getCaughtInventory().getCaughtInventoryList().size() + "/" + (Datastore.getInstance().getPokemons().size() - 1));

        this.binding.setUserViewModel(userViewModel);
        this.binding.progressBarLevel.setMax(100);
        this.binding.progressBarLevel.setProgress(userViewModel.getUserExperience());

        this.binding.chevronBack.setOnClickListener(v -> requireActivity().onBackPressed());

        // Get logout button without id
        this.binding.logoutButton.setOnClickListener(v -> {

            this.binding.logoutButton.setEnabled(false);

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

        this.binding.addFriendButton.setOnClickListener(v -> {
            // Create AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Add a friend");
            builder.setCancelable(true);
            View view = inflater.inflate(R.layout.add_friend_popup, container, false);

            view.findViewById(R.id.add_friend_button).setOnClickListener(v1 -> {
                // verify if the field is not empty
                view.findViewById(R.id.add_friend_button).setEnabled(false);
                String friendName = ((android.widget.EditText) view.findViewById(R.id.pseudo_add_friend)).getText().toString();

                if (friendName.isEmpty()) {
                    view.findViewById(R.id.add_friend_button).setEnabled(true);
                    Toast.makeText(requireContext(), "Please enter a friend name", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Add friend with the request
                (new Thread(() -> {
                    BasicResponse response = (new FriendFetcher(requireContext())).addFriend(friendName);

                    requireActivity().runOnUiThread(() -> {
                        // Close keyboard
                        ((android.view.inputmethod.InputMethodManager) requireContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);

                        if (response == null) {
                            Toast.makeText(requireContext(), "Friend not found", Toast.LENGTH_SHORT).show();
                        } else if (response.getMessage().equals("success")) {
                            Toast.makeText(requireContext(), "Friend request sent", Toast.LENGTH_SHORT).show();
                            builder.create().dismiss();
                        } else {
                            Toast.makeText(requireContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        view.findViewById(R.id.add_friend_button).setEnabled(true);
                        // close the popup
                        view.findViewById(R.id.backArrow_add_friend).performClick();
                    });

                })).start();
            });

            builder.setView(view);
            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            view.findViewById(R.id.backArrow_add_friend).setOnClickListener(v1 -> {
                dialog.dismiss();
            });
            dialog.show();
        });

        this.binding.friendRequestButton.setOnClickListener(v -> {

            binding.friendsRequestCount.setText("loading...");

            FriendRequestListAdapter adapter = new FriendRequestListAdapter();
            adapter.setFriendList(new FriendList());
            this.binding.friendsRequest.setAdapter(adapter);

            this.binding.friendRequestContainer.setVisibility(View.VISIBLE);
            this.binding.friendsRequest.setVisibility(View.VISIBLE);
            this.binding.friendListContainer.setVisibility(View.GONE);
            this.binding.friendsList.setVisibility(View.GONE);

            this.updatePendingFriendListAdapter();
        });

        this.binding.friendListButton.setOnClickListener(v -> {

            binding.friendsCount.setText("loading...");

            FriendListAdapter adapter = new FriendListAdapter();
            adapter.setFriendList(new FriendList());
            this.binding.friendsList.setAdapter(adapter);

            this.binding.friendRequestContainer.setVisibility(View.GONE);
            this.binding.friendsRequest.setVisibility(View.GONE);
            this.binding.friendListContainer.setVisibility(View.VISIBLE);
            this.binding.friendsList.setVisibility(View.VISIBLE);

            this.updateFriendListAdapter();
        });

        return this.binding.getRoot();
    }

    public FriendList loadFriendList() {
        FriendList friendList = (new FriendFetcher(requireContext())).fetchFriendList();
        if (friendList == null) {
            friendList = new FriendList();
        }
        return friendList;
    }

    public FriendList loadPendingFriendList() {
        FriendList pendingFriendList = (new FriendFetcher(requireContext())).fetchPendingFriendList();
        if (pendingFriendList == null) {
            pendingFriendList = new FriendList();
        }
        return pendingFriendList;
    }

    public void updateFriendListAdapter() {
        new Thread(() -> {
            AtomicReference<FriendList> friendList = new AtomicReference<>(this.loadFriendList());
            // run in ui
            requireActivity().runOnUiThread(() -> {
                onLoadFriendList.onLoadFriendList(friendList.get());
            });

        }).start();
    }
    public void updatePendingFriendListAdapter() {
        new Thread(() -> {
            AtomicReference<FriendList> friendList = new AtomicReference<>(this.loadPendingFriendList());
            // run in ui
            requireActivity().runOnUiThread(() -> {
                onLoadPendingFriendList.onLoadFriendList(friendList.get());
            });

        }).start();
    }

}

