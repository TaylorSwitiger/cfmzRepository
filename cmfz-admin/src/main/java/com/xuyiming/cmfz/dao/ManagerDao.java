package com.xuyiming.cmfz.dao;

import com.xuyiming.cmfz.entity.*;
import com.xuyiming.cmfz.entity.Manager;
import com.xuyiming.cmfz.entity.Menu;
import com.xuyiming.cmfz.entity.SysPermission;
import com.xuyiming.cmfz.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 管理员数据访问层接口
 * @Author 徐一鸣
 * @Time 2018-10-04 15:03:30
 */
public interface ManagerDao {

    /**
     * @Description 管理员登录时查找用户抽象方法
     * @Author      徐一鸣
     * @Time        2018-10-04 15:05:30
     * @Param       mgrName 管理员姓名
     * @Exception   null
     */
    public Manager selectManager(String mgrName);

    /**
     * @Description 侧面导航菜单查询
     * @Author      徐一鸣
     * @Time        2018-10-05 12:13:30
     * @Param       null
     * @Exception   null
     */
    public List<Menu> selectMenu();

    /**
     * @Description 更新
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param
     * @Exception   null
     */
    public int updateManager(Manager manager);

    /**
     * @Description 插入
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param
     * @Exception   null
     */
    public int insertManager(Manager manager);

    public List<SysRole> selectRoleByManagerName(String managerName);

    public List<SysPermission> selectPermissionByManagerName(String managerName);

    public int insertManagerRole(String managerId);

    public int updateManagerRole(@Param("roleId") Integer roleId, @Param("managerId") String managerId);

    public List<Manager> selectManagerByPage(@Param("begin") Integer begin, @Param("size") Integer size);

    public int selectManagerCout();

    public int selectManagerCoutByKey(@Param("key")String key,@Param("category") String category);

    public List<Manager> selectManagerByKey(@Param("begin") Integer begin, @Param("size") Integer size,@Param("key")String key,@Param("category")String category);
}
