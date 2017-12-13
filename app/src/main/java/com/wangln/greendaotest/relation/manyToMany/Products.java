package com.wangln.greendaotest.relation.manyToMany;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.wangln.greendaotest.dao.DaoSession;
import com.wangln.greendaotest.dao.OrdersDao;
import com.wangln.greendaotest.dao.ProductsDao;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
@Entity
public class Products {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    @ToMany
    @JoinEntity(entity = OrderWithProduct.class,
                sourceProperty = "productId",
                targetProperty = "orderId")
    private List<Orders> ordersList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1021527468)
    private transient ProductsDao myDao;
    @Generated(hash = 273118029)
    public Products(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1068248053)
    public Products() {
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
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ordersList=" + ordersList +
                '}';
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1262157958)
    public List<Orders> getOrdersList() {
        if (ordersList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OrdersDao targetDao = daoSession.getOrdersDao();
            List<Orders> ordersListNew = targetDao._queryProducts_OrdersList(id);
            synchronized (this) {
                if (ordersList == null) {
                    ordersList = ordersListNew;
                }
            }
        }
        return ordersList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1209682055)
    public synchronized void resetOrdersList() {
        ordersList = null;
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
    @Generated(hash = 651793051)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getProductsDao() : null;
    }
}
