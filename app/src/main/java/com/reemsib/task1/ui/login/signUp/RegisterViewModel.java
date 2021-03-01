package com.reemsib.task1.ui.login.signUp;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.reemsib.task1.R;
import com.reemsib.task1.room.User;
import com.reemsib.task1.room.UserDB;
import com.reemsib.task1.utilis.BaseClass;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import androidx.navigation.Navigation;

import static androidx.navigation.Navigation.*;

public class RegisterViewModel extends ViewModel {
    private static final String TAG = "RegisterViewModel";
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Boolean> status = new MutableLiveData<>();
    MutableLiveData<User> userMutableLiveData;

    public MutableLiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }

    public void registerUser(View v) {
        User user = new User(userName.getValue(), email.getValue(), password.getValue());
        userMutableLiveData.setValue(user);
    }

    public void onClickSkip(View v) {
        findNavController(v).navigate(R.id.action_registerFragment_to_viewUsersFragment);
    }

  public void insertUser(View view,User user) {
       BaseClass.getInstance().userDao().insertUser(user)
                //backgroundThread
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                //Observer
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                         status.setValue(true);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        status.setValue(false);
                        Log.e(TAG, "onError: " + e.getMessage());
                    }
                });
    }
}
