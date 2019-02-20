package com.zs.pms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.until.DateUtil;
import com.zs.pms.until.MD5;
import com.zs.pms.vo.ChkLogin;
import com.zs.pms.vo.QueryUser;



//main 改成 right  menu 改成left  index 改成了main
@Controller
public class LoginController {
	@Autowired
	UserService us;
	@RequestMapping("/tologin.do")
	public String tologin() {
		return "login";
	}
	@RequestMapping("/login.do")
	public String login(ChkLogin chkLogin,QueryUser qu,HttpSession session,ModelMap model) {
		String chkcode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!chkcode.equalsIgnoreCase(chkLogin.getChkcode())) {
			model.addAttribute("MSG","验证码输入有误，请重新输入");
			return "login";
		}
		qu.setLoginname(chkLogin.getLoginname());
		
		
		qu.setPassword(chkLogin.getPassword());
		
		qu.setIsenabled(1);
		List<TUser> users = us.queryByCon(qu);
		if (users==null||users.size()!=1) {
			model.addAttribute("MSG","用户名或验证码输入有误，请重新输入");
			return "login";
		}
		session.setAttribute("CUSER",users.get(0));
		return"main";
	}
	@RequestMapping("/top.do")
	public String top(HttpSession session,ModelMap model) {
		TUser cUser= (TUser)session.getAttribute("CUSER");
		model.addAttribute("TODAY",DateUtil.getStrDate(new Date()));
		model.addAttribute("REALNAME",cUser.getRealname());
		return"top";
	}
	@RequestMapping("/left.do")
	public String left(HttpSession session,ModelMap model) {
		TUser cUser= (TUser)session.getAttribute("CUSER");
		List<TPermission> list1 = us.queryByUid(cUser.getId());
		model.addAttribute("MENU",us.genMenu(list1));
		return"left";
	}
	@RequestMapping("/right.do")
	public String right() {
		return"right";
	}
}
