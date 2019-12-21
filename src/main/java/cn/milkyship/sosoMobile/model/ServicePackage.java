package cn.milkyship.sosoMobile.model;

/**
 * <H2>文件名称: ServicePackage.java </H2>
 * <p>描述: [所有通信套餐的实体类] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 *
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class ServicePackage {
	
	private String name = null;
	
	private int talkTime = 0;
	private int smsCount = 0;
	private int flow = 0;
	private double price = 0;
	
	public ServicePackage() {
	}
	
	public ServicePackage(String name, double price, int talkTime, int smsCount, int flow) {
		this.name = name;
		this.price = price;
		this.talkTime = talkTime;
		this.smsCount = smsCount;
		this.flow = flow;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTalkTime() {
		return talkTime;
	}
	
	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}
	
	public int getSmsCount() {
		return smsCount;
	}
	
	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}
	
	public int getFlow() {
		return flow;
	}
	
	public void setFlow(int flow) {
		this.flow = flow;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
}
