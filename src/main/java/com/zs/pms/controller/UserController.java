package com.zs.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.service.DepService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@Controller
public class UserController {
	@Autowired
	UserService us;
	@Autowired
	DepService ds;
	@RequestMapping("/user/list.do")
	public String list(String page,QueryUser queryUser ,ModelMap map) {
		if (page==null) {
			page="1";
		}
		map.addAttribute("LIST",us.queryByPage(Integer.parseInt(page), queryUser));
		map.addAttribute("PAGECONT",us.queryPageCount(queryUser));
		map.addAttribute("PAGE", page);
		map.addAttribute("QUERY", queryUser);
		return "user/list";
	}
	@RequestMapping("/user/toadd.do")
	public String toadd(ModelMap model) {
		model.addAttribute("DLIST",ds.queryByPid(0));
		return"user/add";
	}
	
	
	
	
}
