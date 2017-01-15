package it.mcsolution.wbm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import it.mcsolution.wbm.bean.BeerBean;
import it.mcsolution.wbm.entity.MenuItemObj;
import it.mcsolution.wbm.google.utility.GoogleSpreadsheetUtil;
import it.mcsolution.wbm.spi.WbmMenuBean;
@Controller
@RequestMapping("/operation")
public class OperationController {

	@Resource(name = "configProperties")
	private Properties configProperties;
	@Autowired
	private WbmMenuBean webMenuBean; 
	
	@RequestMapping(value = "/processExcel", method = RequestMethod.POST)
	public ModelAndView processExcel(Model model, @RequestParam("excelfile") MultipartFile excelfile) {		
		ModelAndView mv = new ModelAndView();
		try {
			File newFile = new File(configProperties.getProperty("excel.folder.new")+"/"+excelfile.getOriginalFilename());
			File folder = new File(configProperties.getProperty("excel.folder.active"));
			File[] listOfFiles = folder.listFiles();

			    for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			    	  listOfFiles[i].delete();
			        
			      } else if (listOfFiles[i].isDirectory()) {
			    	  listOfFiles[i].delete();
			      }
			    }
			File sourceActiveToRepos = new File(configProperties.getProperty("excel.folder.active"));
			File destActiveToRepos = new File(configProperties.getProperty("excel.folder.repository"));
			try {
			    FileUtils.copyDirectory(sourceActiveToRepos, destActiveToRepos);
			} catch (IOException e) {
			    e.printStackTrace();
			}
			File sourceNewToActive = new File(configProperties.getProperty("excel.folder.new"));
			File destNewToActive = new File(configProperties.getProperty("excel.folder.active"));
			try {
			    FileUtils.copyDirectory(sourceNewToActive, destNewToActive);
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			File folder2 = new File(configProperties.getProperty("excel.folder.new"));
			File[] listOfFiles2 = folder2.listFiles();

		    for (int i = 0; i < listOfFiles2.length; i++) {
		      if (listOfFiles2[i].isFile()) {
		    	  listOfFiles2[i].delete();
		        
		      } else if (listOfFiles2[i].isDirectory()) {
		    	  listOfFiles2[i].delete();
		      }
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return new  ModelAndView("fail", "user", false);
		}
		
		if (configProperties.getProperty("client").equalsIgnoreCase("locked")) {
    	    mv = new  ModelAndView("login", "user", false);
        }else{
        	mv = new ModelAndView("success", "user", true);
        }
 
		return mv;	
	}
	
