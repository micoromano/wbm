package it.mcsolution.wbm.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mcsolution.wbm.dao.MenuItemObjDao;
import it.mcsolution.wbm.dao.MenuListDao;
import it.mcsolution.wbm.entity.MenuItemObj;
import it.mcsolution.wbm.entity.MenuList;

@Service("userService")
public class WbmMenuItemServiceImpl implements WbmMenuItemService{
     
    private static final AtomicLong counter = new AtomicLong();
	@Resource(name = "configProperties")
	private static Properties configProperties;
    private static List<MenuItemObj> menuItemObjs;
    @Autowired
    private MenuListDao menulistDao;
    @Autowired
    private MenuItemObjDao menuItemDao;
     
    public void init(){
    	menuItemObjs= populateMenu();
    }
 
    public List<MenuItemObj> findAllItem() {
        return menuItemObjs;
    }
     
    public MenuItemObj findById(long id) {
        for(MenuItemObj obj : menuItemObjs){
            if(obj.getId() == id){
                return obj;
            }
        }
        return null;
    }
     
    public MenuItemObj findByName(String name) {
    	for(MenuItemObj obj : menuItemObjs){
            if( obj.getBeer().equalsIgnoreCase(name)){
                return obj;
            }
        }
        return null;
    }
     
    public void saveItem(MenuItemObj menuItemObj) {
    	String menuId  = configProperties.getProperty("menuId");
    	menuItemObj.setId(Integer.decode(""+counter.incrementAndGet()));
    	menuItemObjs.add(menuItemObj);
    	 MenuList list = new MenuList();
    	 list.setItemListId(menuItemObj.getId());
    	 list.setId(Integer.decode(""+counter.incrementAndGet()));
    	 list.setMenuId(Integer.decode(menuId));
    	 list.setPriority(Integer.decode(""+counter.incrementAndGet()));
    	 menulistDao.save(list);
    }
 
    public void updateItem(MenuItemObj menuItemObj) {
        int index = menuItemObjs.indexOf(menuItemObj);
        menuItemObjs.set(index, menuItemObj);
        MenuList list = menulistDao.getMenuList(""+index);
        list.setItemListId(menuItemObj.getId());
        menulistDao.save(list);
    }
 
    public void deleteItemById(long id) {
         
        for (Iterator<MenuItemObj> iterator = menuItemObjs.iterator(); iterator.hasNext(); ) {
        	MenuItemObj menuItemObj = iterator.next();
            if (menuItemObj.getId() == id) {
                iterator.remove();
                menulistDao.delete(id);
            }
        }
    }
 
    public boolean isItemExist(MenuItemObj menuItemObj) {
        return findByName(menuItemObj.getBeer())!=null;
    }
     
    public void deleteAllItem(){
    	menuItemObjs.clear();
    }
 
    private  List<MenuItemObj> populateMenu(){
    	
    	// popolare qui il menu
        List<MenuItemObj> OggettiMenu = new ArrayList<MenuItemObj>();
        String menuId  = configProperties.getProperty("menuId");
        List<MenuList> listaMenu = menulistDao.getMenu(menuId);
        for(MenuList obj:listaMenu){
        	OggettiMenu.add(menuItemDao.getMenu(""+obj.getItemListId()));
        }
        return OggettiMenu;
    }




 
}
