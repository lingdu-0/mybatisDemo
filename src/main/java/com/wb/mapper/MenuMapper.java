package com.wb.mapper;

import com.wb.entity.Menu;

import java.util.List;

public interface MenuMapper {
    /**
     * 查询一个编号的所有子菜单
     *
     * @param id
     * @return
     */
    List<Menu> findByChilds(int id);

    /**
     * 查询一个编号的所有一级子菜单
     *
     * @param id
     * @return
     */
    List<Menu> findByOneChilds(int id);

    /**
     * 查询一个编号的顶级父菜单
     *
     * @param id
     * @return
     */
    Menu findByParent(int id);

    /**
     * 查询一个编号的直接父菜单
     *
     * @param id
     * @return
     */
    Menu findByOneParent(int id);

}
