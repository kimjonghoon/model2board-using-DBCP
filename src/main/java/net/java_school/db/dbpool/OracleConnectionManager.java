package net.java_school.db.dbpool;

import org.apache.commons.dbcp2.BasicDataSource;

public class OracleConnectionManager extends ConnectionManager {

	public OracleConnectionManager() {
		super("oracle");

		String driverClassName = "oracle.jdbc.driver.OracleDriver";
		String driverType = "jdbc:oracle:thin";
		String url = driverType + ":@" + dbServer + ":" + port + ":" + dbName;

		BasicDataSource ds = new BasicDataSource();

		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		
		ds.setUsername(userID);
		ds.setPassword(passwd);
		ds.setInitialSize(initConn);
		ds.setMaxTotal(maxConn);
		ds.setMaxWaitMillis(maxWait);

		this.ds = ds;
	}

}