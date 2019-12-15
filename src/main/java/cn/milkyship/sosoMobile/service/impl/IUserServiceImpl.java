package cn.milkyship.sosoMobile.service.impl;

import cn.milkyship.sosoMobile.dao.IUserDao;
import cn.milkyship.sosoMobile.model.User;
import cn.milkyship.sosoMobile.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service ("UserService")
public class IUserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao userDao;
	
	public User selectUser(long userId) {
		return this.userDao.selectUser(userId);
	}
	
	@Override
	public int insert(User user) {
		return 0;
	}
	
}