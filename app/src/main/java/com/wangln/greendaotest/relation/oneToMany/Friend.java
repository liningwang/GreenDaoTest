package com.wangln.greendaotest.relation.oneToMany;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
@Entity
public class Friend {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private long peopleId;
    @Generated(hash = 67901280)
    public Friend(Long id, String name, long peopleId) {
        this.id = id;
        this.name = name;
        this.peopleId = peopleId;
    }
    @Generated(hash = 287143722)
    public Friend() {
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", peopleId=" + peopleId +
                '}';
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
    public long getPeopleId() {
        return this.peopleId;
    }
    public void setPeopleId(long peopleId) {
        this.peopleId = peopleId;
    }
}
