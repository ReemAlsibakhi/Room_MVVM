package com.reemsib.task1.ui.alIUsers;

import android.util.Log;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.reemsib.task1.R;
import com.reemsib.task1.adapter.UserAdapter;
import com.reemsib.task1.model.UserObservable;
import com.reemsib.task1.room.User;

import java.util.List;

public class ViewUsersViewModel extends ViewModel {
    private UserObservable userObservable;
    private UserAdapter adapter;
    private  MutableLiveData<List<User>> users;
    public MutableLiveData<User> selected;
    private User user;
    private int pos=-1;
    private static final String TAG = "ViewUsersViewModel";

    public void init() {
        userObservable=new UserObservable();
        user=new User();
        adapter = new UserAdapter(R.layout.user_item, this);
        users=new MutableLiveData<>();
        selected = new MutableLiveData<>();
    }

    public void fetchList(View v) {
        userObservable.fetchList(v);
    }
    public void clickDelete(View v,int pos){
        userObservable.deleteUser(v,pos);
    }
    public void clickUpdate(View view,int pos){
        userObservable.passData(view,pos);


    }

    public MutableLiveData<List<User>> getUsers() {
        return userObservable.getUsers();
    }

    public UserAdapter getAdapter() {
        return adapter;
    }

    public void setUsersInAdapter(List<User> users) {
        this.adapter.setUsers(users);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<User> getSelected() {
        return selected;
    }

    public void onItemClick(Integer index) {
        pos=index;
        Log.e(TAG, "onItemClick: " +index+pos);
        User db = getUserAt(index);
        selected.setValue(db);
    }

    public User getUserAt(Integer index) {
        if (userObservable.getUsers().getValue() != null &&
                index != null &&
                userObservable.getUsers().getValue().size() > index) {
            return userObservable.getUsers().getValue().get(index);
        }
        return null;
    }

}