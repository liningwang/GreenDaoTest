package com.wangln.greendaotest.relation.oneToOne;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
@Entity
public class Product {
    @Id(autoincrement = true)
    private Long id;
    private String product_name;
    @Generated(hash = 1176656976)
    public Product(Long id, String product_name) {
        this.id = id;
        this.product_name = product_name;
    }
    @Generated(hash = 1890278724)
    public Product() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProduct_name() {
        return this.product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                '}';
    }
}
