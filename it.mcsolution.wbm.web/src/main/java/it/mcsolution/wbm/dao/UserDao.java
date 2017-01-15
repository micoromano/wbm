package it.mcsolution.wbm.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.mcsolution.wbm.entity.User;

public interface UserDao extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

	
   @Query("Select u from User u where u.user = :user and password =:password")
   public User getUser (@Param("user") String user,@Param("password") String password);
}