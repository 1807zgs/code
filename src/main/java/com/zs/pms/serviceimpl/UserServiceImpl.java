package com.zs.pms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhlabs.composite.AddComposite;
import com.zs.pms.dao.UserDao;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.until.Constants;
import com.zs.pms.vo.QueryUser;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao dao;
	
	
	@Override
	public void hello() {
		System.out.println("hello spring");
		
	}

	@Override
	public List<TPermission> queryByUid(int id) {
		// TODO Auto-generated method stub
		return dao.queryByUid(id);
	}

	@Override
	public List<TPermission> genMenu(List<TPermission> pers) {
		//创建新容器
		List<TPermission>list=new ArrayList<>();
		//遍历权限列表
		for (TPermission per :pers) {
			//一级菜单
			if (per.getLev()==1) {
				for (TPermission per2 : pers) {
					if (per2.getPid()==per.getId()) {
						per.addChild(per2);
					}
				}
				
				
				list.add(per);
			}
		}
		
		return list;
	}

	@Override
	public List<TUser> queryByCon(QueryUser qu) {
		// TODO Auto-generated method stub
		return dao.queryByCon(qu);
	}

	@Override
	public void deleteByIds(int[] ids) {
		dao.deleteByIds(ids);
		
	}

	@Override
	public void updateUser(TUser user) {
		// TODO Auto-generated method stub
		dao.updateUser(user);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int insertUser(TUser user) {
		// TODO Auto-generated method stub
		dao.insertUser(user);
		int i=1/0;
		dao.insertUser(user);
		return user.getId();
	}

	@Override
	public List<TUser> queryByPage(int page, QueryUser qu) {
		int start=(page-1)*Constants.PASSGECONT+1;
		int end=page*Constants.PASSGECONT;
		qu.setStart(start);
		qu.setEnd(end);
		return dao.queryByPage(qu);
		
	}

	@Override
	public int queryPageCount(QueryUser qu) {
		int count = dao.queryCount(qu);
		if (count%Constants.PASSGECONT==0) {
			return count/Constants.PASSGECONT;
		} else {
		return	count/Constants.PASSGECONT+1;
		}
	}
  
}
