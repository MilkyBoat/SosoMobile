package cn.milkyship.sosoMobile.controller;
/*
 * <p>项目名称: sosoMobbie </p>
 * <p>文件名称: CardController </p>
 * <p>创建时间: 2019-12-15 </p>
 * @author <a href="mail to: xuyunkai@mail.nankai.edu.cn" rel="nofollow">徐云凯</a>
 * @version v1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.milkyship.sosoMobile.service.CardService;

import java.io.IOException;

@Controller
@RequestMapping ("card")
public class CardController {
//	@Resource
//	private CardService cardService;
	
	@RequestMapping ("/addCard.do")
	public void addCard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		long userId = Long.parseLong(request.getParameter("id"));
		
		ObjectMapper mapper = new ObjectMapper();
		
		response.getWriter().close();
	}
}

