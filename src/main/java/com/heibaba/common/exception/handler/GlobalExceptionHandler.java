package com.heibaba.common.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.heibaba.common.exception.BusinessException;
import com.heibaba.common.exception.ChangePwdException;
import com.heibaba.common.exception.UserAuthException;
import com.heibaba.common.exception.UserRegisterException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//使用tomcat和jetty对错误异常捕获不同
	//RuntimeException和Exceptio时：jetty：正常返回错误代码及信息；tomcat：返回500，Internal Server Error。
	
//	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Exception Occured!")
//	@ExceptionHandler(Exception.class)
//	public void handleException(HttpServletRequest request, Exception exception) {
//		logger.error("Exception Occured:: #URL= "+request.getRequestURL()+" #CLASS= "+exception.getClass().getSimpleName()+" #Cause= "+exception.getMessage());
//		logger.error(exception.toString(), exception);
//	}
	
//	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="IOException Occured!")
//	@ExceptionHandler(IOException.class)
//	public void handleIOException(HttpServletRequest request, IOException exception) {
//		logger.error("IOException Occured:: #URL= "+request.getRequestURL()+" #CLASS= "+exception.getClass().getSimpleName()+" #Cause= "+exception.getMessage());
//		logger.error(exception.toString(), exception);
//	}
	
//	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="SQLException Occured!")
//	@ExceptionHandler(SQLException.class)
//	public void handleSQLException(HttpServletRequest request, SQLException exception) {
//		logger.error("SQLException Occured:: #URL= "+request.getRequestURL()+" #CLASS="+exception.getClass().getSimpleName()+" #Cause= "+exception.getMessage());
//		logger.error(exception.toString(), exception);
//	}

	@ExceptionHandler(BusinessException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleBusinessException(HttpServletRequest request, HttpServletResponse response, Exception exception) {

		logger.error("BusinessException Occured:: #URL= "+request.getRequestURL()+" #CLASS= "+exception.getClass().getSimpleName()+" #Cause= "+exception.getMessage());
		logger.error(exception.toString(), exception);
		//Header中文有乱码（中文编码方式有问题）
//		HttpHeaders responseHeaders = new HttpHeaders();
//	    responseHeaders.set("errorCode", "000000");
//	    responseHeaders.set("errorMsg", exception.getMessage());
//	    return new ResponseEntity<>(responseHeaders, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(new ErrorResponse("000000", exception.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ChangePwdException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleChangePwdException(HttpServletRequest request, HttpServletResponse response, Exception exception) {

		logger.error("ChangePwdException Occured:: #URL= "+request.getRequestURL()+" #CLASS= "+exception.getClass().getSimpleName()+" #Cause= "+exception.getMessage());
		logger.error(exception.toString(), exception);
		return new ResponseEntity<>(new ErrorResponse("000003", exception.getMessage()), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(UserAuthException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleUserAuthException(HttpServletRequest request, HttpServletResponse response, Exception exception) {

		logger.error("UserAuthException Occured:: #URL= "+request.getRequestURL()+" #CLASS= "+exception.getClass().getSimpleName()+" #Cause= "+exception.getMessage());
		logger.error(exception.toString(), exception);
		return new ResponseEntity<>(new ErrorResponse("000004", exception.getMessage()), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(UserRegisterException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleUserRegisterException(HttpServletRequest request, HttpServletResponse response, Exception exception) {

		logger.error("UserRegisterException Occured:: #URL= "+request.getRequestURL()+" #CLASS= "+exception.getClass().getSimpleName()+" #Cause= "+exception.getMessage());
		logger.error(exception.toString(), exception);
		return new ResponseEntity<>(new ErrorResponse("000005", exception.getMessage()), HttpStatus.BAD_REQUEST);
	}
}