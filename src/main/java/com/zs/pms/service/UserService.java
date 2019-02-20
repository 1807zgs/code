package com.zs.pms.service;

import java.util.List;

import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserService {
   public void hello();
   public List<TPermission> queryByUid(int id);
   public List<TPermission>genMenu(List<TPermission>pers);
   public List<TUser>queryByCon(QueryUser qu);
   public void deleteByIds(int[]ids);
   public void updateUser(TUser user);
   public int insertUser(TUser user);
   public List<TUser>queryByPage(int page,QueryUser qu);
   public int queryPageCount(QueryUser qu);
}
