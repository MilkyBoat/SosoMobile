package com.sosoMobie;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * <H2>文件名称: CardUtil.java </H2>
 * <p>描述: [工具类，作为所有工具方法的集合] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 * <p><b>成员变量：</b></p>
 * <ul>
 *     <li>private Map[String, MobileCard] cards</li>
 *     <li>private Map[String, List[ConsumInfo]] consumInfos</li>
 *     <li>private List[Scene] scenes</li>
 * </ul>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>public CardUtil()</li>
 *     <li>public void initScenes()</li>
 *     <li>public boolean isExistCard(String number)</li>
 *     <li>public boolean isExistCard(String number, String password)</li>
 *     <li>public String[] getNewNumbers(int count)</li>
 *     <li>public void addCard(MobileCard card)</li>
 *     <li>public void delCard(String card)</li>
 *     <li>public void showRemainDetail(String number)</li>
 *     <li>public void showAmountDetail(String number)</li>
 *     <li>public void printConsumInfo(String number)</li>
 *     <li>public void useSoso(String number)</li>
 *     <li>private void addConsumInfo(String number, ConsumInfo info)</li>
 *     <li>public void showDescription()</li>
 *     <li>public void changingPack(String number, String packNum)</li>
 *     <li>public void chargeMoney(String number, double money)</li>
 * </ul>
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class CardUtil {
	
	private Map<String, MobileCard> cards;
	private Map<String, List<ConsumInfo>> consumInfos;
	private List<Scene> scenes;
	
	/***
	 *  CardUtil
	 * [工具类初始化，载入预置的账户信息，通话记录，场景]
	 * @author  徐云凯
	 */
	
	public CardUtil() {
		
		ServicePackage superPackage = new SuperPackage();
		ServicePackage talkPackage = new TalkPackage();
		ServicePackage netPackage = new NetPackage();
		
		cards = new HashMap<>();
		MobileCard[] mobileCards = new MobileCard[5];
		mobileCards[0] = new MobileCard("13965756432", "test1", "123", superPackage, 79);
		mobileCards[1] = new MobileCard("17822011172", "徐云凯", "1713667", superPackage, 23333);
		mobileCards[2] = new MobileCard("13924221868", "test2", "123", superPackage, 321);
		mobileCards[3] = new MobileCard("13911568956", "test3", "123", netPackage, 1230);
		mobileCards[4] = new MobileCard("13900000000", "test4", "123", talkPackage, 123);
		for (MobileCard i : mobileCards) cards.put(i.cardNumber, i);
		
		consumInfos = new HashMap<>();
		ConsumInfo[][] ifs = new ConsumInfo[2][3];
		ifs[0][0] = new ConsumInfo("13924221868", "通话", 30);
		ifs[0][1] = new ConsumInfo("13924221868", "上网", 30);
		ifs[0][2] = new ConsumInfo("13924221868", "通话", 10);
		ifs[1][0] = new ConsumInfo("13911568956", "短信", 5);
		ifs[1][1] = new ConsumInfo("13911568956", "上网", 500);
		for (int i = 0; i < 2; i++) {
			List<ConsumInfo> consumInfoList = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				if (ifs[i][j] != null) consumInfoList.add(ifs[i][j]);
			}
			consumInfos.put(Objects.requireNonNull(ifs[i][0]).cardNumber, consumInfoList);
		}
		
		for(MobileCard i : mobileCards){
			i.money -= i.serPackage.price;
		}
		
		initScenes();
	}
	
	/***
	 *  initScenes
	 * [场景初始化，载入预设的6个消费场景]
	 * @author  徐云凯
	 */
	
	public void initScenes() {
		scenes = new ArrayList<>();
		scenes.add(new Scene("通话", 90, "问候客户，谁知其如此难缠"));
		scenes.add(new Scene("上网", 90, "在B站学习GO语言"));
		scenes.add(new Scene("上网", 90, "在B站上看帅哥桐谷和人的战斗混剪"));
		scenes.add(new Scene("上网", 90, "在B站上看老婆洛天依的MMD"));
		scenes.add(new Scene("短信", 90, "给自己另一个手机号发短信来假装有女朋友"));
		scenes.add(new Scene("上网", 90, "手机开热点打星际"));
	}
	
	/***
	 *  isExistCard
	 * [判断指定卡号是否存在于系统中]
	 * @param number: 卡号
	 * @return boolean:存在返回true，不存在返回false
	 * @author  徐云凯
	 */
	
	public boolean isExistCard(String number) {
		return cards.containsKey(number);
	}
	
	/***
	 *  isExistCard
	 * [判断指定卡号是否存在且密码是否正确]
	 * @param number: 卡号
	 * @param password: 密码
	 * @return boolean:卡号存在且密码正确返回true，其他返回false
	 * @author  徐云凯
	 */
	
	public boolean isExistCard(String number, String password) {
		if (isExistCard(number)) return cards.get(number).password.equals(password);
		else return false;
	}
	
	/***
	 *  getNewNumbers
	 * [随机生成一组卡号]
	 * @param count: 需要的卡号数量
	 * @return String[] 生成的卡号
	 * @author  徐云凯
	 */
	
	public String[] getNewNumbers(int count) {
		Random random = new Random();
		int num;
		String[] numbers = new String[count];
		for (int i = 0; i < count; i++){
			do {
				num = random.nextInt(100000000);
			} while (isExistCard(String.valueOf(13900000000L + num)));
			numbers[i] = String.valueOf(13900000000L + num);
		}
		return numbers;
	}
	
	/***
	 *  addCard
	 * [添加电话卡到系统中]
	 * @param card:需要添加的电话卡 MobileCard 对象
	 * @author  徐云凯
	 */
	
	public void addCard(MobileCard card) {
		cards.put(card.cardNumber, card);
	}
	
	/***
	 *  delCard
	 * [从系统中删除电话卡]
	 * @param card: 需要删除的电话卡卡号
	 * @author  徐云凯
	 */
	
	public void delCard(String card) {
		cards.remove(card);
		System.out.print("*****办理退网*****\n" + "卡号" + card + "办理退网成功！\n" + "谢谢使用！\n");
	}
	
	/***
	 *  showRemainDetail
	 * [显示电话卡的套餐余量]
	 * @param number: 需要查询的卡号
	 * @author  徐云凯
	 */
	
	public void showRemainDetail(String number) {
		DecimalFormat formatData = new DecimalFormat("#.0");
		
		System.out.print("******套餐余量查询******\n" + "您的卡号是" + number + "套餐内剩余：\n");
		MobileCard c = cards.get(number);
		if (c.serPackage instanceof TalkPackage) {
			System.out.print("通话时长：" + Math.max(((TalkPackage) c.serPackage).talkTime - c.realTalkTime, 0) + "分钟\n"
							+ "短信条数：" + Math.max(((TalkPackage) c.serPackage).smsCount - c.realSMSCount, 0) + "条\n");
		} else if (c.serPackage instanceof SuperPackage) {
			System.out.print( "通话时长：" + Math.max(((SuperPackage) c.serPackage).talkTime - c.realTalkTime, 0) + "分钟\n"
							+ "短信条数：" + Math.max(((SuperPackage) c.serPackage).smsCount - c.realSMSCount, 0) + "条\n"
							+ "上网流量：" + formatData.format(Math.max(((SuperPackage) c.serPackage).flow - c.realFlow, 0)) + "GB\n");
		} else {
			System.out.print( "上网流量：" + formatData.format(Math.max(((NetPackage) c.serPackage).flow * 1024 - c.realFlow, 0)) + "MB\n");
		}
	}
	
	/***
	 *  showAmountDetail
	 * [显示当月账单]
	 * @param number: 需要查询的卡号
	 * @author  徐云凯
	 */
	
	public void showAmountDetail(String number) {
		DecimalFormat formatData = new DecimalFormat("#.0");
		
		MobileCard c = cards.get(number);
		
		System.out.print(
			"******本月账单查询******\n" +
			"您的卡号：" + number + "，当月账单：\n" +
			"套餐资费：" + formatData.format(c.serPackage.price) + "元\n" +
			"合计：" + formatData.format(c.consumAmount) + "元\n" +
			"账户余额：" + formatData.format(c.money) + "元\n");
	}
	
	/***
	 *  printConsumInfo
	 * 输出消费记录，并导出为txt文本文件保存
	 * @param number: 需要查询的卡号
	 * @author  徐云凯
	 */
	
	public void printConsumInfo(String number) {
		StringBuilder content = new StringBuilder();
		content.append("**********").append(number).append("消费记录**********\n").append("序号\t类型\t数据[通话(分钟)/上网(MB)/短信(条)]\n");
		List<ConsumInfo> list = consumInfos.get(number);
		for (int i = 0; i < list.size(); i++) {
			content.append(i + 1).append(".\t").append(list.get(i).type).append("\t").append(list.get(i).consumData).append("\n");
		}
		System.out.print(content);
		
		try {
			File file = new File(number + " 消费记录.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(content.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 *  useSoso
	 * 用户消费总方法，
	 *               随机从六个场景中选择一个，验证该卡套餐是否能够完成该场景操作后，分别调用对应类的对应消费方法，
	 *               最后对余额不足情况进行异常处理，
	 *               完成后添加一条消费记录并显示提示信息
	 * @param number:消费使用的卡号
	 * @author  徐云凯
	 */
	
	public void useSoso(String number) {
		Random random = new Random();
		int i;
		MobileCard c = cards.get(number);
		Scene scene;
		ConsumInfo info;
		boolean flag = false;
		
		do{
			i = random.nextInt(6);
			scene = scenes.get(i);
			
			if(c.serPackage instanceof SuperPackage)
				flag = true;
			else if(c.serPackage instanceof TalkPackage)
				flag = scene.type.equals("通话") || scene.type.equals("短信");
			else
				flag = scene.type.equals("上网");
		}while(!flag);
		
		try {
			if(scene.type.equals("通话"))
				((CallService)c.serPackage).call(scene.data, c);
			else if(scene.type.equals("短信"))
				((SendService)c.serPackage).send(scene.data, c);
			else
				((NetService)c.serPackage).netPlay(scene.data, c);
		} catch (InsufficientBalanceException e) {
			System.out.println("本次已" + e.done() + "，您的余额不足，请充值后再使用！");
			e.printStackTrace();
		} finally {
			System.out.print(scene.description + scene.type + scene.data);
			if (scene.type.equals("通话")) System.out.println("分钟");
			else if (scene.type.equals("上网")) System.out.println("MB");
			else System.out.println("条");
		}
		
		if (consumInfos.get(number) == null) {
			System.out.print("不存在此卡的消费记录，");
		}
		info = new ConsumInfo(number, scene.type, scene.data);
		addConsumInfo(number, info);
		System.out.println("已添加一条消费记录。");
	}
	
	private void addConsumInfo(String number, ConsumInfo info) {
		consumInfos.computeIfAbsent(number, k -> new ArrayList<>());
		consumInfos.get(number).add(info);
	}
	
	/***
	 *  showDescription
	 * 读取资费说明.txt并输出到屏幕
	 * @author  徐云凯
	 */
	
	public void showDescription() {
		
		try {
			FileReader fileReader = new FileReader("资费说明.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			
			while (line != null) {  //循环输出每一行数据
				System.out.println(line);
				line = bufferedReader.readLine();
			}
			
			bufferedReader.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("资费说明文件未找到");
		} catch (IOException e) {
			System.out.println("文件读取错误");
		}
	}
	
	/***
	 *  changingPack
	 * 更换套餐，并进行合理性检查，包括是否同一套餐，是否余额不足
	 * @param number: 卡号
	 * @param packNum: 目标套餐代号，TalkPackage为1，NetPackage为2，SuperPackage为3。
	 * @author  徐云凯
	 */
	
	public void changingPack(String number, String packNum) {
		MobileCard c = cards.get(number);
		
		//检查更换套餐是否发生了变更
		ServicePackage preSp = c.serPackage;
		if ((packNum.equals("1") && preSp instanceof TalkPackage)
				|| (packNum.equals("2") && preSp instanceof NetPackage)
				|| (packNum.equals("3") && preSp instanceof SuperPackage)) {
			System.out.println("对不起，您已经是该套餐用户，无需更换套餐！");
			return;
		}
		
		//检查余额
		ServicePackage toSp;
		if (packNum.equals("1")) toSp = new TalkPackage();
		else if (packNum.equals("2")) toSp = new NetPackage();
		else toSp = new SuperPackage();
		
		if (c.money < toSp.price) {
			System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！");
			return;
		}
		
		//扣除套餐费用，清空使用时间，更换套餐
		c.money -= toSp.price;
		c.realTalkTime = c.realSMSCount = c.realFlow = 0;
		c.serPackage = toSp;
		System.out.println("套餐更换成功");
		
		//输出相关信息
		c.showMeg();
		c.serPackage.showInfo();
	}
	
	/***
	 *  chargeMoney
	 * 账户充值，充值金额低于50元报错，完成后显示最新余额
	 * @param number: 卡号
	 * @param money: 充值金额
	 * @author  徐云凯
	 */
	
	public void chargeMoney(String number, double money) {
		//充值金额的检查在二级菜单中已完成，不做重复检查
		DecimalFormat formatData = new DecimalFormat("#.0");    //用于控制精度
		MobileCard mobileCard = cards.get(number);
		mobileCard.money += money;
		System.out.println(String.format("充值成功，当前话费余额为%s元。", formatData.format(mobileCard.money)));
	}
}
