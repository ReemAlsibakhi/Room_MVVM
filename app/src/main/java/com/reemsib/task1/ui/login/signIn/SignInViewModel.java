package com.reemsib.task1.ui.login.signIn;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.reemsib.task1.room.User;
import com.reemsib.task1.room.UserDB;
import com.reemsib.task1.utilis.BaseClass;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignInViewModel extends ViewModel {
    private static final String TAG = "SignInViewModel";
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> msg=new MutableLiveData<>();
    public MutableLiveData<User> userMutableLiveData;
    public MutableLiveData<Boolean> status = new MutableLiveData<>();


    public MutableLiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }
    public void loginUser(View v){
        User user=new User(email.getValue(), password.getValue());
        userMutableLiveData.setValue(user);
       }
   public void loginDB(View v,User user){
       BaseClass.getInstance().userDao().count(email.getValue())
               .subscribeOn(Schedulers.computation())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<Integer>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {
                       Log.e(TAG, "onSubscribe: "+d.toString() );
                   }

                   @Override
                   public void onNext(@NonNull Integer integer) {
                       if (integer<=0) {
                           status.setValue(false);
                           msg.setValue("Email Not Found");
                           Log.e(TAG, "onNext: email not found " );
                       }
                   }

                   @Override
                   public void onError(@NonNull Throwable e) {
                       Log.e(TAG, "onError: "+e.getMessage().toString());

                   }

                   @Override
                   public void onComplete() {
                       Log.e(TAG, "onComplete: " );
                   }
               });

       BaseClass.getInstance().userDao().getPassByEmail(email.getValue())
               .subscribeOn(Schedulers.computation())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<String>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {
                       Log.e(TAG, "onSubscribe: " );
                   }

                   @Override
                   public void onNext(@NonNull String s) {
                       if (password.getValue().equals(s)){
                           status.setValue(true);
                           Log.e(TAG, "onNext: Passwords are match" );
                       }else {
                           msg.setValue("Password is mistake");
                           status.setValue(false);
                           Log.e(TAG, "onNext: Passwords not match ");

                       }
                   }

                   @Override
                   public void onError(@NonNull Throwable e) {
                       Log.e(TAG, "onError: "+e.getMessage().toString() );
                   }

                   @Override
                   public void onComplete() {
                       Log.e(TAG, "onComplete: " );
                   }
               });
    }
    }
