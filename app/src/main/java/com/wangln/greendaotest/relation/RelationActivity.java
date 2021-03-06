package com.wangln.greendaotest.relation;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wangln.greendaotest.R;
import com.wangln.greendaotest.dao.FriendDao;
import com.wangln.greendaotest.dao.OrderWithProductDao;
import com.wangln.greendaotest.dao.OrdersDao;
import com.wangln.greendaotest.dao.People1Dao;
import com.wangln.greendaotest.dao.PeopleDao;
import com.wangln.greendaotest.dao.PersonDao;
import com.wangln.greendaotest.dao.ProductDao;
import com.wangln.greendaotest.dao.ProductsDao;
import com.wangln.greendaotest.dao.UserDao;
import com.wangln.greendaotest.relation.manyToMany.OrderWithProduct;
import com.wangln.greendaotest.relation.manyToMany.Orders;
import com.wangln.greendaotest.relation.manyToMany.Products;
import com.wangln.greendaotest.relation.oneToMany.Friend;
import com.wangln.greendaotest.relation.oneToMany.People;
import com.wangln.greendaotest.relation.oneToMany.People1;
import com.wangln.greendaotest.relation.oneToOne.Person;
import com.wangln.greendaotest.relation.oneToOne.Product;
import com.wangln.greendaotest.simple.MyApp;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

