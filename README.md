# connection
用于控制最大连接数，创造一个连接池，从中通过独有的名称获取连接。 
使用方式
```java
String connectionName = "connectionName" ;
Connection conn = null ;
try {
	conn = ConnectionManager.getConnection(connectionName) ;
    //TODO
} catch (ConnectionException e) {
	//TODO
} finally{
	if(conn!=null){
		conn.close() ;
	}
}
```
