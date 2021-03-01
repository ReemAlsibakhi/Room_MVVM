package com.reemsib.task1.model;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.reemsib.task1.room.User;
import com.reemsib.task1.room.UserDB;
import com.reemsib.task1.ui.alIUsers.ViewUsersFragmentDirections;
import com.reemsib.task1.utilis.BaseClass;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserObservable extends BaseObservable {

    private String status;
    private List<User> usersList = new ArrayList<>();
    private MutableLiveData<List<User>> users = new MutableLiveData<>();
    private static final String TAG = "UserObservable";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addUser(User bd) {
        usersList.add(bd);
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public void fetchList(View v) {
        BaseClass.getInstance().userDao().getAllUser().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull List<User> user) {
//                        setUsersInAdapter(user);
                        users.setValue(user);
                        usersList = user;
                        Log.e(TAG, "onNext: list user ");
                        for (int i=0; i<user.size();i++){
                            Log.e(TAG, "onNext: "+user.get(i).getId());
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void deleteUser(View v, Integer position) {
        User user = usersList.get(position);
        BaseClass.getInstance().userDao().deleteUser(user).subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: "+d.toString() );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " );
                    }
                });
    }
    public void passData(View v, Integer position) {
        User user = usersList.get(position);
        NavDirections action = ViewUsersFragmentDirections.actionViewUsersFragmentToUpdateInfoFragment(user);
        Navigation.findNavController(v).navigate(action);
    }

}
