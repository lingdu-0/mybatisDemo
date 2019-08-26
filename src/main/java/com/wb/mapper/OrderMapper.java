package com.wb.mapper;

import com.wb.entity.Orders;

import java.util.List;

public interface OrderMapper {
    /**
     * 一对多端    多端
     * 订单对订单详情
     *
     * @param id
     * @return
     */
    List<Orders> findAll(int id);

    /**
     * 一对多   一端
     *
     * @param id
     * @return
     */
    List<Orders> findAll2(int id);

}
