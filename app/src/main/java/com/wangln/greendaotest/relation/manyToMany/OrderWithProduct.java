package com.wangln.greendaotest.relation.manyToMany;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/11/20 0020.
 */

@Entity
public class OrderWithProduct {
    @Id(autoincrement = true)
    private Long id;
    private Long productId;
    private Long orderId;
    @Generated(hash = 558037291)
    public OrderWithProduct(Long id, Long productId, Long orderId) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
    }
    @Generated(hash = 2126010695)
    public OrderWithProduct() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return this.productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Long getOrderId() {
        return this.orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
