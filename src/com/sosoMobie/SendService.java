package com.sosoMobie;

/**
 * <H2>文件名称: SendService.java </H2>
 * <p>描述: [短信功能接口] </p>
 * <p>创建时间: 2019.9.23 </p><br>
 * <p><b>成员方法：</b></p>
 * <ul>
 *     <li>int send(int count, MobileCard card) throws InsufficientBalanceException</li>
 * </ul>
 * @author <a href="xuyunkai@mail.nankai.edu.cn"> 徐云凯 </a>
 * @version v1.0
 */

public interface SendService {
    
    /**
     * <H3>int send(int count, MobileCard card) throws InsufficientBalanceException</H3>
     * 短信功能实现，用于计算实际可消费额度并扣除对应套餐余量或账户余额
     * @param count 消费额度
     * @param card 用户电话卡实体类
     * @return int 实际消费额度
     * @throws InsufficientBalanceException 用户余额不足异常
     */
    
    int send(int count, MobileCard card) throws InsufficientBalanceException;

}
