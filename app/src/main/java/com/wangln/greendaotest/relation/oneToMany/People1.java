package com.wangln.greendaotest.relation.oneToMany;

import com.wangln.greendaotest.dao.DaoSession;
import com.wangln.greendaotest.dao.FriendDao;
import com.wangln.greendaotest.dao.PeopleDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;
import com.wangln.greendaotest.dao.People1Dao;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
@Entity
public class People1 {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private Date createdTime;
    @ToMany(referencedJoinProperty = "peopleId")
    private List<Friend> friendList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1509775001)
    private transient People1Dao myDao;
    @Generated(hash = 668757155)
    public People1(Long id, String name, Date createdTime) {
        this.id = id;
        this.name = name;
        this.createdTime = createdTime;
    }
    @Generated(hash = 490280816)
    public People1() {
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
        return "People1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdTime=" + createdTime +
                ", friendList=" + friendList +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                '}';
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1870429802)
    public List<Friend> getFriendList() {
        if (friendList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FriendDao targetDao = daoSession.getFriendDao();
            List<Friend> friendListNew = targetDao._queryPeople1_FriendList(id);
            synchronized (this) {
                if (friendList == null) {
                    friendList = friendListNew;
                }
            }
        }
        return friendList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1699354330)
    public synchronized void resetFriendList() {
        friendList = null;
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
    @Generated(hash = 65018629)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPeople1Dao() : null;
    }
    public Date getCreatedTime() {
        return this.createdTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
