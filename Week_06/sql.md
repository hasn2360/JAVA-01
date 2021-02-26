## 订单表 ##    
    CREATE TABLE `order` (
      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
      `order_id` varchar(32) NOT NULL DEFAULT '' COMMENT '订单号',
      `product_code` varchar(32) NOT NULL DEFAULT '' COMMENT '商品code',
      `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
      `count` int(11) NOT NULL DEFAULT '0' COMMENT '购买数量',
      `unit_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '单价',
      `real_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '实际支付价',
      `discount_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '让利价格',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
      PRIMARY KEY (`id`),
      UNIQUE KEY `unique_idx_order_id` (`order_id`),
      KEY `idx_product_code` (`product_code`),
      KEY `idx_user_name` (`user_name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';
## 商品表 ##
    CREATE TABLE `product` (
      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
      `product_code` varchar(32) NOT NULL DEFAULT '' COMMENT '商品code',
      `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名称',
      `product_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
      `short_desc` varchar(50) NOT NULL DEFAULT '' COMMENT '简要描述',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
      PRIMARY KEY (`id`),
      UNIQUE KEY `unique_idx_product_code` (`product_code`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';
## 用户表 ##
    CREATE TABLE `user` (
      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
      `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
      `real_name` varchar(30) NOT NULL DEFAULT '' COMMENT '真实姓名',
      `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
      `email` varchar(40) NOT NULL DEFAULT '' COMMENT '邮箱',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
      PRIMARY KEY (`id`),
      UNIQUE KEY `unique_idx_user_name` (`user_name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
## 说明 ##
1. 订单表通过product_code与商品表关联，通过user_name与用户表关联
2. product_code在商品表中唯一
3. user_name在用户表中唯一