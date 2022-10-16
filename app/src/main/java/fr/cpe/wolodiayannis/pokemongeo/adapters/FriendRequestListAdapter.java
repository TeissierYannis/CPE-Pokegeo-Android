package fr.cpe.wolodiayannis.pokemongeo.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.BasicResponse;
import fr.cpe.wolodiayannis.pokemongeo.databinding.FriendsRequestItemBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.FriendList;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.FriendFetcher;
import fr.cpe.wolodiayannis.pokemongeo.listeners.OnFriend;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.FriendViewModel;

/**
 * Adapter for the list of Pokemon.
 */
public class FriendRequestListAdapter extends RecyclerView.Adapter<FriendRequestListAdapter.ViewHolder> {

    private FriendList friendList;

    private OnFriend onFriend;

    /**
     * Constructor.
     *
     * @param friendList the friend list
     */
    public FriendRequestListAdapter() {
    }

    /**
     * Create a new ViewHolder.
     *
     * @param parent   Parent ViewGroup.
     * @param viewType ViewType.
     * @return ViewHolder.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FriendsRequestItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.friends_request_item,
                parent,
                false
        );

        return new ViewHolder(binding);
    }

    /**
     * Bind the ViewHolder with the Pokemon.
     *
     * @param holder   ViewHolder.
     * @param position Position of the Pokemon in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FriendViewModel friendViewModel = new FriendViewModel();
        friendViewModel.setFriendRequest(friendList.getFriendList().get(position));

        holder.binding.accept.setOnClickListener(v -> {
            // Executor
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                new Thread(() -> {
                    BasicResponse response = (new FriendFetcher(v.getContext())).acceptFriend(
                            friendList.getFriendList().get(position).getFriendId()
                    );
                    // run on UI thread
                    v.post(() -> {
                        if (response.getMessage().equals("success")) {
                            friendList.getFriendList().remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, friendList.getFriendList().size());
                            onFriend.onAccept();
                        } else {
                            Toast.makeText(holder.binding.getRoot().getContext(), "Friend request not accepted an error occurred", Toast.LENGTH_SHORT).show();
                        }
                        executor.shutdown();
                    });
                }).start();
            });
        });

        holder.binding.decline.setOnClickListener(v -> {
            new Thread(() -> {
                BasicResponse response = (new FriendFetcher(v.getContext())).declineFriend(
                        friendList.getFriendList().get(position).getFriendId()
                );
                // run on UI thread
                v.post(() -> {
                    if (response.getMessage().equals("success")) {
                        friendList.getFriendList().remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, friendList.getFriendList().size());
                        onFriend.onDecline();
                    } else {
                        Toast.makeText(holder.binding.getRoot().getContext(), "Friend request not declined an error occurred", Toast.LENGTH_SHORT).show();
                    }
                });

            }).start();
        });

        holder.binding.setItemViewModel(friendViewModel);
    }

    /**
     * Get the number of Pokemon in the list.
     *
     * @return Number of Pokemon.
     */
    @Override
    public int getItemCount() {
        return friendList.getFriendList().size();
    }

    public void setFriendList(FriendList friendList) {
        this.friendList = friendList;
    }


    /**
     * ViewHolder for the friend list.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private final FriendsRequestItemBinding binding;

        /**
         * Constructor.
         *
         * @param binding the binding
         */
        ViewHolder(FriendsRequestItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setOnFriend(OnFriend onFriend) {
        this.onFriend = onFriend;
    }
}



