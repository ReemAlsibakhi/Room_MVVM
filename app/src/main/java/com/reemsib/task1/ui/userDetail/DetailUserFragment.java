package com.reemsib.task1.ui.userDetail;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.reemsib.task1.R;
import com.reemsib.task1.databinding.DetailUserFragmentBinding;
import com.reemsib.task1.room.User;

public class DetailUserFragment extends Fragment {

    private DetailUserViewModel mViewModel;
    private DetailUserFragmentBinding binding;
    User user;
    View view;
    public static DetailUserFragment newInstance() {
        return new DetailUserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.detail_user_fragment, container, false);
        view=binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailUserViewModel.class);
        binding.setViewModel(mViewModel);
        user = DetailUserFragmentArgs.fromBundle(getArguments()).getUserDetail();
        mViewModel.setData(user);

    }

}