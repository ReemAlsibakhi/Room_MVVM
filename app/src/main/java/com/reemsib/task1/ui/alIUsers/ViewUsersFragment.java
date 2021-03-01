package com.reemsib.task1.ui.alIUsers;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.reemsib.task1.R;
import com.reemsib.task1.databinding.ViewUsersFragmentBinding;
import com.reemsib.task1.room.User;
import java.util.List;

public class ViewUsersFragment extends Fragment {

    private ViewUsersViewModel mViewModel;
    private ViewUsersFragmentBinding binding;
    private View view;
    private static final String TAG = "ViewUsersFragment";


    public static ViewUsersFragment newInstance() {
        return new ViewUsersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.view_users_fragment, container, false);
        view=binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewUsersViewModel.class);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        if (savedInstanceState == null) {
            mViewModel.init();
        }
        setupListUpdate();
    }
    private void setupListUpdate() {
        mViewModel.fetchList(view);
        mViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users.size() > 0) {
                    mViewModel.setUsersInAdapter(users);
                }

            }
        });
        setupListClick();
    }

    private void setupListClick() {
        mViewModel.getSelected().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    NavDirections action = ViewUsersFragmentDirections.actionViewUsersFragmentToDetailUserFragment(user);
                    Navigation.findNavController(view).navigate(action);
                    Log.e(TAG, "onChanged: "+user );
                    Toast.makeText(requireActivity(), "You selected a " +user.getUserName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}