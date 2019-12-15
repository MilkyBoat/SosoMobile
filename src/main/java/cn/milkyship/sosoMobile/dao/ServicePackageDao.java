package cn.milkyship.sosoMobile.dao;

import cn.milkyship.sosoMobile.model.ServicePackage;

public interface ServicePackageDao {
	/***
	 * Title: findPackage
	 * Description: [由套餐名称查找套餐]
	 * @param name:
	 * @return cn.milkyship.sosoMobile.model.ServicePackage
	 * @author 徐云凯
	 * Datetime:  2019/11/16 20:39
	 */
	ServicePackage findPackage(String name);
	
	/***
	 * Title: findAllPackage
	 * Description: [查找全部套餐]
	 * @return cn.milkyship.sosoMobile.model.ServicePackage
	 * @author 徐云凯
	 * Datetime:  2019/11/17 10:22
	 */
	
	ServicePackage[] findAllPackage();
}
