package it.mcsolution.wbm.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.mcsolution.wbm.entity.MenuItemObj;
import it.mcsolution.wbm.entity.User;

public interface MenuItemObjDao extends CrudRepository<MenuItemObj, Long>, JpaSpecificationExecutor<MenuItemObj> {

	   
	 @Query("Select mobj from MenuItemObj mobj where mobj.id = :Id")
	   public MenuItemObj getMenu (@Param("Id") String Id);
}