package cn.milkyship.sosoMobile.service.impl;
/*
 * <p>项目名称: sosoMobile </p>
 * <p>文件名称: cardServiceImpl </p>
 * <p>创建时间: 2019-12-15 </p>
 * @author <a href="mail to: xuyunkai@mail.nankai.edu.cn" rel="nofollow">徐云凯</a>
 * @version v1.0
 */

import cn.milkyship.sosoMobile.dao.CardDao;
import cn.milkyship.sosoMobile.dao.ConsumInfoDao;
import cn.milkyship.sosoMobile.dao.ServicePackageDao;
import cn.milkyship.sosoMobile.model.ConsumInfo;
import cn.milkyship.sosoMobile.model.MobileCard;
import cn.milkyship.sosoMobile.model.ServicePackage;
import cn.milkyship.sosoMobile.service.CardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;

@Service ("CardService")
public class cardServiceImpl implements CardService {
	
	@Resource
	private CardDao cardDao;
	
	@Resource
	private ServicePackageDao servicePackageDao;
	
	@Resource
	private ConsumInfoDao consumInfoDao;
	
	@Override
	public int login(String number, String password) {
		if (cardDao.findCardByNumber(number) == null) {
			return 1;
		}
		if (cardDao.checkCard(number, password) == null) {
			return 2;
		}
		return 0;
	}
	
	@Override
	public String bill(String number) {
		MobileCard mobileCard = cardDao.findCardByNumber(number);
		ServicePackage servicePackage = servicePackageDao.findPackage(mobileCard.getSerPackage());
		StringBuilder out = new StringBuilder();
		DecimalFormat formatData = new DecimalFormat("#0.00");
		out.append("<p>您的卡号：").append(number)
				.append("</p><p>当月账单：").append(formatData.format(mobileCard.getConsumAmount()))
				.append("</p><p>套餐资费：").append(formatData.format(servicePackage.getPrice())).append("元</p>")
				.append("<p>合计：").append(formatData.format(mobileCard.getConsumAmount() + servicePackage.getPrice())).append("元</p>")
				.append("<p>账户余额：").append(formatData.format(mobileCard.getMoney())).append("元</p>");
		return out.toString();
	}
	
	@Override
	public String remain(String number) {
		MobileCard mobileCard = cardDao.findCardByNumber(number);
		ServicePackage servicePackage = servicePackageDao.findPackage(mobileCard.getSerPackage());
		StringBuilder out = new StringBuilder();
		DecimalFormat formatData = new DecimalFormat("#0.00");
		out.append("<p>您的卡号是").append(number)
				.append("</p><p>套餐内剩余：</p>")
				.append("<p>通话时长：").append(Math.max(servicePackage.getTalkTime() - mobileCard.getRealTalkTime(), 0)).append("分钟</p>")
				.append("<p>短信条数：").append(Math.max(servicePackage.getSmsCount() - mobileCard.getRealSMSCount(), 0)).append("条</p>")
				.append("<p>上网流量：").append(formatData.format(Math.max(servicePackage.getFlow() - mobileCard.getRealFlow(), 0))).append("MB</p>");
		return out.toString();
	}
	
	@Override
	public String consumList(String number) {
//		StringBuilder content = new StringBuilder();
//		content.append("**********").append(number).append("消费记录**********\n").append("序号\t类型\t数据[通话(分钟)/上网(MB)/短信(条)]\n");
//		ConsumInfo[] list = consumInfoDao.findRecByNumber(number);
//		int ind = 1;
//		for (ConsumInfo i : list) {
//			if (i != null) {
//				content.append(ind++).append(".\t").append(i.getRecType()).append("\t").append(i.consumData).append("\n");
//			}
//		}
		StringBuilder content = new StringBuilder();
		content.append("<table border='1'><caption>").append("消费记录</caption>")
				.append("<thead><tr><th>序号</th><th>类型</th><th>数据[通话(分钟)/上网(MB)/短信(条)]</th></tr></thead>");
		ConsumInfo[] list = consumInfoDao.findRecByNumber(number);
		int ind = 1;
		content.append("<tbody>");
		for (ConsumInfo i : list) {
			if (i != null) {
				content.append("<tr><td>")
						.append(ind++).append("</td><td>")
						.append(i.getRecType()).append("</td><td>")
						.append(i.consumData).append("</td></tr>");
			}
		}
		content.append("</tbody>");
		return content.toString();
	}
	
	@Override
	public String changePack(String number, String toPack) {
		MobileCard mobileCard = cardDao.findCardByNumber(number);
		if (mobileCard.getSerPackage().equals(toPack)) {
			return "转出套餐与当前套餐相同";
		}
		ServicePackage servicePackage = servicePackageDao.findPackage(toPack);
		if (mobileCard.getMoney() < servicePackage.getPrice()) {
			return "余额不足以支付新套餐首月资费";
		}
		mobileCard.setSerPackage(toPack);
		cardDao.update(mobileCard);
		StringBuilder info = new StringBuilder();
		info.append("套餐转换成功。");
		DecimalFormat formatData = new DecimalFormat("#.0");
		info.append(" 当前余额：").append(formatData.format(mobileCard.getMoney())).append("元。")
				.append("当前套餐：").append(mobileCard.getSerPackage())
				.append(String.format(",通话时长为%d分钟/月，短信条数为%d条/月，上网流量为%dMB/月",
						servicePackage.getTalkTime(), servicePackage.getSmsCount(), servicePackage.getFlow()));
		return info.toString();
	}
	
	@Override
	public void del(String number) {
		cardDao.del(number);
	}
}
