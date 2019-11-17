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
	
	private SosoDao sosoDAO;
	private List<Scene> scenes;
	
	/***
	 *  CardUtil
	 * [工具类初始化，载入预置的账户信息，通话记录，场景]
	 * @author  徐云凯
	 */
	
	public CardUtil() {
		sosoDAO = new SosoDaoMySQL();
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
	 * Title: singnedIn
	 * Description: [用户登录]
	 * @return java.lang.String
	 * @author 徐云凯
	 * Datetime:  2019/11/16 20:28
	 */
	
	public String singnedIn() {
		Scanner scanner = new Scanner(System.in);
		String card, password;
		boolean flag;
		
		System.out.print("请输入手机卡号：");
		card = scanner.next();
		flag = isExistCard(card);
		if (flag) {   //先检查卡号是否存在，验证存在后再输入密码
			System.out.print("请输入密码：");
			password = scanner.next();
			flag = isExistCard(card, password);
			if (flag) {   //再检查密码是否正确
				System.out.println("登录成功");
				return card;
			}
			else {
				System.out.println("密码输入错误，请重试");
			}
		}
		else {
			System.out.println("手机卡号不存在，请重试");
		}
		return null;
	}
	
	/***
	 *  isExistCard
	 * [判断指定卡号是否存在于系统中]
	 * @param number: 卡号
	 * @return boolean:存在返回true，不存在返回false
	 * @author  徐云凯
	 */
	
	public boolean isExistCard(String number) {
		return sosoDAO.isCardExit(number);
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
		return sosoDAO.isCardExit(number, password);
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
	 * @author  徐云凯
	 */
	
	public void addCard() {
		Scanner scanner = new Scanner(System.in);
		
		MobileCard newCard = new MobileCard();
		int numIndex, numberCount = 9;
		
		System.out.println("*****可选的卡号*****");
		String[] numbers = getNewNumbers(numberCount);
		int cntLine = 0;    //该变量用于计数卡号数量，决定是否换行
		for (int i = 0; i < numbers.length; i++, cntLine++) {
			if (cntLine != 0) {
				System.out.print("\t");
			}
			System.out.print(i + 1 + "." + numbers[i]);
			if (cntLine == 2) {
				cntLine = - 1;
				System.out.println();
			}
		}
		do {
			System.out.print("请选择卡号(输入1~9的序号)：");
			try {
				numIndex = scanner.nextInt();
			}
			catch (InputMismatchException e) {
				scanner = new Scanner(System.in);
				numIndex = - 1;
			}
		} while (numIndex < 1 || numIndex > numberCount);    //循环检查输入是否合法
		newCard.cardNumber = numbers[numIndex - 1];
		
		ServicePackage[] servicePackages = sosoDAO.findPackage();
		
		int ind;
		do {
			System.out.println("请选择套餐(输入序号)：");
			ind = 1;
			for (ServicePackage i : servicePackages) {
				if (i != null) {
					System.out.println("\t" + ind++ + "\t" + i.name);
				}
			}
			numIndex = scanner.nextInt();
		} while (numIndex < 0 || numIndex > ind);
		
		newCard.serPackage = servicePackages[numIndex - 1];
		
		System.out.print("请输入姓名：");
		newCard.userName = scanner.next();
		
		System.out.print("请输入密码：");
		newCard.password = scanner.next();
		
		double preMoney = - 1;
		boolean tempFlag = true;
		do {
			System.out.print("请输入预存话费金额：");
			if (! tempFlag) {
				System.out.print("您预存的话费金额不足以支付本月固定套餐资费，请重新充值：");
			}
			try {
				preMoney = scanner.nextDouble();
			}
			catch (InputMismatchException e) {
				scanner = new Scanner(System.in);
				System.out.println("请输入正确的数字");
			}
			tempFlag = ! (preMoney < newCard.serPackage.price) || ! (preMoney > 0);
		} while (preMoney <= 0 || preMoney < newCard.serPackage.price);    //循环检查输入是否合法
		
		//直接扣除首月套餐费再存入
		newCard.money = preMoney - newCard.serPackage.price;
		
		sosoDAO.save(newCard);
		
		System.out.print("注册成功！");
		newCard.showMeg();
		newCard.serPackage.showInfo();
	}
	
	/***
	 *  delCard
	 * [从系统中删除电话卡]
	 * @param card: 需要删除的电话卡卡号
	 * @author  徐云凯
	 */
	
	public void delCard(String card) {
		sosoDAO.del(card);
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
		MobileCard c = sosoDAO.findCardByNumber(number);
		ServicePackage tempServerPackage = sosoDAO.findPackage(c.serPackage.type);
		System.out.print("通话时长：" + Math.max(tempServerPackage.getTalkTime() - c.realTalkTime, 0) + "分钟\n"
				+ "短信条数：" + Math.max(tempServerPackage.getSmsCount(), 0) + "条\n"
				+ "上网流量：" + formatData.format(Math.max(tempServerPackage.getFlow() - c.realFlow, 0)) + "GB\n");
	}
	
	/***
	 *  showAmountDetail
	 * [显示当月账单]
	 * @param number: 需要查询的卡号
	 * @author  徐云凯
	 */
	
	public void showAmountDetail(String number) {
		DecimalFormat formatData = new DecimalFormat("#.0");
		
		MobileCard c = sosoDAO.findCardByNumber(number);
		
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
		ConsumInfo[] list = sosoDAO.findRecByNumber(number);
		int ind = 1;
		for (ConsumInfo i : list) {
			if (i != null) {
				content.append(ind++).append(".\t").append(i.type).append("\t").append(i.consumData).append("\n");
			}
		}
		System.out.print(content);
		
		try {
			File file = new File(number + " 消费记录.txt");
			if (file.createNewFile()) {
				FileWriter writer = new FileWriter(file);
				writer.write(content.toString());
				writer.flush();
				writer.close();
			}
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
	 * @author  徐云凯
	 */
	
	public void useSoso() {
		Scanner scanner = new Scanner(System.in);
		
		String number;
		do {
			System.out.print("请输入手机卡号：");
			number = scanner.next();
			if (! isExistCard(number)) {
				System.out.println("手机卡号不存在，请重试");
			}
			else {
				break;
			}
		} while (true);
		
		Random random = new Random();
		int i;
		MobileCard c = sosoDAO.findCardByNumber(number);
		Scene scene;
		ConsumInfo info;
		boolean flag = false;
		
		scene = scenes.get(random.nextInt(6));
		
		try {
			if (scene.type.equals("通话")) {
				(c.serPackage).call(scene.data, c);
			}
			else if (scene.type.equals("短信")) {
				(c.serPackage).send(scene.data, c);
			}
			else {
				(c.serPackage).netPlay(scene.data, c);
			}
		} catch (InsufficientBalanceException e) {
			System.out.println("本次已" + e.done() + "，您的余额不足，请充值后再使用！");
			e.printStackTrace();
		} finally {
			System.out.print(scene.description + scene.type + scene.data);
			if (scene.type.equals("通话")) System.out.println("分钟");
			else if (scene.type.equals("上网")) System.out.println("MB");
			else System.out.println("条");
		}
		
		if (sosoDAO.findRecByNumber(number) == null) {
			System.out.print("不存在此卡的消费记录，");
		}
		info = new ConsumInfo(number, scene.type, scene.data);
		addConsumInfo(number, info);
		System.out.println("已添加一条消费记录。");
	}
	
	private void addConsumInfo(String number, ConsumInfo info) {
		if (info != null) {
			sosoDAO.save(info);
		}
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
	 * @author  徐云凯
	 */
	
	public void changingPack(String number) {
		
		Scanner scanner = new Scanner(System.in);
		
		ServicePackage[] servicePackages = sosoDAO.findPackage();
		
		System.out.println("******套餐变更******");
		String numIndex;
		int nIndex;
		int ind;
		do {
			ind = 1;
			System.out.println("请选择(序号)：");
			for (ServicePackage i : servicePackages)
				if (i != null) {
					System.out.println("\t" + ind++ + "\t" + i.name);
				}
			numIndex = scanner.next();
			nIndex = Integer.parseInt(numIndex);
		} while (nIndex < 0 || nIndex > ind);
		
		MobileCard c = sosoDAO.findCardByNumber(number);
		
		//检查更换套餐是否发生了变更
		if (servicePackages[nIndex - 1].type.equals(c.serPackage.type)) {
			System.out.println("对不起，您已经是该套餐用户，无需更换套餐！");
			return;
		}
		
		//检查余额
		ServicePackage toSp = servicePackages[nIndex - 1];
		
		if (c.money < toSp.price) {
			System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！");
			return;
		}
		
		//扣除套餐费用，清空使用时间，更换套餐
		c.money -= toSp.price;
		c.realTalkTime = c.realSMSCount = c.realFlow = 0;
		c.serPackage = toSp;
		sosoDAO.update(c);
		System.out.println("套餐更换成功");
		
		//输出相关信息
		c.showMeg();
		c.serPackage.showInfo();
	}
	
	/***
	 *  chargeMoney
	 * 账户充值，充值金额低于50元报错，完成后显示最新余额
	 * @author  徐云凯
	 */
	
	public void chargeMoney() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("请输入充值卡号：");
		String number = scanner.next();
		
		if (isExistCard(number)) {
			double money = - 1;
			do {
				System.out.print("请输入充值金额：");
				try {
					money = scanner.nextDouble();
				}
				catch (InputMismatchException e) {
					scanner = new Scanner(System.in);
					System.out.println("请输入正确的数字");
				}
			} while (money <= 0);    //循环检查输入是否合法
			
			if (money < 50) {
				System.out.println("充值金额至少为50元");
			}
			else {
				DecimalFormat formatData = new DecimalFormat("#.0");    //用于控制精度
				MobileCard mobileCard = sosoDAO.findCardByNumber(number);
				mobileCard.money += money;
				sosoDAO.update(mobileCard);
				System.out.println(String.format("充值成功，当前话费余额为%s元。", formatData.format(mobileCard.money)));
			}
		}
		else {
			System.out.println("充值卡号不存在，请重试");
		}
	}
}
