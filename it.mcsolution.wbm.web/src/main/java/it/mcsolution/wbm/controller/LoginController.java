package it.mcsolution.wbm.controller;



import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

@Resource(name = "configProperties")
private Properties configProperties;
	
	@RequestMapping(value="/doLogin", method=RequestMethod.POST)
	public ModelAndView doLogin(@RequestParam("u") String user,@RequestParam("p") String password )
	{
		  ModelAndView mv = new ModelAndView();
	    if(checkUsr(user,password)){
	    	    mv.addObject("State", "True");
	    	    
	    	    
	    	    mv = new  ModelAndView("nations", "user", true);
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
		
		if(user.equalsIgnoreCase("pippo")&&password.equalsIgnoreCase("pippo")){
		return true;
		}else{
		return false;	
		}
	}



	}
