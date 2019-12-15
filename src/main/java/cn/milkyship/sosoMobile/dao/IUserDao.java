package cn.milkyship.sosoMobile.dao;

import cn.milkyship.sosoMobile.model.User;

public interface IUserDao {
	
	User selectUser(long id);
	
}