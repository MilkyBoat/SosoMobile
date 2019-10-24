package com.sosoMobie;

import java.text.DecimalFormat;

/**
 * <H2>文件名称: MobileCard.java </H2>
 * <p>描述: [电话卡类，用于存放被实例化的用户电话卡信息] </p>
 * <p>创建时间: 2019.9.23 </p><br>
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
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class MobileCard {

    public String cardNumber;
    public String userName;
    public String password;
    public ServicePackage serPackage;
    public double consumAmount;
    public double money;
    public int realTalkTime;
    public int realSMSCount;
    public int realFlow;

    /**
     *  MobileCard
     * [MobileCard类无参构造函数]
     * @author  徐云凯
     */
    
    public MobileCard() {
    }
    
    /**
     *  MobileCard
     * [MobileCard类有参构造函数]
     * @param cardNumber:卡号，String
     * @param userName:用户名，String
     * @param password:密码，String
     * @param serPackage:卡使用的套餐，ServicePackage
     * @param money:预存金额，double
     * @author  徐云凯
     */
    
    public MobileCard(String cardNumber, String userName, String password, ServicePackage serPackage, double money) {
        this.cardNumber = cardNumber;
        this.userName = userName;
        this.password = password;
        this.serPackage = serPackage;
        this.consumAmount = this.serPackage.price;
        this.money = money;
    }

    /**
     *  showMeg
     * [输出电话卡基础信息及余额]
     * @author  徐云凯
     */
    
    public void showMeg(){
        DecimalFormat formatData = new DecimalFormat("#.0");
        System.out.println("卡号：" + cardNumber + " 用户名：" + userName + " 当前余额" + formatData.format(money));
    }
}
