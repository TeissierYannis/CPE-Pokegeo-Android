package fr.cpe.wolodiayannis.pokemongeo.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.databinding.FriendsListItemBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.FriendList;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.FriendViewModel;

/**
 * Adapter for the list of Pokemon.
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {

    private FriendList friendList;

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
        FriendsListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.friends_list_item,
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

        holder.binding.setItemViewModel(friendViewModel);
    }

    /**
     * Get the number of Pokemon in the list.
     *
     * @return Number of Pokemon.
     */
    @Override
    public int getItemCount() {
        return this.friendList.getFriendList().size();
    }


    /**
     * ViewHolder for the friend list.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private final FriendsListItemBinding binding;

        /**
         * Constructor.
         *
         * @param binding the binding
         */
        ViewHolder(FriendsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    /**
     * Set friend list.
     */
    public void setFriendList(FriendList friendList) {
        this.friendList = friendList;
    }
}



