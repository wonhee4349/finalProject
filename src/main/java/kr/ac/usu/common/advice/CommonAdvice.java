package kr.ac.usu.common.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonAdvice {
	public void before(){
	      log.info("=================================================");
	   }
	   
	   public void after(){
	      log.info("-------------------------------------------------");
	   }
	   
	   
	   public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
	      Object[] args = joinPoint.getArgs();
	      Object target = joinPoint.getTarget();
	      String targetname = target.getClass().getSimpleName();
	      Signature signature = joinPoint.getSignature();
	      String methodName = signature.getName();
//	      log.info("{}.{}.({})",targetname,methodName,signature);
//	      log.info("=================================================",args);
//	      long start = System.currentTimeMillis();
	      Object targetResult = joinPoint.proceed(args);   //advice 내에서 target 호출함.
//	      long end = System.currentTimeMillis();
//	      log.info("-------------소요 시간 : {}ms -------------------------------",(end-start));
	      
	      return targetResult;
	   }
}




