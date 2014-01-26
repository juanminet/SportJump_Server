package es.uma.sportjump.sjs.web.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLog{

	private Log log = LogFactory.getLog(ServiceLog.class);

	@Pointcut( "execution(* es.uma.sportjump.sjs.service.services..*.*(..))")
	public void serviceMethod(){
	}

	@Before("serviceMethod()")
	public void beforeServiceMethod(JoinPoint joinPoint) { 
		log.info("[Start] class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", method: " + joinPoint.getSignature().getName());
	}		

	@AfterReturning("serviceMethod()")
	public void afterServiceMethod(JoinPoint joinPoint) { 
		log.info("[Finish] class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", method: " + joinPoint.getSignature().getName());
	}

	@AfterThrowing(pointcut = "serviceMethod()", throwing = "exception")
	public void afterThrowException(JoinPoint joinPoint, Throwable exception) { 
		log.error("[Exception] class: "  + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", method: " + joinPoint.getSignature().getName() 
				  						+ "[" + exception.getMessage() + "]");
	}        
    
}