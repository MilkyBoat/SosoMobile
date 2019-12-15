package cn.milkyship.sosoMobile.service;

import cn.milkyship.sosoMobile.model.User;


public interface IUserService {
	
	User selectUser(long userId);
	
	int insert(User user);
	
}