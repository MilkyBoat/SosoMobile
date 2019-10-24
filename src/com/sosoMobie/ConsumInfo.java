package com.sosoMobie;

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
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class ConsumInfo {

    public String cardNumber;
    public String type;
    public int consumData;
    
    /**
     * <H2> public ConsumInfo(String cardNumber, String type, int consumData) </H2>
     * 用户信息类的唯一构造函数
     * @param cardNumber 卡号，String
     * @param type 消费类型， String
     * @param consumData 消费数额， int
     */
    
    public ConsumInfo(String cardNumber, String type, int consumData) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.consumData = consumData;
    }
}
