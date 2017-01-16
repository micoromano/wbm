package it.mcsolution.wbm.api;

import java.util.List;

import it.mcsolution.wbm.entity.MenuItemObj;
import it.mcsolution.wbm.entity.MenuList;
import it.mcsolution.wbm.entity.User;

public interface WbmMenuApi {
	
	public List<MenuItemObj> getListMenu(String menuId) ;
	public User getUser(String user,String Password) ;
	public boolean saveMenuObj(MenuItemObj obj);
	public boolean saveListObj(MenuList obj);
	public void deleteMenuObj(MenuItemObj obj);
	public void deleteListObj(MenuList obj);
	public boolean ListObj(MenuItemObj obj);
	public void deleteAllListObj();
	public void deleteAllMenuObj();
	public MenuItemObj findItemById(Integer id);
	public MenuList findItemListById(Integer id);
	public List<MenuItemObj> getAllItems();
}
