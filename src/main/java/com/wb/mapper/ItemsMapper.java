package com.wb.mapper;

import com.wb.entity.Items;
import com.wb.vo.QueryItems;

import java.util.List;

public interface ItemsMapper {
    /**
     * 根据商品名称或详情模糊查询
     *
     * @param queryItems
     * @return
     */
    List<Items> search(QueryItems queryItems);

    /**
     * 查询多个商品编号
     *
     * @param queryItems
     * @return
     */
    List<Items> findByIds(QueryItems queryItems);
}
