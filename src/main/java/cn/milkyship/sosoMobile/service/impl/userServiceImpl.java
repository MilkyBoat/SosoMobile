package cn.milkyship.sosoMobile.service.impl;
/*
 * <p>项目名称: sosoMobile </p>
 * <p>文件名称: userServiceImpl </p>
 * <p>创建时间: 2019-12-15 </p>
 * @author <a href="mail to: xuyunkai@mail.nankai.edu.cn" rel="nofollow">徐云凯</a>
 * @version v1.0
 */

import cn.milkyship.sosoMobile.dao.CardDao;
import cn.milkyship.sosoMobile.dao.ConsumInfoDao;
import cn.milkyship.sosoMobile.dao.ServicePackageDao;
import cn.milkyship.sosoMobile.model.ConsumInfo;
import cn.milkyship.sosoMobile.model.MobileCard;
import cn.milkyship.sosoMobile.model.Scene;
import cn.milkyship.sosoMobile.model.ServicePackage;
import cn.milkyship.sosoMobile.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service ("UserService")
public class userServiceImpl implements UserService {
	
	@Resource
	private CardDao cardDao;
	
	@Resource
	private ServicePackageDao servicePackageDao;
	
	@Resource
	private ConsumInfoDao consumInfoDao;
	
	@Override
	public String useSoso(String number) {
		
		StringBuilder output = new StringBuilder();
		
		MobileCard mobileCard = cardDao.findCardByNumber(number);
		if (mobileCard == null) {
			output.append("卡号不存在。");
			return output.toString();
		}
		
		ServicePackage servicePackage = servicePackageDao.findPackage(mobileCard.getSerPackage());
		
		List<Scene> scenes = sceneInit();
		Random random = new Random();
		Scene scene = scenes.get(random.nextInt(scenes.size()));
		
		ConsumInfo consumInfo;
		if (scene.type.equals("通话")) {
			consumInfo = servicePackage.call(scene.data, mobileCard);
		}
		else if (scene.type.equals("短信")) {
			consumInfo = servicePackage.send(scene.data, mobileCard);
		}
		else {
			consumInfo = servicePackage.netPlay(scene.data, mobileCard);
		}
		
		cardDao.update(mobileCard);
		
		output.append(scene.description);
		output.append(scene.type);
		output.append(consumInfo.consumData);
		if (scene.type.equals("通话")) {
			output.append("分钟。");
		}
		else if (scene.type.equals("上网")) {
			output.append("MB。");
		}
		else {
			output.append("条。");
		}
		
		if (consumInfo.consumData != scene.data) {
			output.append("余额不足！请充值。");
		}
		
		ConsumInfo[] consumInfos = consumInfoDao.findRecByNumber(number);
		if (consumInfos == null || consumInfos.length == 0) {
			output.append("不存在此卡的消费记录，");
		}
		consumInfoDao.save(consumInfo);
		output.append("已添加一条消费记录。");
		
		return output.toString();
	}
	
	private List<Scene> sceneInit() {
		List<Scene> scenes = new ArrayList<>();
		scenes.add(new Scene("通话", 90, "问候客户，谁知其如此难缠。"));
		scenes.add(new Scene("上网", 90, "在B站学习GO语言。"));
		scenes.add(new Scene("上网", 90, "在B站上看帅哥桐谷和人的战斗混剪。"));
		scenes.add(new Scene("上网", 90, "在B站上看老婆洛天依的MMD。"));
		scenes.add(new Scene("短信", 90, "给自己另一个手机号发短信来假装有女朋友。"));
		scenes.add(new Scene("上网", 90, "手机开热点打星际。"));
		return scenes;
	}
	
	@Override
	public String charge(String number, double money) {
		StringBuilder output = new StringBuilder();
		
		MobileCard mobileCard = cardDao.findCardByNumber(number);
		if (mobileCard == null) {
			output.append("卡号不存在。");
			return output.toString();
		}
		if (money < 20) {
			output.append("充值金额不得小于20元。");
			return output.toString();
		}
		
		mobileCard.money += money;
		cardDao.update(mobileCard);
		
		output.append("充值成功，当前余额");
		output.append(mobileCard.money);
		output.append("元。");
		
		return output.toString();
	}
	
	@Override
	public String reg(String number, String userName, String password, String servicePackageName, Double preMoney) {
		MobileCard newCard = new MobileCard();
		
		if (cardDao.findCardByNumber(number) != null) {
			return "卡号已被使用";
		}
		newCard.cardNumber = number;
		
		ServicePackage servicePackage = servicePackageDao.findPackage(servicePackageName);
		if (servicePackage == null) {
			return "套餐不存在";
		}
		newCard.serPackage = servicePackageName;
		newCard.userName = userName;
		newCard.password = password;
		
		if (preMoney - servicePackage.getPrice() < 0) {
			return "预存的话费金额不足以支付本月固定套餐资费";
		}
		
		//直接扣除首月套餐费再存入
		newCard.money = preMoney - servicePackage.getPrice();
		
		cardDao.save(newCard);
		
		StringBuilder info = new StringBuilder();
		info.append("注册成功！");
		DecimalFormat formatData = new DecimalFormat("#.0");
		info.append("卡号：").append(number).
				append(" 用户名：").append(userName).
				append(" 当前余额：").append(formatData.format(newCard.money)).append("元。");
		info.append(String.format("%s：通话时长为%d分钟/月，短信条数为%d条/月，上网流量为%dGB/月",
				servicePackage.getName(), servicePackage.getTalkTime(), servicePackage.getSmsCount(), servicePackage.getFlow()));
		return info.toString();
	}
	
	@Override
	public List<String> getNewNumbers(int numberCount) {
		List<String> numbers = new ArrayList<>();
		Random random = new Random();
		int num;
		for (int i = 0; i < numberCount; i++) {
			String numStr = null;
			MobileCard resCard = null;
			do {
				num = random.nextInt(100000000);
				numStr = String.valueOf(13900000000L + num);
				resCard = cardDao.findCardByNumber(numStr);
			} while (resCard != null);
			numbers.add(String.valueOf(13900000000L + num));
		}
		return numbers;
	}
	
	@Override
	public ServicePackage[] getServicePackages() {
		return servicePackageDao.findAllPackage();
	}
}
