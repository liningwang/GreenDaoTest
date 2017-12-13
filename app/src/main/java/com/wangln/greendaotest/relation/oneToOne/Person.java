package com.wangln.greendaotest.relation.oneToOne;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.wangln.greendaotest.dao.DaoSession;
import com.wangln.greendaotest.dao.ProductDao;
import com.wangln.greendaotest.dao.PersonDao;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
@Entity
public class Person {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private Long pid;
    @ToOne(joinProperty = "pid")
    private Product product;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 778611619)
    private transient PersonDao myDao;
    @Generated(hash = 988150077)
    public Person(Long id, String name, Long pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }
    @Generated(hash = 1024547259)
    public Person() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", product=" + getProduct() +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getPid() {
        return this.pid;
    }
    public void setPid(Long pid) {
        this.pid = pid;
    }
    @Generated(hash = 587652864)
    private transient Long product__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 156842052)
    public Product getProduct() {
        Long __key = this.pid;
        if (product__resolvedKey == null || !product__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProductDao targetDao = daoSession.getProductDao();
            Product productNew = targetDao.load(__key);
            synchronized (this) {
                product = productNew;
                product__resolvedKey = __key;
            }
        }
        return product;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 741560049)
    public void setProduct(Product product) {
        synchronized (this) {
            this.product = product;
            pid = product == null ? null : product.getId();
            product__resolvedKey = pid;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2056799268)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPersonDao() : null;
    }
}
