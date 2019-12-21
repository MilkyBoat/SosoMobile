package cn.milkyship.sosoMobile.model;

/**
 * <H2>文件名称: ConsumInfo.java </H2>
 * <p>描述: [用户信息类，用于用户消费信息] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 * <p><b>成员变量：</b></p>
 * <ul>
 *     <li>public String cardNumber</li>
 *     <li>public String type</li>
 *     <li>public int consumData</li>
 * </ul>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>public ConsumInfo(String cardNumber, String type, int consumData)</li>
 * </ul>
 *
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class ConsumInfo {
	
	private String cardNumber;
	private String recType;
	public int consumData;
	
	public ConsumInfo() {
	}
	
	public ConsumInfo(String cardNumber, String recType, int consumData) {
		this.cardNumber = cardNumber;
		this.recType = recType;
		this.consumData = consumData;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getRecType() {
		return recType;
	}
	
	public void setRecType(String recType) {
		this.recType = recType;
	}
	
	public int getConsumData() {
		return consumData;
	}
	
	public void setConsumData(int consumData) {
		this.consumData = consumData;
	}
}
