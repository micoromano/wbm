package it.mcsolution.wbm.spi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.mcsolution.wbm.api.WbmMenuApi;
import it.mcsolution.wbm.dao.MenuItemObjDao;
import it.mcsolution.wbm.dao.MenuListDao;
import it.mcsolution.wbm.dao.UserDao;
import it.mcsolution.wbm.entity.MenuItemObj;
import it.mcsolution.wbm.entity.MenuList;
import it.mcsolution.wbm.entity.User;

public class WbmMenuBean implements WbmMenuApi {
	
	@Autowired
	MenuItemObjDao menuItemRepository;
	@Autowired
	MenuListDao MenuListDaoRepository;
	@Autowired
	UserDao UserDaoDaoRepository;
	
	@Override
	public List<MenuItemObj> getListMenu(String menuId) {
	
	List<MenuList> menuList  =  MenuListDaoRepository.getMenu(menuId);
	List<MenuItemObj> list = new ArrayList<MenuItemObj>();
	for(MenuList objMenu : menuList){
		list.add(menuItemRepository.getMenu(""+objMenu.getItemListId()));
	}
	return list;
	
	}

	@Override
	public User getUser(String user, String password) {
		return UserDaoDaoRepository.getUser(user, password);
	}

}
