package it.mcsolution.wbm.controller;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import it.mcsolution.wbm.api.WbmMenuItemService;
import it.mcsolution.wbm.api.WbmMenuItemServiceImpl;
import it.mcsolution.wbm.dao.MenuItemObjDao;
import it.mcsolution.wbm.dao.MenuListDao;
import it.mcsolution.wbm.entity.MenuItemObj;
import it.mcsolution.wbm.entity.MenuList;
import it.mcsolution.wbm.spi.WbmMenuBean;
@Service
@RestController
public class ListDataController {
	private static final AtomicLong counter = new AtomicLong();
	@Resource(name = "configProperties")
	private Properties configProperties;
    @Autowired
    WbmMenuItemServiceImpl listService;  //Service which will do all data retrieval/manipulation work
    @Autowired(required=false)
    private WbmMenuBean menuDao;
  
     
    //-------------------Retrieve All Users--------------------------------------------------------
      
    @RequestMapping(value = "/item/", method = RequestMethod.GET)
    public ResponseEntity<List<MenuItemObj>> fetchAllItem() {
        List<MenuItemObj> objs = menuDao.getListMenu(configProperties.getProperty("menuId"));
        if(objs.isEmpty()){
            return new ResponseEntity<List<MenuItemObj>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        listService.setMenuItemObjs(objs);
        return new ResponseEntity<List<MenuItemObj>>(objs, HttpStatus.OK);
    }
  
  
     
    //-------------------Retrieve Single User--------------------------------------------------------
      
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItemObj> getItem(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        MenuItemObj obj = listService.findById(id);
        if (obj == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<MenuItemObj>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MenuItemObj>(obj, HttpStatus.OK);
    }
  
      
      
    //-------------------Create a User--------------------------------------------------------
      
    @RequestMapping(value = "/item/", method = RequestMethod.POST)
    public ResponseEntity<Void> createItem(@RequestBody MenuItemObj obj,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + obj.getBeer());
  
       
        if (listService.isItemExist(obj)) {
            System.out.println("A User with name " + obj.getBeer() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        String menuId  = configProperties.getProperty("menuId");
        MenuList list = new MenuList();
        list.setItemListId((menuDao.getAllItems().size()+1));
   	 	list.setMenuId(Integer.decode(menuId));
   	 	list.setPriority(menuDao.getListMenu(configProperties.getProperty("menuId")).size()+1);
   	 	list.setId(menuDao.getListMenu(configProperties.getProperty("menuId")).size()+1);
   	 	
   	    if(menuDao.findItemById(obj.getId())==null){
   	    	obj.setId((menuDao.getAllItems().size()+1));
   	    	menuDao.saveMenuObj(obj);
   	    }
   	   
   	    menuDao.saveListObj(list);
        listService.saveItem(obj);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/item/{id}").buildAndExpand(obj.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
     
      
    //------------------- Update a User --------------------------------------------------------
      
    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MenuItemObj> updateItem(@PathVariable("id") long id, @RequestBody MenuItemObj item) {
        System.out.println("Updating User " + id);
          
        MenuItemObj currentObj = listService.findById(id);
          
        if (currentObj==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<MenuItemObj>(HttpStatus.NOT_FOUND);
        }
  
        MenuList list = menuDao.findItemListById(Integer.valueOf(""+id));
        list.setItemListId(item.getId());
       
   	    	menuDao.saveMenuObj(item);
   	    	menuDao.saveListObj(list);
        listService.updateItem(currentObj,item);
        
        return new ResponseEntity<MenuItemObj>(item, HttpStatus.OK);
    }
  
     
     
    //------------------- Delete a User --------------------------------------------------------
      
    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MenuItemObj> deleteItem(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
  
        MenuItemObj user = listService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<MenuItemObj>(HttpStatus.NOT_FOUND);
        }
        MenuList list = menuDao.findItemListById(Integer.valueOf(""+id));
        menuDao.deleteListObj(list);
        listService.deleteItemById(id);
        return new ResponseEntity<MenuItemObj>(HttpStatus.NO_CONTENT);
    }
  
      
     
    //------------------- Delete All Users --------------------------------------------------------
      
    @RequestMapping(value = "/item/", method = RequestMethod.DELETE)
    public ResponseEntity<MenuItemObj> deleteAllItem() {
        System.out.println("Deleting All Users");
  
        listService.deleteAllItem();
        menuDao.deleteAllListObj();
        return new ResponseEntity<MenuItemObj>(HttpStatus.NO_CONTENT);
    }






	
  
}