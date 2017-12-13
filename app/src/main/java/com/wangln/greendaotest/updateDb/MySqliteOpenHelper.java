package com.wangln.greendaotest.updateDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wangln.greendaotest.dao.DaoMaster;
import com.wangln.greendaotest.dao.FriendDao;
import com.wangln.greendaotest.dao.OrderWithProductDao;
import com.wangln.greendaotest.dao.OrdersDao;
import com.wangln.greendaotest.dao.PeopleDao;
import com.wangln.greendaotest.dao.PersonDao;
import com.wangln.greendaotest.dao.ProductDao;
import com.wangln.greendaotest.dao.ProductsDao;
import com.wangln.greendaotest.dao.TestDao;
import com.wangln.greendaotest.dao.UserDao;

/**
 * Created by Administrator on 2017/11/20 0020.
 */

public class MySqliteOpenHelper extends DaoMaster.OpenHelper {
    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("wang", "onUpgrade: " + oldVersion + " new " + newVersion);
        MigrationHelper.migrate(db, FriendDao.class, OrdersDao.class, OrderWithProductDao.class
                , PeopleDao.class, PersonDao.class, ProductDao.class, ProductsDao.class, UserDao.class, TestDao.class);
    }
}
