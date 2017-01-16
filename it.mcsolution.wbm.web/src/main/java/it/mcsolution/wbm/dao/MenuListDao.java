package it.mcsolution.wbm.dao;

import java.util.List;

import javax.persistence.Entity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.mcsolution.wbm.entity.MenuList;


@Component
public interface MenuListDao extends CrudRepository<MenuList, Long>, JpaSpecificationExecutor<MenuList> {

	
	  @Query("Select ml from MenuList ml where ml.menuId = :menuId order by ml.priority")
	   public List<MenuList> getMenu (@Param("menuId") Integer menuId);
	
	  @Query("Select ml from MenuList ml where ml.priority = :pri order by ml.priority")
	   public MenuList getMenuList (@Param("pri") Integer id);
	  
	  @Query("Select ml from MenuList ml where ml.itemListId = :id order by ml.priority")
	   public MenuList getMenuListIdItem (@Param("id") Integer id);
   
}