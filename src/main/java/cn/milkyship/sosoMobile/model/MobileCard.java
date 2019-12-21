package cn.milkyship.sosoMobile.model;

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
	
	private String cardNumber;
	private String userName;
	private String password;
	private String serPackage;
	private double consumAmount = 0;
	private double money;
	private int realTalkTime = 0;
	private int realSMSCount = 0;
	private int realFlow = 0;
	
	public MobileCard() {
	}
	
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
