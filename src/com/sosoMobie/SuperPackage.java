package com.sosoMobie;

/**
 * <H2>文件名称: SuperPackage.java </H2>
 * <p>描述: [超级套餐类] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 * <p><b>成员变量：</b></p>
 * <ul>
 *     <li>public int talkTime</li>
 *     <li>public int smsCount</li>
 *     <li>public int flow</li>
 * </ul>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>public SuperPackage()</li>
 *     <li>public void showInfo()</li>
 *     <li>public int call(int minCount, MobileCard card) throws InsufficientBalanceException</li>
 *     <li>public int send(int count, MobileCard card) throws InsufficientBalanceException</li>
 *     <li>public int netPlay(int flow, MobileCard card) throws InsufficientBalanceException</li>
 * </ul>
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class SuperPackage extends ServicePackage implements CallService, SendService, NetService {

    public int talkTime;
    public int smsCount;
    public int flow;
    
    /**
     * <H2> public SuperPackage() </H2>
     * 超级套餐类的唯一构造函数
     */
    
    public SuperPackage() {
        price = 78;
        talkTime = 300;
        smsCount = 50;
        flow = 1;
    }
    
    /**
     * {@inheritDoc}
     */
    
    public void showInfo(){
        System.out.println(String.format("超人套餐：通话时长为%d分钟/月，短信条数为%d条/月，上网流量为%dGB/月",talkTime, smsCount,flow));
    }
    
    /**
     * {@inheritDoc}
     */
    
    public int call(int minCount, MobileCard card) throws InsufficientBalanceException{
        if(card.realTalkTime + minCount >= talkTime){
            int temp = 0;
            if (card.realTalkTime < talkTime) {
                temp += talkTime - card.realTalkTime;
                minCount -= talkTime - card.realTalkTime;
                card.realTalkTime = talkTime;
            }
            if(card.money >= minCount * 0.2){
                card.realTalkTime += minCount;
                card.money -= minCount * 0.2;
                temp += minCount;
                return temp;
            }
            else {
                temp += (int)Math.round(card.money / 0.2);
                card.realTalkTime += temp;
                card.money = 0;
                throw new InsufficientBalanceException("通话" + temp + "分钟");
            }
        }
        else{
            card.realTalkTime += minCount;
            return minCount;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    
    public int send(int count, MobileCard card) throws InsufficientBalanceException{
        if(card.realSMSCount + count >= smsCount){
            int temp = 0;
            if (card.realSMSCount < smsCount) {
                temp += smsCount - card.realSMSCount;
                count -= smsCount - card.realSMSCount;
                card.realSMSCount = smsCount;
            }
            if(card.money >= count * 0.1){
                card.realSMSCount += count;
                card.money -= count * 0.1;
                temp += count;
                return temp;
            }
            else {
                temp += (int)Math.round(card.money / 0.1);
                card.realSMSCount += temp;
                card.money = 0;
                throw new InsufficientBalanceException("短信" + temp + "条");
            }
        }
        else{
            card.realSMSCount += count;
            return count;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    
    public int netPlay(int flow, MobileCard card) throws InsufficientBalanceException{
        if(card.realFlow + flow >= this.flow * 1024){
            int temp = 0;
            if (card.realFlow  < this.flow * 1024) {
                temp += this.flow * 1024 - card.realFlow;
                flow -= this.flow * 1024 - card.realFlow;
                card.realFlow = this.flow;
            }
            if(card.money >= flow * 0.1){
                card.realFlow += flow;
                card.money -= flow * 0.1;
                temp += flow;
                return temp;
            }
            else {
                temp += (int)Math.round(card.money / 0.1);
                card.realFlow += temp;
                card.money = 0;
                throw new InsufficientBalanceException("上网" + temp + "MB");
            }
        }
        else{
            card.realFlow += flow;
            return flow;
        }
    }
}
