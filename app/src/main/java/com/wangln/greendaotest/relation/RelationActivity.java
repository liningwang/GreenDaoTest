package com.wangln.greendaotest.relation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wangln.greendaotest.R;
import com.wangln.greendaotest.dao.FriendDao;
import com.wangln.greendaotest.dao.OrderWithProductDao;
import com.wangln.greendaotest.dao.OrdersDao;
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
import com.wangln.greendaotest.relation.oneToOne.Person;
import com.wangln.greendaotest.relation.oneToOne.Product;
import com.wangln.greendaotest.simple.MyApp;

import java.util.ArrayList;
import java.util.List;

public class RelationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation);
    }
    public void productManyToManyData(View v) {
        //产生订单数据
        OrdersDao orderDao = ((MyApp)getApplication()).getDaoSession().getOrdersDao();
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
        ProductsDao proDao = ((MyApp)getApplication()).getDaoSession().getProductsDao();
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
        OrderWithProductDao orderPro = ((MyApp)getApplication()).getDaoSession().getOrderWithProductDao();
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
        OrdersDao orderDao = ((MyApp)getApplication()).getDaoSession().getOrdersDao();
        List<Orders> orders =
                orderDao.queryBuilder()
                .build()
                .list();
        Log.d("wang", "productManyToManyData: " + orders.toString() + " data " + orders.get(0).getProductsList().toString());
        ProductsDao proDao = ((MyApp)getApplication()).getDaoSession().getProductsDao();
        List<Products> products = proDao.queryBuilder()
                .build()
                .list();
        //两个toString都调用的话，会出现你调我，我调你，从而产生oom。
        Log.d("wang", "productManyToManyData: " + products.toString() + " data " + products.get(0).getOrdersList().size());
    }
    public void productData(View view) {
        ProductDao pd = ((MyApp)getApplication()).getDaoSession().getProductDao();
        PersonDao personDao = ((MyApp)getApplication()).getDaoSession().getPersonDao();
        Product product = new Product();
        product.setProduct_name("phone");
        pd.insert(product);
        Person person = new Person();
        person.setName("wang");
        person.setProduct(product);
        personDao.insert(person);
    }
    public void getOneToOneData(View view) {
        PersonDao personDao = ((MyApp)getApplication()).getDaoSession().getPersonDao();
        List<Person> personList = personDao.queryBuilder()
                .where(PersonDao.Properties.Name.eq("wang"))
                .list();
        Log.d("wang", "getOneToOneData: " + personList.toString());
    }
    public void productOneToManyData(View v) {
        FriendDao friendDao = ((MyApp)getApplication()).getDaoSession().getFriendDao();
        PeopleDao peopleDao = ((MyApp)getApplication()).getDaoSession().getPeopleDao();
        List<Friend> friends = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Friend friend = new Friend();
            friend.setName("aaa" + i);
            friend.setPeopleId(10);
            friends.add(friend);
//            friendDao.insert(friend);
        }
        friendDao.insertInTx(friends);
        People people = new People();
        people.setName("wanglining");
        people.setId((long) 10);
        peopleDao.insert(people);

    }
    public void getOneToManyData(View v) {
        PeopleDao peopleDao = ((MyApp)getApplication()).getDaoSession().getPeopleDao();
        List<People> peoples =
                peopleDao.queryBuilder()
                .where(PeopleDao.Properties.Id.eq(10))
                .list();
        Log.d("wang", "getOneToManyData: " + peoples.toString());
    }
}
