package com.wangln.greendaotest.simple;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;

import com.wangln.greendaotest.dao.DaoMaster;
import com.wangln.greendaotest.dao.DaoSession;
import com.wangln.greendaotest.updateDb.MySqliteOpenHelper;

/**
 * Created by Administrator on 2017/11/20 0020.
 */

public class MyApp extends Application{
    DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.OpenHelper helper = new MySqliteOpenHelper(this, "notes-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
    public DaoSession getDaoSession(){
        return daoSession;
    }
}
