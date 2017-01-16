package it.mcsolution.wbm.api;

import java.util.List;

import it.mcsolution.wbm.entity.MenuItemObj;

public interface WbmMenuItemService {
    
	MenuItemObj findById(long id);
     
	MenuItemObj findByName(String name);
     
    void saveItem(MenuItemObj MenuItemObj);
     
    void deleteItemById(long id);
 
    List<MenuItemObj> findAllItem(); 
     
    void deleteAllItem();
     
    public boolean isItemExist(MenuItemObj MenuItemObj);
    
    void updateItem(MenuItemObj menuItemObj,MenuItemObj newObj);
     
}