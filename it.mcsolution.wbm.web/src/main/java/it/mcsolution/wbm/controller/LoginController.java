package it.mcsolution.wbm.controller;



import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.mcsolution.wbm.dao.UserDao;

@Controller
@RequestMapping("/login")
public class LoginController {

@Resource(name = "configProperties")
private Properties configProperties;
@Autowired
private UserDao userData; 
	
	@RequestMapping(value="/doLogin", method=RequestMethod.POST)
	public ModelAndView doLogin(@RequestParam("u") String user,@RequestParam("p") String password )
	{
		  ModelAndView mv = new ModelAndView();
	    if(checkUsr(user,password)){
	    	    mv.addObject("State", "True");
	    	    
	    	    
	    	    mv = new  ModelAndView("moduloBirre", "user", true);
	        }else{
	        	mv = new ModelAndView("login", "user", false);
	        }
	    return mv;
	    }
	
	@RequestMapping(value="/startApp", method=RequestMethod.POST)
	public ModelAndView startApp()
	{
		  ModelAndView mv = new ModelAndView();
		  if (configProperties.getProperty("client").equalsIgnoreCase("locked")) {
	    	    mv = new  ModelAndView("login", "user", false);
	        }else{
	        	mv = new ModelAndView("menuCompile", "user", true);
	        }
	    return mv;
	    }
	
	@RequestMapping(value="/startWbm", method=RequestMethod.POST)
	public ModelAndView startWbm()
	{
		  ModelAndView mv = new ModelAndView();
		  if (configProperties.getProperty("client").equalsIgnoreCase("locked")) {
	    	    mv = new  ModelAndView("login", "user", false);
	        }else{
	        	mv = new ModelAndView("menuView", "user", true);
	        }
	    return mv;
	    }
		
	
	
	
	public boolean checkUsr(String user,String password){
		
		if(userData.getUser(user, password)!=null){
		return true;
		}else{
		return false;	
		}
	}



	}
