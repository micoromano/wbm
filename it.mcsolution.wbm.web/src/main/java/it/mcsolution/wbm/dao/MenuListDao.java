package it.mcsolution.wbm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.mcsolution.wbm.entity.MenuList;
import it.mcsolution.wbm.entity.User;

public interface MenuListDao extends CrudRepository<MenuList, Long>, JpaSpecificationExecutor<MenuList> {

	   
	  @Query("Select ml from Menulist ml where ml.menuId = :menuId order by ml.priority")
	   public List<MenuList> getMenu (@Param("menuId") String menuId);
	  
	  @Query("Select ml from Menulist ml where ml.priority = :pri order by ml.priority")
	   public MenuList getMenuList (@Param("pri") String id);
   
}