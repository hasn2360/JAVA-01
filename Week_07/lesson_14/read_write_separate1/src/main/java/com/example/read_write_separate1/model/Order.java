package com.example.read_write_separate1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    /**
     * 自增id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 订单号
     *
     * @mbg.generated
     */
    private String orderId;

    /**
     * 商品code
     *
     * @mbg.generated
     */
    private String productCode;

    /**
     * 用户名
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * 购买数量
     *
     * @mbg.generated
     */
    private Integer count;

    /**
     * 单价
     *
     * @mbg.generated
     */
    private BigDecimal unitPrice;

    /**
     * 实际支付价
     *
     * @mbg.generated
     */
    private BigDecimal realPrice;

    /**
     * 让利价格
     *
     * @mbg.generated
     */
    private BigDecimal discountPrice;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", productCode=").append(productCode);
        sb.append(", userName=").append(userName);
        sb.append(", count=").append(count);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", realPrice=").append(realPrice);
        sb.append(", discountPrice=").append(discountPrice);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}