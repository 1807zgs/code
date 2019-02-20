import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class testUser {
	@Autowired
	UserService us;
	
 

	public void Testrole() {
		List<TPermission> list1 = us.queryByUid(3084);
	   for (TPermission tPermission : list1) {
		System.out.println(tPermission.getPname());
	}
	   System.out.println("-------整理后的-------");
	   for (TPermission per1 : us.genMenu(list1)) {
		System.out.println(per1.getPname());
		for (TPermission per2 : per1.getChildren()) {
			System.out.println("---"+per2.getPname());
		}
	}
	 
	}
	
	public void testQuery() {
		QueryUser queryUser=new QueryUser();
		queryUser.setLoginname("abc");
		List<TUser> users = us.queryByCon(queryUser);
		System.out.println(users.size());
		
		
	}
	
	public void testDelete() {
		int[]ids= {1005,1006};
		us.deleteByIds(ids);
	}

	public void testUpdate() {
		TUser user=new TUser();
		user.setLoginname("shun0123");
		user.setPassword("shun0123");
		user.setUpdator(1000);
		user.setId(1008);
		us.updateUser(user);
	}
	@Test
	public void testinsert() {
		TUser user=new TUser();
		TDep dep=new TDep();
		
		user.setLoginname("aa1123");
		user.setPassword("shun123");
		user.setSex("女");
		user.setBirthday(new Date());
		user.setEmail("insert5@qq.com");
		user.setDept(dep);
		user.setRealname("冀禹6");
		user.setCreator(1001);
		user.setPic("jysm.jsp");
		user.setIsenabled(1);
		us.insertUser(user);
		
		
	}
	
	public void testQuery1() {
		QueryUser queryUser=new QueryUser();
		queryUser.setSex("男");
		for (TUser user : us.queryByPage(1, queryUser)) {
			System.out.println(user.getId()+":"+user.getLoginname());
		}
		System.out.println("共"+us.queryPageCount(queryUser)+"页");
		
	}
}
