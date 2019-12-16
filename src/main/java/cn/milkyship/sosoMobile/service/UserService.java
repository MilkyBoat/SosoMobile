package cn.milkyship.sosoMobile.service;

import cn.milkyship.sosoMobile.model.ServicePackage;

import java.util.List;

public interface UserService {
	
	public String useSoso(String number);
	
	public String charge(String number, double money);
	
	public String reg(String number, String userName, String password, String servicePackage, Double preMoney);
	
	public List<String> getNewNumbers(int numberCount);
	
	public ServicePackage[] getServicePackages();
}
