package cn.milkyship.sosoMobile.model;

import java.text.DecimalFormat;

/**
 * <H2>文件名称: MobileCard.java </H2>
 * <p>描述: [电话卡类，用于存放被实例化的用户电话卡信息] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 *
 * <p><b>成员变量：</b></p>
 * <ul>
 *     <li>public String cardNumber</li>
 *     <li>public String userName</li>
 *     <li>public String password</li>
 *     <li>public ServicePackage serPackage</li>
 *     <li>public double consumAmount</li>
 *     <li>public double money</li>
 *     <li>public int realTalkTime</li>
 *     <li>public int realSMSCount</li>
 *     <li>public int realFlow</li>
 * </ul>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>public MobileCard()</li>
 *     <li>public MobileCard(String cardNumber, String userName, String password, ServicePackage serPackage, double money)</li>
 *     <li>public void showMeg()</li>
 * </ul>
 *
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class MobileCard {
	
	public String cardNumber;
	public String userName;
	public String password;
	public String serPackage;
	public double consumAmount = 0;
	public double money;
	public int realTalkTime = 0;
	public int realSMSCount = 0;
	public int realFlow = 0;
	
	/**
	 * MobileCard
	 * [MobileCard类无参构造函数]
	 *
	 * @author 徐云凯
	 */
	
	public MobileCard() {
	}
	
	/**
	 * MobileCard
	 * [MobileCard类有参构造函数]
	 *
	 * @param cardNumber:卡号，String
	 * @param userName:用户名，String
	 * @param password:密码，String
	 * @param serPackage:卡使用的套餐名称，ServicePackage
	 * @param money:预存金额，double
	 * @author 徐云凯
	 */
	
	public MobileCard(String cardNumber, String userName, String password, String serPackage, double money) {
		this.cardNumber = cardNumber;
		this.userName = userName;
		this.password = password;
		this.serPackage = serPackage;
		this.money = money;
		this.consumAmount = 0;
		this.realTalkTime = 0;
		this.realSMSCount = 0;
		this.realFlow = 0;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getSerPackage() {
		return serPackage;
	}
	
	public double getConsumAmount() {
		return consumAmount;
	}
	
	public double getMoney() {
		return money;
	}
	
	public int getRealTalkTime() {
		return realTalkTime;
	}
	
	public int getRealSMSCount() {
		return realSMSCount;
	}
	
	public int getRealFlow() {
		return realFlow;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setSerPackage(String serPackage) {
		this.serPackage = serPackage;
	}
	
	public void setConsumAmount(double consumAmount) {
		this.consumAmount = consumAmount;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public void setRealTalkTime(int realTalkTime) {
		this.realTalkTime = realTalkTime;
	}
	
	public void setRealSMSCount(int realSMSCount) {
		this.realSMSCount = realSMSCount;
	}
	
	public void setRealFlow(int realFlow) {
		this.realFlow = realFlow;
	}
}