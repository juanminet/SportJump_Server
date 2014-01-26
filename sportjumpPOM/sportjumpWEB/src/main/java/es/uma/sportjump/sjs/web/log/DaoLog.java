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
public class DaoLog{

	private Log log = LogFactory.getLog(DaoLog.class);

	@Pointcut( "execution(* es.uma.sportjump.sjs.dao.daos..*.*(..))")
	public void daoMethod(){
	}

	@Before("daoMethod()")
	public void beforeDaoMethod(JoinPoint joinPoint) { 
		log.info("[Start] class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", method: " + joinPoint.getSignature().getName());
	}		

	@AfterReturning("daoMethod()")
	public void afterDaoMethod(JoinPoint joinPoint) { 
		log.info("[Finish] class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", method: " + joinPoint.getSignature().getName());
	}

	@AfterThrowing(pointcut = "daoMethod()", throwing = "exception")
	public void afterThrowDaoException(JoinPoint joinPoint, Throwable exception) { 
		log.error("[Exception] class: "  + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", method: " + joinPoint.getSignature().getName() 
				  						+ "[" + exception.getMessage() + "]");
	}        
    
}