public class RelationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation);
    }

    public void productManyToManyData(View v) {
        //产生订单数据
        OrdersDao orderDao = ((MyApp) getApplication()).getDaoSession()
                .getOrdersDao();
        Orders orders = new Orders();
        orders.setId((long) 11);
        orders.setOrderNum("3232134455");
        orderDao.insert(orders);
        Orders orders1 = new Orders();
        orders1.setId((long) 12);
        orders1.setOrderNum("3232134466");
        orderDao.insert(orders1);
        Orders orders2 = new Orders();
        orders2.setId((long) 13);
        orders2.setOrderNum("3232134477");
        orderDao.insert(orders2);
        //产生商品数据
        ProductsDao proDao = ((MyApp) getApplication()).getDaoSession()
                .getProductsDao();
        Products pro = new Products();
        pro.setId((long) 21);
        pro.setName("手机");
        proDao.insert(pro);
        Products pro1 = new Products();
        pro1.setId((long) 22);
        pro1.setName("电脑");
        proDao.insert(pro1);
        Products pro2 = new Products();
        pro2.setId((long) 23);
        pro2.setName("超薄本");
        proDao.insert(pro2);
        //生成中间表数据 一个订单多个商品
        OrderWithProductDao orderPro = ((MyApp) getApplication())
                .getDaoSession().getOrderWithProductDao();
        OrderWithProduct ordPro = new OrderWithProduct();
        ordPro.setOrderId((long) 11);
        ordPro.setProductId((long) 21);
        orderPro.insert(ordPro);
        OrderWithProduct ordPro1 = new OrderWithProduct();
        ordPro1.setOrderId((long) 11);
        ordPro1.setProductId((long) 22);
        orderPro.insert(ordPro1);

        OrderWithProduct ordPro2 = new OrderWithProduct();
        ordPro2.setOrderId((long) 12);
        ordPro2.setProductId((long) 21);
        orderPro.insert(ordPro2);
        OrderWithProduct ordPro3 = new OrderWithProduct();
        ordPro3.setOrderId((long) 12);
        ordPro3.setProductId((long) 22);
        orderPro.insert(ordPro3);

    }

    public void getManyToManyData(View v) {
        OrdersDao orderDao = ((MyApp) getApplication()).getDaoSession()
                .getOrdersDao();
        List<Orders> orders =
                orderDao.queryBuilder()
                        .build()
                        .list();
        Log.d("wang", "productManyToManyData: " + orders.toString() + " data " +
                "" + orders.get(0).getProductsList().toString());
        ProductsDao proDao = ((MyApp) getApplication()).getDaoSession()
                .getProductsDao();
        List<Products> products = proDao.queryBuilder()
                .build()
                .list();
        //两个toString都调用的话，会出现你调我，我调你，从而产生oom。
        Log.d("wang", "productManyToManyData: " + products.toString() + " " +
                "data " + products.get(0).getOrdersList().size());
    }

    public void productData(View view) {
        ProductDao pd = ((MyApp) getApplication()).getDaoSession()
                .getProductDao();
        PersonDao personDao = ((MyApp) getApplication()).getDaoSession()
                .getPersonDao();
        Product product = new Product();
        product.setProduct_name("phone");
        pd.insert(product);
        Person person = new Person();
        person.setName("wang");
        person.setProduct(product);
        personDao.insert(person);
    }

    public void getOneToOneData(View view) {
        PersonDao personDao = ((MyApp) getApplication()).getDaoSession()
                .getPersonDao();
        List<Person> personList = personDao.queryBuilder()
                .where(PersonDao.Properties.Name.eq("wang"))
                .list();
        Log.d("wang", "getOneToOneData: " + personList.toString());
    }

    public void productOneToManyData(View v) {
        FriendDao friendDao = ((MyApp) getApplication()).getDaoSession()
                .getFriendDao();
        PeopleDao peopleDao = ((MyApp) getApplication()).getDaoSession()
                .getPeopleDao();
        List<Friend> friends = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Friend friend = new Friend();
            friend.setName("aaa" + i);
            friend.setPeopleId(12);
            friends.add(friend);
//            friendDao.insert(friend);
        }
        friendDao.insertInTx(friends);
        People people = new People();
        people.setCreatedTime(new Date());
        people.setName("wanglining");
        people.setId((long) 12);
        peopleDao.insert(people);

    }

    public void productOneToManyData1(View v) {
        FriendDao friendDao = ((MyApp) getApplication()).getDaoSession()
                .getFriendDao();
        People1Dao peopleDao = ((MyApp) getApplication()).getDaoSession()
                .getPeople1Dao();
        List<Friend> friends = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Friend friend = new Friend();
            friend.setName("bbb" + i);
            friend.setPeopleId(9);
            friends.add(friend);
//            friendDao.insert(friend);
        }
        friendDao.insertInTx(friends);
        People1 people = new People1();
        people.setCreatedTime(new Date());
        people.setName("wanglibbb");
        people.setId((long) 9);
        peopleDao.insert(people);

    }

    public void genUnion(View view) {
        productOneToManyData(null);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productOneToManyData1(null);
    }

    public void doUnion(View view) {
        People1Dao peopleDao = ((MyApp) getApplication()).getDaoSession()
                .getPeople1Dao();
        //union例子
//        Query<People1> query = Query.internalCreate(peopleDao,"select *
// from" +
//                " PEOPLE1 union select * from PEOPLE order by CREATED_TIME " +
//                "ASC",new
//                String[]{});
        //group by 例子
        Database database = ((MyApp) getApplication()).getDaoSession()
                .getDatabase();

        Query<People1> query = Query.internalCreate(peopleDao, "SELECT NAME," +
                "_id, CREATED_TIME,sum(_id)  FROM PEOPLE1 union select NAME," +
                "CREATED_TIME," +
                "_id,sum(_id) " +
                "from PEOPLE group by _id order by CREATED_TIME desc;", new
                String[]{});

        List<People1> people1s = query.list();
        Log.d("wang", "peoples " + people1s.toString());
    }
    private void transaction() throws Exception {

        //带结果的事务,在主线程完成的不是异步
        String result = ((MyApp) getApplication()).getDaoSession().callInTx(new
                                                                     Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
        //不带结果的事务,在主线程完成的不是异步
        ((MyApp) getApplication()).getDaoSession().runInTx(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
    private void rawQuery(){
        Cursor cursor = ((MyApp) getApplication()).getDaoSession().getDatabase()
                .rawQuery
                ("select * from people where id = ?", new String[]{"1"});
    }
    public void asyncOpearate() throws Exception {
        AsyncSession asyncSession = ((MyApp) getApplication()).getDaoSession()
                .startAsyncSession();

        //没有返回值的异步，后缀带TX的都是在事务中完成的。
        //如果指定了第二个参数为FLAG_MERGE_TX，
        // 则在从队列中拿到AsyncOperation处理前会先等待一定时间去获取下一个AsyncOperation，
        // 判断是否与其可以进行使用同一事务的合并操作
        //如果不指定第二个参数，默认是不会这样的。
        asyncSession.runInTx(new Runnable() {
            @Override
            public void run() {
                //操作sql
            }
        },AsyncOperation.FLAG_MERGE_TX);
        //有返回值的异步
        // 泛型就是返回的值，最终会给AsyncOperation operation的getResult方法
        asyncSession.callInTx(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //操作sql
                return null;
            }
        });
        asyncSession.setListenerMainThread(new AsyncOperationListener(){

            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                //异步操作的监听结果
                //完成
                operation.isCompleted();
                //失败
                operation.isFailed();
                //获取结果操作
                operation.getResult();
            }
        });
        People1Dao peopleDao = ((MyApp) getApplication()).getDaoSession()
                .getPeople1Dao();
        Query<People1> query = peopleDao.queryBuilder().where(People1Dao
                .Properties
                .Name.eq
                ("Joe"))
                .orderAsc(People1Dao.Properties.Id).build();
        //查询结果从onAsyncOperationCompleted中拿
        asyncSession.queryList(query);
//      asyncSession.insert(people);
    }
    public void getOneToManyData(View v) {
        PeopleDao peopleDao = ((MyApp) getApplication()).getDaoSession()
                .getPeopleDao();
        List<People> peoples =
                peopleDao.queryBuilder()
                        .where(PeopleDao.Properties.Id.eq(10))
                        .list();
        Log.d("wang", "getOneToManyData: " + peoples.toString());
    }
}
