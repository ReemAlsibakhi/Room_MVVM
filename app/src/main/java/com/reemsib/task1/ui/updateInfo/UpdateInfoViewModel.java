package com.reemsib.task1.ui.updateInfo;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import com.reemsib.task1.R;
import com.reemsib.task1.room.User;
import com.reemsib.task1.room.UserDB;
import com.reemsib.task1.utilis.BaseClass;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import static androidx.navigation.Navigation.findNavController;


public class UpdateInfoViewModel extends ViewModel {
    private static final String TAG = "UpdateInfoViewModel";
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<User> userMutableLiveData;

    public MutableLiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }

    public void setData(User user) {
        userName.setValue(user.getUserName());
        email.setValue(user.getEmail());
        password.setValue(user.getPassword());
    }

    public void updateData(View view,int pos) {
        User user = new User(pos,userName.getValue(), email.getValue(), password.getValue());
        userMutableLiveData.setValue(user);

    }

    public void updateUser(View view, User user) {
        Log.e(TAG, "updateUser: " + user.getId() + user.getUserName());
        BaseClass.getInstance().userDao().updateUser(user).subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onComplete() {
                        findNavController(view).popBackStack();
                        Log.e(TAG, "onComplete: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ");
                    }
                });
    }


}