	@RequestMapping(value = "/readExcel")
	public ModelAndView readExcel() {		
		ModelAndView model = new ModelAndView();
		List<BeerBean> lstUser = new ArrayList<BeerBean>();
		try {
			
			
			
			// Build a new authorized API client service.
			if(configProperties.getProperty("isgoogleSheet").equalsIgnoreCase("true")){
			
			GoogleSpreadsheetUtil  gservice = new GoogleSpreadsheetUtil();
			gservice.setConfigProperties(configProperties.getProperty("excel.folder.root"));
	        Sheets service = gservice.getSheetsService();

	        // Prints the names and majors of students in a sample spreadsheet:
	        // https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
	        String spreadsheetId =configProperties.getProperty("sheetId");
	        String range = "colRange";
	        ValueRange response = service.spreadsheets().values()
	            .get(spreadsheetId, range)
	            .execute();
	        List<List<Object>> values = response.getValues();
	        if (values == null || values.size() == 0) {
	            System.out.println("No data found.");
	        } else {
	          int i=0;
	          for (List row : values) {
	            // Print columns A and E, which correspond to indices 0 and 4.
	          if(values.get(i).get(0)!=null){
	            
	        	BeerBean beer = new BeerBean();
				// Creates an object representing a single row in excel
				
				// Sets the Read data to the model class
				beer.setBeer(""+values.get(i).get(0));
				beer.setType(""+values.get(i).get(1));
				beer.setNationality(""+values.get(i).get(2));
				beer.setShort_Description(""+values.get(i).get(3));
				if(values.get(i).get(4)==null){
				beer.setGrade("N/D");
				}else{
					beer.setGrade(""+values.get(i).get(4));	
				}
				if(values.get(i).get(5)==null){
				beer.setNote("N/D");
				}else{
				beer.setNote(""+values.get(i).get(5));
				}
				// persist data into database in here
				lstUser.add(beer);
				
	          }else{
	        	  break;
	          }
	          i++;
			}			
	            
	          }
	        
	    }else{ 
	    	if(configProperties.getProperty("isexcelSheet").equalsIgnoreCase("true")){
			File folder = new File(configProperties.getProperty("excel.folder.active"));
			File[] listOfFiles = folder.listFiles();
			    for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
						// Creates a workbook object from the uploaded excelfile
			    		InputStream is = new FileInputStream(configProperties.getProperty("excel.folder.active")+"/"+listOfFiles[i].getName());
			    		XSSFWorkbook workbook = new XSSFWorkbook(is);
						// Creates a worksheet object representing the first sheet
						XSSFSheet worksheet = workbook.getSheetAt(0);
						// Reads the data in excel file until last row is encountered
						while (i <= worksheet.getLastRowNum()) {
							// Creates an object for the UserInfo Model
							
							BeerBean beer = new BeerBean();
							// Creates an object representing a single row in excel
							XSSFRow row = worksheet.getRow(i++);
							// Sets the Read data to the model class
							beer.setBeer(row.getCell(0).getStringCellValue());
							beer.setType(row.getCell(1).getStringCellValue());
							beer.setNationality(row.getCell(2).getStringCellValue());
							beer.setShort_Description(row.getCell(3).getStringCellValue());
							if(row.getCell(4)==null){
							beer.setGrade("N/D");
							}else{
								beer.setGrade(row.getCell(4).getStringCellValue());	
							}
							if(row.getCell(5)==null){
							beer.setNote("N/D");	
							}else{
							beer.setNote(row.getCell(5).getStringCellValue());
							}
							// persist data into database in here
							lstUser.add(beer);
						}			
						workbook.close();
					
			    	  
			        
			      } else if (listOfFiles[i].isDirectory()) {
			    	 continue;
			      }
			    }
	    	}else{
	    	
	    		String menuId  = configProperties.getProperty("menuId");
	    		List<MenuItemObj> menuItemObjList = webMenuBean.getListMenu(menuId);
	    		
	    		for(MenuItemObj obj:menuItemObjList){
	    		BeerBean beer = new BeerBean();
				// Creates an object representing a single row in excel
			
		    		if(obj.getBeer()!=null){
		    			beer.setBeer(obj.getBeer());
		    		}else{
		    			beer.setBeer("N/D");	
		    		}
		    		if(obj.getType()!=null){
		    			beer.setType(obj.getType());
		    		}else{
		    			beer.setType("N/D");	
		    		}
		    		if(obj.getNationality()!=null){
		    			beer.setNationality(obj.getNationality());
		    		}else{
		    			beer.setNationality("N/D");	
		    		}
		    		if(obj.getShort_description()!=null){
		    			beer.setShort_Description(obj.getShort_description());
		    		}else{
		    			beer.setShort_Description("N/D");	
		    		}
		    		if(obj.getGrade()!=null){
		    			beer.setGrade(obj.getGrade());
		    		}else{
		    			beer.setGrade("N/D");	
		    		}
		    		if(obj.getNote()!=null){
		    			beer.setNote(obj.getNote());
		    		}else{
		    			beer.setNote("N/D");	
		    		}
			
				
				
				lstUser.add(beer);
	    		}
	    		//check if listSize is even
	    		if(lstUser.size()% 2 != 0){
	    			BeerBean beer = new BeerBean();
	    			beer.setBeer("---");
	    			beer.setType("---");
	    			beer.setNationality("---");
	    			beer.setShort_Description("---");
	    			beer.setGrade("---");
	    			beer.setNote("---");
	    			lstUser.add(beer);
	    		} 
	    		
	    	}
	    }
		} catch (Exception e) {
			e.printStackTrace();
			return new  ModelAndView("fail", "user", false);
		}
		if (configProperties.getProperty("client").equalsIgnoreCase("locked")) {
    	    model = new  ModelAndView("login", "user", false);
        }else{
        	model = new ModelAndView("birre", "user", lstUser);
        }
		
		return model;	
	}
	
}

