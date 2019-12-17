package cn.milkyship.sosoMobile.service;

import cn.milkyship.sosoMobile.model.MobileCard;

import java.util.List;

public interface CardService {
	
	public int login(String number, String password);
	
	public String bill(String number);
	
	public String remain(String number);
	
	public String consumList(String number);
	
	public String changePack(String number, String toPack);
	
	public void del(String number);
}
