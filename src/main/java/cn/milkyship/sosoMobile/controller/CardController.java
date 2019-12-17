package cn.milkyship.sosoMobile.controller;

import cn.milkyship.sosoMobile.model.MobileCard;
import cn.milkyship.sosoMobile.model.ServicePackage;
import cn.milkyship.sosoMobile.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.milkyship.sosoMobile.service.CardService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping ("/card")
public class CardController {
	
	@Resource
	private CardService cardService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping ("/mySosoMobile")
	public String logIn(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String number = request.getParameter("number");
		String password = request.getParameter("password");
		ObjectMapper mapper = new ObjectMapper();
		int returnParam = cardService.login(number, password);
		if (returnParam != 0) {
			if (returnParam == 1) {
				model.addAttribute("info", "账号不存在");
			}
			else {
				model.addAttribute("info", "密码错误");
			}
			return "/../../index";
		}
		request.getSession().setAttribute("number", number);
		request.getSession().setAttribute("password", password);
		return "/mySosoMobile";
	}
	
	@RequestMapping ("/getChanPacks.do")
	public void getChangeablePackages(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String number = request.getSession().getAttribute("number").toString();
		List<ServicePackage> packs = this.userService.getAviliablePackages(number);
		StringBuilder output = new StringBuilder();
		for (ServicePackage i : packs) {
			output.append("<option value='").append(i.getName()).append("'></option>");
		}
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(output));
		response.getWriter().close();
	}
	
	@RequestMapping ("/bill.do")
	public void addCard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String number = request.getSession().getAttribute("number").toString();
		String info = cardService.bill(number);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(info));
		response.getWriter().close();
	}
	
	@RequestMapping ("/remain.do")
	public void remain(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String number = request.getSession().getAttribute("number").toString();
		String info = cardService.remain(number);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(info));
		response.getWriter().close();
	}
	
	@RequestMapping ("/consumList.do")
	public void consumList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String number = request.getSession().getAttribute("number").toString();
		String consumptions = cardService.consumList(number);
//      String filePath = "file:///data/wwwroot/default/resources/" + number + "record.txt";
//		File file = null;
//		try {
//			file = new File(filePath);
//			if (file.createNewFile()) {
//				FileWriter writer = new FileWriter(file);
//				writer.write(consumptions);
//				writer.flush();
//				writer.close();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		ObjectMapper mapper = new ObjectMapper();
//		response.getWriter().write(mapper.writeValueAsString("/resources/" + number + "record.txt"));
		response.getWriter().write(mapper.writeValueAsString(consumptions));
		response.getWriter().close();
	}
	
	@RequestMapping ("/changePack.do")
	public void changePack(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String number = request.getSession().getAttribute("number").toString();
		String toPackStr = request.getParameter("pack");
		String info = cardService.changePack(number, toPackStr);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(info));
		response.getWriter().close();
	}
	
	@RequestMapping ("/del.do")
	public void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String number = request.getSession().getAttribute("number").toString();
		cardService.del(number);
		request.getSession().invalidate();
	}
}
