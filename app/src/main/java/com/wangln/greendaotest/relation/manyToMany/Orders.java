package com.wangln.greendaotest.relation.manyToMany;

import com.wangln.greendaotest.relation.oneToOne.Product;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.wangln.greendaotest.dao.DaoSession;
import com.wangln.greendaotest.dao.ProductsDao;
import com.wangln.greendaotest.dao.OrdersDao;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
@Entity
public class Orders {
    @Id(autoincrement = true)
    private Long id;
    private String orderNum;
    @ToMany
    @JoinEntity(entity = OrderWithProduct.class,
                sourceProperty = "orderId",
                targetProperty = "productId")
    private List<Products> productsList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1717339351)
    private transient OrdersDao myDao;
    @Generated(hash = 1540984694)
    public Orders(Long id, String orderNum) {
        this.id = id;
        this.orderNum = orderNum;
    }
    @Generated(hash = 1753857294)
    public Orders() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderNum() {
        return this.orderNum;
    }
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderNum='" + orderNum + '\'' +
                ", productsList=" + productsList +
                '}';
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1620328510)
    public List<Products> getProductsList() {
        if (productsList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProductsDao targetDao = daoSession.getProductsDao();
            List<Products> productsListNew = targetDao
                    ._queryOrders_ProductsList(id);
            synchronized (this) {
                if (productsList == null) {
                    productsList = productsListNew;
                }
            }
        }
        return productsList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1257519328)
    public synchronized void resetProductsList() {
        productsList = null;
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
    @Generated(hash = 121826766)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOrdersDao() : null;
    }

}
