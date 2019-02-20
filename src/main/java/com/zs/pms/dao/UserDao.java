package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserDao {
  public List<TPermission> queryByUid(int id);
  public List<TUser>queryByCon(QueryUser qu);
  public void deleteByIds(int[]ids);
  public void updateUser(TUser user);
  public int insertUser(TUser user);
  public List<TUser>queryByPage(QueryUser qu);
  public int queryCount(QueryUser qu);
}
