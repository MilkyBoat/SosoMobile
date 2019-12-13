package com.sosoMobie;

/**
 * <H2>文件名称: ServicePackage.java </H2>
 * <p>描述: [所有通信套餐的抽象基类] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 * <p><b>成员变量：</b></p>
 * <ul>
 *     <li>protected double price</li>
 * </ul>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>abstract public void showInfo()</li>
 * </ul>
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class ServicePackage {
    
    public String name = null;
    public String type = null;
    
    private int talkTime = 0;
    private int smsCount = 0;
    private int flow = 0;
    double price = 0;
    
    public ServicePackage() {
    }
    
    /***
     * Title: ServicePackage
     * Description: [带参构造]
     * @param type:
     * @param price:
     * @param talkTime:
     * @param smsCount:
     * @param flow:
     * @author 徐云凯
     * Datetime:  2019/11/16 21:16
     */
    
    public ServicePackage(String type, double price, int talkTime, int smsCount, int flow) {
        this.type = type;
        this.price = price;
        this.talkTime = talkTime;
        this.smsCount = smsCount;
        this.flow = flow;
    }
    /**
     * <H2>public void showInfo()</H2>
     * 输出套餐信息
     */

    public void showInfo() {
        System.out.println(String.format("网虫套餐：通话时长为%d分钟/月，短信条数为%d条/月，上网流量为%dGB/月", talkTime, smsCount, flow));
    }
    
    public int getTalkTime() {
        return talkTime;
    }
    
    public int getSmsCount() {
        return smsCount;
    }
    
    public int getFlow() {
        return flow;
    }
    
    /**
     * <H3>int call(int minCount, MobileCard card) throws InsufficientBalanceException</H3>
     * 通话功能实现，用于计算实际可消费额度并扣除对应套餐余量或账户余额
     *
     * @param minCount: 消费数额
     * @param card:     电话卡实体类
     * @return int 实际消费额度
     * @throws InsufficientBalanceException 用户余额不足异常
     */
    public int call(int minCount, MobileCard card) throws InsufficientBalanceException {
        
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
                return temp;
            }
            else {
                temp += (int) Math.round(card.money / 0.2);
                card.realTalkTime += temp;
                card.money = 0;
                throw new InsufficientBalanceException("通话" + temp + "分钟");
            }
        }
        else {
            card.realTalkTime += minCount;
            return minCount;
        }
    }
    
    /**
     * <H3>int netPlay(int flow, MobileCard card) throws InsufficientBalanceException</H3>
     * 上网功能实现，用于计算实际可消费额度并扣除对应套餐余量或账户余额
     *
     * @param flow 消费额度
     * @param card 用户电话卡实体类
     * @return int 实际消费额度
     * @throws InsufficientBalanceException 用户余额不足异常
     */
    
    public int netPlay(int flow, MobileCard card) throws InsufficientBalanceException {
        
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
                return temp;
            }
            else {
                temp += (int) Math.round(card.money / 0.1);
                card.realFlow += temp;
                card.money = 0;
                throw new InsufficientBalanceException("上网" + temp + "MB");
            }
        }
        else {
            card.realFlow += flow;
            return flow;
        }
    }
    
    /**
     * <H3>int send(int count, MobileCard card) throws InsufficientBalanceException</H3>
     * 短信功能实现，用于计算实际可消费额度并扣除对应套餐余量或账户余额
     *
     * @param count 消费额度
     * @param card  用户电话卡实体类
     * @return int 实际消费额度
     * @throws InsufficientBalanceException 用户余额不足异常
     */
    
    public int send(int count, MobileCard card) throws InsufficientBalanceException {
        
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
                return temp;
            }
            else {
                temp += (int) Math.round(card.money / 0.1);
                card.realSMSCount += temp;
                card.money = 0;
                throw new InsufficientBalanceException("短信" + temp + "条");
            }
        }
        else {
            card.realSMSCount += count;
            return count;
        }
    }
    
}
