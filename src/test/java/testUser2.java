import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.dao.UserDao2;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService2;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class testUser2 {
	@Autowired
	UserService2 userService2;
	@Test
	public void testQuery() {
		QueryUser user=new QueryUser();
		user.setSex("男");
		List<TUser> queryByCon = userService2.queryByCon(user);
		System.out.println(queryByCon.size());
	}
}
