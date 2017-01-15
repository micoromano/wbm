package it.mcsolution.wbm.api;

import java.util.List;

import it.mcsolution.wbm.entity.MenuItemObj;
import it.mcsolution.wbm.entity.User;

public interface WbmMenuApi {
	
	public List<MenuItemObj> getListMenu(String menuId) ;
	public User getUser(String user,String Password) ;

}
