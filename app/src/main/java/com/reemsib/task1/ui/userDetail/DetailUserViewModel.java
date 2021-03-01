package com.reemsib.task1.ui.userDetail;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.reemsib.task1.room.User;

public class DetailUserViewModel extends ViewModel {
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public void setData(User user){
      userName.setValue(user.getUserName());
      email.setValue(user.getEmail());
      password.setValue(user.getPassword());
    }

}