package com.sosoMobie;

/**
 * <H2>文件名称: NetPackage.java </H2>
 * <p>描述: [上网套餐类] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 * <p><b>成员变量：</b></p>
 * <ul>
 *     <li>public int flow</li>
 * </ul>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>public NetPackage()</li>
 *     <li>public void showInfo()</li>
 *     <li>public int netPlay(int flow, MobileCard card) throws InsufficientBalanceException</li>
 * </ul>
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public class NetPackage extends ServicePackage implements NetService {

    public int flow;
    
    /**
     * <H2> public NetPackage() </H2>
     * 上网套餐类的唯一构造函数
     */
    
    public NetPackage() {
        price = 68;
        flow = 3;
    }
    
    /**
     * {@inheritDoc}
     */
    
    public void showInfo(){
        System.out.println(String.format("网虫套餐：通话时长为%d分钟/月，短信条数为%d条/月，上网流量为%dGB/月",0, 0, flow));
    }
    
    /**
     * {@inheritDoc}
     */
    
    public int netPlay(int flow, MobileCard card) throws InsufficientBalanceException{
        if(card.realFlow + flow >= 3072){
            int temp = 0;
            if (card.realFlow  < 3072) {
                temp += 3072 - card.realFlow;
                flow -= 3072 - card.realFlow;
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
