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
	
	/***
	 * Title: ServicePackage
	 * Description: [带参构造]
	 * @param name:
	 * @param price:
	 * @param talkTime:
	 * @param smsCount:
	 * @param flow:
	 * @author 徐云凯
	 * Datetime:  2019/11/16 21:16
	 */
	
	public ServicePackage(String name, double price, int talkTime, int smsCount, int flow) {
		this.name = name;
		this.price = price;
		this.talkTime = talkTime;
		this.smsCount = smsCount;
		this.flow = flow;
	}
	
	/**
	 * <H3>int call(int minCount, MobileCard card) throws InsufficientBalanceException</H3>
	 * 通话功能实现，用于计算实际可消费额度并扣除对应套餐余量或账户余额
	 *
	 * @param minCount: 消费数额
	 * @param card:     电话卡实体类
	 * @return int 实际消费额度
	 */
	public ConsumInfo call(int minCount, MobileCard card) {
		
		if (card.realTalkTime + minCount >= talkTime) {
			int temp = 0;
			if (card.realTalkTime < talkTime) {
				temp += talkTime - card.realTalkTime;
				minCount -= talkTime - card.realTalkTime;
				card.realTalkTime = talkTime;
			}
			if (card.money >= minCount * 0.2) {
				card.realTalkTime += minCount;
				card.money -= minCount * 0.2;
				temp += minCount;
				return new ConsumInfo(card.cardNumber, "通话", temp);
			}
			else {
				temp += (int) Math.round(card.money / 0.2);
				card.realTalkTime += temp;
				card.money = 0;
				return new ConsumInfo(card.cardNumber, "通话", temp);
			}
		}
		else {
			card.realTalkTime += minCount;
			return new ConsumInfo(card.cardNumber, "通话", minCount);
		}
	}
	
	/**
	 * <H3>int netPlay(int flow, MobileCard card) throws InsufficientBalanceException</H3>
	 * 上网功能实现，用于计算实际可消费额度并扣除对应套餐余量或账户余额
	 *
	 * @param flow 消费额度
	 * @param card 用户电话卡实体类
	 * @return int 实际消费额度
	 */
	
	public ConsumInfo netPlay(int flow, MobileCard card) {
		
		if (card.realFlow + flow >= this.flow * 1024) {
			int temp = 0;
			if (card.realFlow < this.flow * 1024) {
				temp += this.flow * 1024 - card.realFlow;
				flow -= this.flow * 1024 - card.realFlow;
				card.realFlow = this.flow;
			}
			if (card.money >= flow * 0.1) {
				card.realFlow += flow;
				card.money -= flow * 0.1;
				temp += flow;
				return new ConsumInfo(card.cardNumber, "上网", temp);
			}
			else {
				temp += (int) Math.round(card.money / 0.1);
				card.realFlow += temp;
				card.money = 0;
				return new ConsumInfo(card.cardNumber, "上网", temp);
			}
		}
		else {
			card.realFlow += flow;
			return new ConsumInfo(card.cardNumber, "上网", flow);
		}
	}
	
	/**
	 * <H3>int send(int count, MobileCard card) throws InsufficientBalanceException</H3>
	 * 短信功能实现，用于计算实际可消费额度并扣除对应套餐余量或账户余额
	 *
	 * @param count 消费额度
	 * @param card  用户电话卡实体类
	 * @return int 实际消费额度
	 */
	
	public ConsumInfo send(int count, MobileCard card) {
		
		if (card.realSMSCount + count >= smsCount) {
			int temp = 0;
			if (card.realSMSCount < smsCount) {
				temp += smsCount - card.realSMSCount;
				count -= smsCount - card.realSMSCount;
				card.realSMSCount = smsCount;
			}
			if (card.money >= count * 0.1) {
				card.realSMSCount += count;
				card.money -= count * 0.1;
				temp += count;
				return new ConsumInfo(card.cardNumber, "短信", temp);
			}
			else {
				temp += (int) Math.round(card.money / 0.1);
				card.realSMSCount += temp;
				card.money = 0;
				return new ConsumInfo(card.cardNumber, "短信", temp);
			}
		}
		else {
			card.realSMSCount += count;
			return new ConsumInfo(card.cardNumber, "短信", count);
		}
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
