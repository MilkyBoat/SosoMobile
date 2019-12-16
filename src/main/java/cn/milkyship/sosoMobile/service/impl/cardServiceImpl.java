package cn.milkyship.sosoMobile.service.impl;
/*
 * <p>项目名称: sosoMobile </p>
 * <p>文件名称: cardServiceImpl </p>
 * <p>创建时间: 2019-12-15 </p>
 * @author <a href="mail to: xuyunkai@mail.nankai.edu.cn" rel="nofollow">徐云凯</a>
 * @version v1.0
 */

import cn.milkyship.sosoMobile.dao.CardDao;
import cn.milkyship.sosoMobile.model.MobileCard;
import cn.milkyship.sosoMobile.service.CardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service ("CardService")
public class cardServiceImpl implements CardService {
	
	@Resource
	private CardDao cardDao;
	
	@Override
	public String bill(MobileCard mobileCard) {
		return null;
	}
}
