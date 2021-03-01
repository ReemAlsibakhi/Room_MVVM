package com.reemsib.task1.utilis;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.reemsib.task1.room.UserDB;
import com.reemsib.task1.room.UserDao;

public class BaseClass extends Application {
    private static UserDB instance;
    public static Context context;
//    //migration
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

    }
    public static synchronized UserDB getInstance(){
        if (instance==null){
            instance = Room.databaseBuilder(context
                    ,UserDB.class, "user_db")
                    .allowMainThreadQueries()
              //      .addMigrations(MIGRATION_1_2)
                    .build()
            ;
        }
        return instance;
    }
//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
//                    + "`name` TEXT, PRIMARY KEY(`id`))");
//        }
//    };

}

