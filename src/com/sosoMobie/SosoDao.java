package com.sosoMobie;

/**
 * <p>项目名称: SosoMobie </p>
 * <p>文件名称: SosoDao.java </p>
 * <p>描述: DAO接口 </p>
 * <p>创建时间:  2019.11.13</p>
 *
 * @author xuyunkai@mail.nankai.edu.cn 徐云凯
 * @version v1.0
 */

public interface SosoDao {
	
	/**
	 * Title: save
	 * Description: [将电话卡信息存入数据库]
	 *
	 * @param mobileCard:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:34
	 */
	void save(MobileCard mobileCard);
	
	/***
	 * Title: del
	 * Description: [从数据库删除电话卡信息]
	 * @param number:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:35
	 */
	void del(String number);
	
	/***
	 * Title: update
	 * Description: [更改数据库中的某一个电话卡信息]
	 * @param mobileCard:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:35
	 */
	void update(MobileCard mobileCard);
	
	/***
	 * Title: findCardByNumber
	 * Description: [由卡号查找电话卡]
	 * @param Number:
	 * @return com.sosoMobie.MobileCard
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:36
	 */
	MobileCard findCardByNumber(String Number);
	
	/***
	 * Title: isCardExit
	 * Description: [由号码判断电话卡存在]
	 * @param number:
	 * @return boolean:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:36
	 */
	boolean isCardExit(String number);
	
	/***
	 * Title: isCardExit
	 * Description: [由号码与密码判断电话卡存在]
	 * @param number:
	 * @param password:
	 * @return boolean:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:36
	 */
	boolean isCardExit(String number, String password);
	
	/***
	 * Title: save
	 * Description: [向数据库中存入通话记录]
	 * @param consumInfo:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:36
	 */
	void save(ConsumInfo consumInfo);
	
	/***
	 * Title: del
	 * Description: [从数据库中删除通话记录]
	 * @param consumInfo:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:37
	 */
	void del(ConsumInfo consumInfo);
	
	/***
	 * Title: update
	 * Description: [更改数据库中已经存在的通话记录]
	 * @param consumInfo:
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:37
	 */
	void update(ConsumInfo consumInfo);
	
	/***
	 * Title: findRecByNumber
	 * Description: [由卡号查找通话记录]
	 * @param Number:
	 * @return com.sosoMobie.ConsumInfo
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:37
	 */
	ConsumInfo[] findRecByNumber(String Number);
	
	/***
	 * Title: findPackage
	 * Description: [由套餐名称查找套餐]
	 * @param name:
	 * @return com.sosoMobie.ServicePackage
	 * @author 徐云凯
	 * Datetime:  2019/11/16 20:39
	 */
	ServicePackage findPackage(String name);
	
	/***
	 * Title: findPackage
	 * Description: [查找全部套餐]
	 * @return com.sosoMobie.ServicePackage
	 * @author 徐云凯
	 * Datetime:  2019/11/17 10:22
	 */
	
	ServicePackage[] findPackage();
}
