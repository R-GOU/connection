package com.component.connection;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接池，默认最大连接数为10。
 * @author rgou
 */
public final class ConnectionEntry {

	private final static Logger LOG = LoggerFactory.getLogger(ConnectionEntry.class);
	private String name = "";//连接池名字
	private int maxNum;//最大连接数
	private List<Connection> connections = new ArrayList<Connection>();//连接池的活跃连接
	
	/**
	 * 构造方法
	 * @param name	连接池入口名称	
	 * @param maxNum	连接池最大连接数
	 */
	public ConnectionEntry(String name, int maxNum){
		this.name = name;
		this.maxNum = maxNum;
	}
	
	/**
	 * 移除连接池中的连接
	 */
	void removeConnection(Connection conn){
		synchronized(connections){
			if(connections.contains(conn)){
				connections.remove(conn);
				if(LOG.isInfoEnabled()){
					LOG.info("close_connection:{}-{}-{}",name,conn.hashCode(),conn.getCreateTime());
				}
			}
			LOG.info("reclose_connection:{}-{}-{}",name,conn.hashCode(),conn.getCreateTime());
		}
	}
	
	/**
	 * 清除连接池的活跃连接
	 */
	public void clean(){
		synchronized(connections){
			connections.clear();
		}
	}
	
	/**
	 * 获取当前连接池中的活跃连接数
	 * @return	当前连接池中的活跃连接数
	 */
	public int getCurrentNum(){
		return this.connections.size();
	}
	
	/**
	 * 获取连接池的名称
	 * @return	连接池的名称
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * 设置连接池的最大连接数
	 * @param maxNum	连接池最大连接数
	 */
	public void setMaxNum(int maxNum){
		synchronized(connections){
			if( maxNum < 1 ){
				throw new IllegalArgumentException(maxNum+"");
			}
			this.maxNum = maxNum;
		}
	}
	
	/**
	 * 获取连接池的最大访问数
	 * @return	连接池的最大访问数
	 */
	public int getMaxNum() {
		return maxNum;
	}

	/**
	 * 从连接池中获取一个连接Connection
	 * @return	连接，Connection
	 * @throws ConnectionException 连接达至最大限制
	 */
	public Connection getConnection() throws ConnectionException{
		synchronized (connections) {
			if (this.connections.size() >= this.maxNum){
				LOG.info("max_connection:{}/{}",name,connections.size());
				throw new ConnectionException("max_connection:"+name);
			}
			Connection conn = new Connection(this) ;
			connections.add(conn);
			return conn;
		}
	}
	
	/**
	 * 获取连接池当前所有的活跃连接
	 * @return	连接池当前所有的活跃连接
	 */
	protected List<Connection> getConnections(){
		return this.connections;
	}
	
}
