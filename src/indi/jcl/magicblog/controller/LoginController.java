package indi.jcl.magicblog.controller;

import indi.jcl.magicblog.service.IUserService;
import indi.jcl.magicblog.vo.Response;
import indi.jcl.magicblog.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {
	@Resource
	private IUserService userService;

	@RequestMapping(value = "login")
	@ResponseBody
	public Response login( String userName,String pwd,HttpSession session)throws Exception{
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(pwd)){
			return new Response(Response.FAIL,"用户名或密码不能为空");
		}
		User user = userService.login(userName,pwd);
		if(user==null){
			return new Response(Response.FAIL,"用户名或密码错误");
		}
		session.setAttribute("session",user);
		return new Response(Response.SUCCESS,"登录成功");

	}
}