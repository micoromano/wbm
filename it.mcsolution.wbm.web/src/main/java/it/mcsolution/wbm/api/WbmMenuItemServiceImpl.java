package it.mcsolution.wbm.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.mcsolution.wbm.dao.MenuItemObjDao;
import it.mcsolution.wbm.dao.MenuListDao;
import it.mcsolution.wbm.entity.MenuItemObj;
import it.mcsolution.wbm.entity.MenuList;
import it.mcsolution.wbm.spi.WbmMenuBean;

@Service
public class WbmMenuItemServiceImpl implements WbmMenuItemService{
     
    private static final AtomicLong counter = new AtomicLong();

    private static List<MenuItemObj> menuItemObjs;
    @Resource(name = "configProperties")
	private Properties configProperties;
    @Autowired(required=false)
    private WbmMenuBean menuDao;
  
    private void init() {
    	
    	menuItemObjs  = menuDao.getListMenu(configProperties.getProperty("menuId"));

	}
     
   
    public static List<MenuItemObj> getMenuItemObjs() {
		return menuItemObjs;
	}

	public void setMenuItemObjs(List<MenuItemObj> menuItemObjs) {
		WbmMenuItemServiceImpl.menuItemObjs = menuItemObjs;
	}

	public List<MenuItemObj> findAllItem() {
        return menuItemObjs;
    }
     
    public MenuItemObj findById(long id) {
    	menuItemObjs  = menuDao.getListMenu(configProperties.getProperty("menuId"));
        for(MenuItemObj obj : menuItemObjs){
            if(obj.getId() == Integer.decode(""+id)){
                return obj;
            }
        }
        return null;
    }
     
    public MenuItemObj findByName(String name) {
    	menuItemObjs  = menuDao.getListMenu(configProperties.getProperty("menuId"));
    	for(MenuItemObj obj : menuItemObjs){
            if( obj.getBeer().equalsIgnoreCase(name)){
                return obj;
            }
        }
        return null;
    }
     
    public void saveItem(MenuItemObj menuItemObj) {
    	
    	menuItemObj.setId(Integer.decode(""+counter.incrementAndGet()));
    	menuItemObjs.add(menuItemObj);
    	menuItemObjs  = menuDao.getListMenu(configProperties.getProperty("menuId"));
    	 
    }
 
    public void updateItem(MenuItemObj menuItemObj,MenuItemObj newObj) {
        int index = menuItemObjs.indexOf(menuItemObj);
        menuItemObjs.set(index, newObj);
        menuItemObjs  = menuDao.getListMenu(configProperties.getProperty("menuId"));
       }
 
    public void deleteItemById(long id) {
         
        for (Iterator<MenuItemObj> iterator = menuItemObjs.iterator(); iterator.hasNext(); ) {
        	MenuItemObj menuItemObj = iterator.next();
            if (menuItemObj.getId() == id) {
                iterator.remove();
                
            }
        }
        menuItemObjs  = menuDao.getListMenu(configProperties.getProperty("menuId"));
    }
 
    public boolean isItemExist(MenuItemObj menuItemObj) {
        return findByName(menuItemObj.getBeer())!=null;
    }
     
    public void deleteAllItem(){
    	menuItemObjs.clear();
    }
 
  




 
}
