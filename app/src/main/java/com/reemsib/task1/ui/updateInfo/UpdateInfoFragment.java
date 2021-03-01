package com.reemsib.task1.ui.updateInfo;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.reemsib.task1.BR;
import com.reemsib.task1.R;
import com.reemsib.task1.databinding.UpdateInfoFragmentBinding;
import com.reemsib.task1.room.User;
import java.util.Objects;

public class UpdateInfoFragment extends Fragment {

    private UpdateInfoViewModel mViewModel;
    private UpdateInfoFragmentBinding binding;
    private View view;
    private static final String TAG = "UpdateInfoFragment";

    public static UpdateInfoFragment newInstance() {
        return new UpdateInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.update_info_fragment, container, false);
        view = binding.getRoot();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UpdateInfoViewModel.class);
        binding.setViewModel(mViewModel);
        User user = UpdateInfoFragmentArgs.fromBundle(getArguments()).getUser();
        mViewModel.setData(user);
        binding.setVariable(BR.position, user.getId());
        Log.e(TAG, "onActivityCreated: "+user.getId()+user.getUserName()+user.getPassword() );
        mViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                Log.e(TAG, "onChanged: " + user.getUserName() + user.getEmail() + user.getPassword());

                if (TextUtils.isEmpty(Objects.requireNonNull(user).getUserName())) {
                    binding.etName.setError("Enter an User Name ");
                    binding.etName.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(user).getEmail())) {
                    binding.etEmailAddress.setError("Enter an E-Mail Address");
                    binding.etEmailAddress.requestFocus();
                } else if (!user.isEmailValid()) {
                    binding.etEmailAddress.setError("Enter a Valid E-mail Address");
                    binding.etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(user).getPassword())) {
                    binding.etPassword.setError("Enter a Password");
                    binding.etPassword.requestFocus();
                } else if (!user.isPasswordLengthGreaterThan5()) {
                    binding.etPassword.setError("Enter at least 6 Digit password");
                    binding.etPassword.requestFocus();
                } else {
                    mViewModel.updateUser(view,user);
                }
            }
        });

    }

}