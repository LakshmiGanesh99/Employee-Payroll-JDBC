package com.capg.emppayrollservices;

public class DBServiceException extends Exception {
		DBServiceExceptionType exceptionType;
		
		public DBServiceException(String message, DBServiceExceptionType exceptionType) {
			super(message);
			this.exceptionType=exceptionType;
		}
}
