package com.reemsib.task1.ui.login.signIn;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.reemsib.task1.R;
import com.reemsib.task1.databinding.SignInFragmentBinding;
import com.reemsib.task1.room.User;

import java.util.Objects;

public class SignInFragment extends Fragment {
    private static final String TAG = "SignInFragment";
    private SignInViewModel mViewModel;
    private SignInFragmentBinding binding;
    View view;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.sign_in_fragment, container, false);
        view = binding.getRoot();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        binding.setViewModelLogin(mViewModel);
        binding.setLifecycleOwner(this);

        mViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                Log.e(TAG, "onChanged: "+user.getEmail()+user.getPassword() );

                if (TextUtils.isEmpty(Objects.requireNonNull(user).getEmail())) {
                    binding.etEmailAddress.setError("Enter an E-Mail Address");
                    binding.etEmailAddress.requestFocus();
                }
                else if (!user.isEmailValid()) {
                    binding.etEmailAddress.setError("Enter a Valid E-mail Address");
                    binding.etEmailAddress.requestFocus();
                }
                else if (TextUtils.isEmpty(Objects.requireNonNull(user).getPassword())) {
                    binding.etPassword.setError("Enter a Password");
                    binding.etPassword.requestFocus();
                }
                else if (!user.isPasswordLengthGreaterThan5()) {
                    binding.etPassword.setError("Enter at least 6 Digit password");
                    binding.etPassword.requestFocus();
                }
                else {
                    mViewModel.loginDB(view,user);
                }
            }
        });
        mViewModel.status.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean status) {
                if (!status){
                    Toast.makeText(requireActivity(),"This Email not exist or password is mistake",Toast.LENGTH_LONG).show();
                }else {
                    Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_viewUsersFragment);
                }
            }
        });
    }

}