package com.infosys.admin.directory;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.core.env.Environment;
	import org.springframework.stereotype.Component;




	@Component("config")
	public class AdminConfiguration {
		
	    	
	    	@Value("${AdminService.VALID_DEPARTMENTS}")
		      String[] validDepartments;
	    	
	    	@Value("${AdminService.VALID_METRICS}")
	    	public  String[] validMetrics;
		 	
		 	@Autowired
		 	public Environment env;
		 	
		    public  String[] validDepartments() {
		    	
		        return validDepartments;
		    }
		    
		    public  String[] validMetrics() {
		    	
		        return validMetrics;
		    }
		
		    public String getMessage(String str) {
		    	
		    	return env.getProperty(str);
	    	
		    
		    }


}
