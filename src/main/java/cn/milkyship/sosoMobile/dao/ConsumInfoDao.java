package cn.milkyship.sosoMobile.dao;

import cn.milkyship.sosoMobile.model.ConsumInfo;

public interface ConsumInfoDao {
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
	 * @return cn.milkyship.sosoMobile.model.ConsumInfo
	 * @author 徐云凯
	 * Datetime:  2019/11/13 22:37
	 */
	ConsumInfo[] findRecByNumber(String Number);
	
}
