package com.component.connection;

import java.util.Calendar;

public final class Connection {
	private ConnectionEntry connectionEntry;//连接所使用的连接池
	private String createTime;//创建时间
	
	/**
	 * 构造方法
	 * @param connectionEntry	连接所使用的连接池
	 */
	protected Connection(ConnectionEntry connectionEntry){
		this.connectionEntry = connectionEntry;
		Calendar calendar = Calendar.getInstance();
		createTime = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
	}
	
	/**
	 * 关闭连接
	 */
	public void close(){
		connectionEntry.removeConnection(this);
	}
	
	/**
	 * 连接是否是关闭的
	 * @return	true/false
	 */
	public boolean isClosed(){
		return connectionEntry.getConnections().contains(this);
	}

	/**
	 * 连接的建立时间
	 * @return	时分秒，HH:mi:ss
	 */
	public String getCreateTime() {
		return createTime;
	}

}
