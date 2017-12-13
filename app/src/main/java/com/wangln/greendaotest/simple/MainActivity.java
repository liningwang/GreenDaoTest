package com.wangln.greendaotest.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wangln.greendaotest.R;
import com.wangln.greendaotest.dao.DaoSession;
import com.wangln.greendaotest.dao.TestDao;
import com.wangln.greendaotest.dao.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    UserDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoSession daoSession = ((MyApp)getApplication()).getDaoSession();
        dao = daoSession.getUserDao();
    }
    public void insertUser(View view) {

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("wang"+i);
            dao.insert(user);
        }
        User user1 = new User();
        user1.setName("wang"+1);
        user1.setAge(100);
        dao.insert(user1);
        User user2 = new User();
        user2.setName("wang"+1);
        user2.setAge(101);
        dao.insert(user2);

        DaoSession daoSession = ((MyApp)getApplication()).getDaoSession();
        TestDao testDao = daoSession.getTestDao();
        Test test = new Test();
        test.setName("test");
        testDao.insert(test);
    }
    public void queryUser(View view) {
        List<User> users = dao.loadAll();
        Log.d("wang", "queryUser: " + users.toString());
    }
    public void modifyUser(View view) {
        User user = new User();
        user.setId((long) 5);
        user.setName("hahahhaha");
        dao.update(user);
    }
    public void deleteUser(View view) {
        dao.deleteByKey((long) 5);
    }
    public void queryListUser(View view) {
        List<User> users = dao.queryBuilder()
                .where(UserDao.Properties.Name.eq("wang5"))
                .orderDesc(UserDao.Properties.Id)
                .list();
        Log.d("wang", "queryListUser: " + users.toString());
    }
}
