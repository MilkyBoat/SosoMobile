package cn.milkyship.sosoMobile.controller;

import cn.milkyship.sosoMobile.model.ServicePackage;
import cn.milkyship.sosoMobile.service.CardService;
import cn.milkyship.sosoMobile.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping ("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping ("/useSoso.do")
	public void useSoso(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String number = request.getParameter("number");
		String info = userService.useSoso(number);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(info));
		response.getWriter().close();
	}
	
	@RequestMapping ("/charge.do")
	public void charge(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String parameter = request.getParameter("infos");
		String[] parameters = parameter.split(";");
		double amount = Double.parseDouble(parameters[1]);
		String info = userService.charge(parameters[0], amount);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(info));
		response.getWriter().close();
	}
	
	@RequestMapping ("/signUp")
	public String jumpToRegPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "/signUp";
	}
	
	@RequestMapping ("/reg.do")
	public void reg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String parameter = request.getParameter("info");
		String[] parameters = parameter.split(";");
		String number = parameters[0];
		String userName = parameters[1];
		String password = parameters[2];
		String servicePackage = parameters[3];
		Double preMoney = Double.parseDouble(parameters[4]);
		String info = userService.reg(number, userName, password, servicePackage, preMoney);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(info));
		response.getWriter().close();
	}
	
	@RequestMapping ("/getNumbers.do")
	public void getNewNumbers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int numReq = Integer.parseInt(request.getParameter("num"));
		List<String> nums = this.userService.getNewNumbers(numReq);
		StringBuilder output = new StringBuilder();
		for (String i : nums)
			output.append("<option value='").append(i).append("'></option>");
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(output));
		response.getWriter().close();
	}
	
	@RequestMapping ("/getPacks.do")
	public void getServicePackages(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<ServicePackage> packs = this.userService.getServicePackages();
		StringBuilder output = new StringBuilder();
		for (ServicePackage i : packs)
			output.append("<option value='").append(i.getName()).append("'></option>");
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(output));
		response.getWriter().close();
	}
}
