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

abstract public class ServicePackage {

    protected double price;
    
    /**
     * <H2>public void showInfo()</H2>
     * 输出套餐信息
     */
    
    abstract public void showInfo();

}
