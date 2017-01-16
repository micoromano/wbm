package it.mcsolution.wbm.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.mcsolution.wbm.entity.User;

@Component
public interface UserDao extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

	
   @Query("Select u from User u where u.user = :user and password =:password")
   public User getUser (@Param("user") String user,@Param("password") String password);
}