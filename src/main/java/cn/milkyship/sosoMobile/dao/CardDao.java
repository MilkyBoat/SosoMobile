package cn.milkyship.sosoMobile.dao;

import cn.milkyship.sosoMobile.model.MobileCard;

public interface CardDao {
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
	 * @return cn.milkyship.sosoMobile.model.MobileCard
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:36
	 */
	MobileCard findCardByNumber(String Number);
	
}
