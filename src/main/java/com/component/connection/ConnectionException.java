package com.component.connection;

/**
 * 连接异常
 * @author rgou 
 */
public class ConnectionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 构造方法
	 * @param msg	异常信息描述
	 */
	public ConnectionException(String msg){
		super(msg);
	}
}
