package com.ali.luna.mortred.tddl;

/**
 * 表名查找路由注解失败.
 * @author tongfeng.dhy
 *
 */
public class AnnotationLookupException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public AnnotationLookupException(String message) {
		super(message);
	}

	public AnnotationLookupException(String message, Throwable cause) {
		super(message, cause);
	}
}
