package es.uma.sportjump.sjs.web.controller.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;

public class BaseValidator {		

	
	protected void checkMandatory(String path, Object value, String messageKey, BindingResult errors) {
		if(value == null){
			errors.rejectValue(path, "NotEmpty." + messageKey +"." + path);
		}else if (value instanceof String){
			if (StringUtils.isEmpty((String)value)){
				errors.rejectValue(path, "NotEmpty." + messageKey +"." + path);
			}		
		}else if (value instanceof Long){
			Long zero = new Long(0);
			if(value.equals(zero)){
				errors.rejectValue(path, "NotEmpty." + messageKey +"." + path);
			}
		}
	}
	
	protected void checkDate(String date, String path, String messageKey, BindingResult errors) {
		if (ValidationUtils.validateDateFormat(date)){
			errors.rejectValue(path, messageKey);
		}	
	}

	protected void checkDni(String dni, String path, String messageKey,BindingResult errors) {
		if (!dni.matches("\\d{8}([A-Z]|[a-z])")){
			errors.rejectValue(path, messageKey);
		}		
	}
	
	


	





}
