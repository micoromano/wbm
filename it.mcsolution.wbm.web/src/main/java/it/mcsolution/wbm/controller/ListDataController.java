package it.mcsolution.wbm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import it.mcsolution.wbm.api.WbmMenuItemService;
import it.mcsolution.wbm.entity.MenuItemObj;

@RestController
public class ListDataController {
  
    @Autowired
    WbmMenuItemService listService;  //Service which will do all data retrieval/manipulation work
  
     
    //-------------------Retrieve All Users--------------------------------------------------------
      
    @RequestMapping(value = "/item/", method = RequestMethod.GET)
    public ResponseEntity<List<MenuItemObj>> listAllItem() {
        List<MenuItemObj> objs = listService.findAllItem();
        if(objs.isEmpty()){
            return new ResponseEntity<List<MenuItemObj>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
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
  
        listService.saveItem(obj);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/item/{id}").buildAndExpand(obj.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
     
      
    //------------------- Update a User --------------------------------------------------------
      
    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MenuItemObj> updateUser(@PathVariable("id") long id, @RequestBody MenuItemObj item) {
        System.out.println("Updating User " + id);
          
        MenuItemObj currentUser = listService.findById(id);
          
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<MenuItemObj>(HttpStatus.NOT_FOUND);
        }
  
        return new ResponseEntity<MenuItemObj>(currentUser, HttpStatus.OK);
    }
  
     
     
    //------------------- Delete a User --------------------------------------------------------
      
    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MenuItemObj> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
  
        MenuItemObj user = listService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<MenuItemObj>(HttpStatus.NOT_FOUND);
        }
  
    
        return new ResponseEntity<MenuItemObj>(HttpStatus.NO_CONTENT);
    }
  
      
     
    //------------------- Delete All Users --------------------------------------------------------
      
    @RequestMapping(value = "/item/", method = RequestMethod.DELETE)
    public ResponseEntity<MenuItemObj> deleteAllUsers() {
        System.out.println("Deleting All Users");
  
        listService.deleteAllItem();;
        return new ResponseEntity<MenuItemObj>(HttpStatus.NO_CONTENT);
    }






	
  
}