package com.infosys.directory.utility;

abstract class ClientSubError {

}




class ClientSubErrorInfo extends ClientSubError {
  

private String object;
   private String field;
   private Object rejectedValue;
   private String message;

   ClientSubErrorInfo(String object, String message) {
       this.object = object;
       this.message = message;
   }
   
   public ClientSubErrorInfo(String object, String field, Object rejectedValue, String message) {
		this(object,message);
		this.field = field;
		this.rejectedValue = rejectedValue;
	}
   
   
}