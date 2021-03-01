package com.reemsib.task1.room;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table", indices = {@Index(value = {"email"}, unique = true)})
public class User extends BaseObservable implements Parcelable {

  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        public User[] newArray(int size) {
            return new User[size];
        }
    };

  @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "user_name")
    String userName;
    @ColumnInfo(name = "email")
    String Email;
    @ColumnInfo(name = "password")
    String Password;
    @Ignore
    String aa;

    public User() {
    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        Email = email;
        Password = password;
    }
  public User(int id,String userName, String email, String password) {
        this.id=id;
        this.userName = userName;
        Email = email;
        Password = password;
    }

    public User(String email, String password) {
        Email = email;
        Password = password;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5;
    }

    // Parcelling part
    public User(Parcel in){
        this.id = in.readInt();
        this.userName = in.readString();
        this.Email=  in.readString();
        this.Password=  in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
          parcel.writeInt(this.id);
          parcel.writeString(this.userName);
          parcel.writeString(this.Email);
          parcel.writeString(this.Password);
    }
}
