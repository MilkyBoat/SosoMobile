package com.sosoMobie;

/**
 * <H2>文件名称: TalkPackage.java </H2>
 * <p>描述: [通话套餐类] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 * <p><b>成员变量：</b></p>
 * <ul>
 *     <li>public int talkTime</li>
 *     <li>public int smsCount</li>
 * </ul>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>public TalkPackage()</li>
 *     <li>public void showInfo()</li>
 *     <li>public int call(int minCount, MobileCard card) throws InsufficientBalanceException</li>
 *     <li>public int send(int count, MobileCard card) throws InsufficientBalanceException</li>
 * </ul>
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class TalkPackage extends ServicePackage implements CallService, SendService  {

    public int talkTime;
    public int smsCount;
    
    /**
     * <H2> public TalkPackage() </H2>
     * 通话套餐类的唯一构造函数
     */
    
    public TalkPackage(){
        price = 58;
        talkTime = 500;
        smsCount = 30;
    }
    
    /**
     * {@inheritDoc}
     */
    
    public void showInfo(){
        System.out.println(String.format("话痨套餐：通话时长为%d分钟/月，短信条数为%d条/月，上网流量为%dGB/月",talkTime, smsCount,0));
    }
    
    /**
     * {@inheritDoc}
     */
    
    public int call(int minCount, MobileCard card) throws InsufficientBalanceException{
        if(card.realTalkTime + minCount >= 500){
            int temp = 0;
            if (card.realTalkTime < 500) {
                temp += 500 - card.realTalkTime;
                minCount -= 500 - card.realTalkTime;
                card.realTalkTime = 500;
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
        if(card.realSMSCount + count >= 30){
            int temp = 0;
            if (card.realSMSCount < 30) {
                temp += 30 - card.realSMSCount;
                count -= 30 - card.realSMSCount;
                card.realSMSCount = 30;
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
}
