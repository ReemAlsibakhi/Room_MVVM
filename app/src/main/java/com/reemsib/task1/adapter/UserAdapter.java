package com.reemsib.task1.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.reemsib.task1.BR;
import com.reemsib.task1.room.User;
import com.reemsib.task1.ui.alIUsers.ViewUsersViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.GenericViewHolder> {
    private int layoutId;
    private List<User> userList;
    private ViewUsersViewModel viewModel;
    private ViewGroup parent;
    private int viewType;

    public UserAdapter(@LayoutRes int layoutId, ViewUsersViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    @NotNull
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setUsers(List<User> usersList) {
        this.userList = usersList;

    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ViewUsersViewModel viewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
//    private List<User> usersList = new ArrayList<>();
//    Context context;
//    private static final String TAG = "UserAdapter";
//    ViewUsersViewModel viewUsersViewModel;
//
//    public UserAdapter() {
//    }
//
//    public UserAdapter(List<User> usersList, Context context, ViewUsersViewModel viewUsersViewModel) {
//        this.usersList = usersList;
//        this.context = context;
//        this.viewUsersViewModel = viewUsersViewModel;
//    }
//
//    @NonNull
//    @Override
//    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
//        holder.tvUserName.setText(usersList.get(position).getUserName());
//        holder.tvEmail.setText(usersList.get(position).getEmail());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              //  new ViewUsersViewModel().onItemClick(position);
//                Log.e(TAG, "onClick: "+usersList.get(position).getUserName());
//
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return usersList.size();
//    }
//
//    public void setList(List<User> usersList) {
//        this.usersList = usersList;
//        notifyDataSetChanged();
//    }
//
//    public static class viewHolder extends RecyclerView.ViewHolder {
//
//        TextView tvUserName, tvEmail;
//        CardView cardView;
//        public viewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvUserName = itemView.findViewById(R.id.tvUserName);
//            tvEmail = itemView.findViewById(R.id.tvEmail);
//            cardView = itemView.findViewById(R.id.cardView);
//
//
//        }
//    }
}
