package it.mcsolution.wbm.spi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.mcsolution.wbm.api.WbmMenuApi;
import it.mcsolution.wbm.dao.MenuItemObjDao;
import it.mcsolution.wbm.dao.MenuListDao;
import it.mcsolution.wbm.dao.UserDao;
import it.mcsolution.wbm.entity.MenuItemObj;
import it.mcsolution.wbm.entity.MenuList;
import it.mcsolution.wbm.entity.User;

@Service
@Component
public class WbmMenuBean implements WbmMenuApi {
	
	@Autowired
	MenuItemObjDao menuItemObjDao;
	@Autowired
	MenuListDao menuListDao;
	@Autowired
	UserDao userDao;
	
	@Override
	public List<MenuItemObj> getListMenu(String menuId) {
	
	List<MenuList> menuList  =  menuListDao.getMenu(Integer.decode(menuId));
	List<MenuItemObj> list = new ArrayList<MenuItemObj>();
	for(MenuList objMenu : menuList){
		list.add(menuItemObjDao.getMenu(objMenu.getItemListId()));
	}
	return list;
	
	}

	@Override
	public User getUser(String user, String password) {
		return userDao.getUser(user, password);
	}

	@Override
	public boolean saveMenuObj(MenuItemObj obj) {
		MenuItemObj objRet = menuItemObjDao.save(obj);
		return objRet!=null;
	}

	@Override
	public boolean saveListObj(MenuList obj) {
		MenuList objRet = menuListDao.save(obj);
		return objRet!=null;
	}

	@Override
	public void deleteMenuObj(MenuItemObj obj) {
		menuItemObjDao.delete(obj);
	}

	@Override
	public boolean ListObj(MenuItemObj obj) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public void deleteListObj(MenuList obj) {
		menuListDao.delete(obj);	
	}

	@Override
	public void deleteAllListObj() {
		menuListDao.deleteAll();
		
	}

	@Override
	public void deleteAllMenuObj() {
		menuListDao.deleteAll();
		
	}

	@Override
	public MenuItemObj findItemById(Integer id) {
		return menuItemObjDao.getMenu(id);
	}

	@Override
	public MenuList findItemListById(Integer id) {
		return menuListDao.getMenuListIdItem(id);
	}

	@Override
	public List<MenuItemObj> getAllItems() {
		return (List<MenuItemObj>) menuItemObjDao.findAll();
	}

}
