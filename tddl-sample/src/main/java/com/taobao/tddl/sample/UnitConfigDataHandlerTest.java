package com.taobao.tddl.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import com.taobao.tddl.config.ConfigDataListener;
import com.taobao.tddl.config.impl.UnitConfigDataHandler;

public class UnitConfigDataHandlerTest extends UnitConfigDataHandler {
	static Map<String, String> meta = new HashMap<String, String>();
	static {
		meta.put("com.taobao.tddl.v1_test_app_dbgroups", "test_group_key0,test_group_key1");
		{   //group key
			meta.put("com.taobao.tddl.jdbc.group_V2.4.1_test_group_key0", "127_0_0_1_3306_tddl_test_0:R10W10,127_0_0_1_3307_tddl_test_0:R0W0");
			{   //global key
				meta.put("com.taobao.tddl.atom.global.127_0_0_1_3306_tddl_test_0", "ip=127.0.0.1\nport=3306\ndbName=tddl_test_0\ndbType=mysql\ndbStatus=RW");
				//app key
				meta.put("com.taobao.tddl.atom.app.test_app.127_0_0_1_3306_tddl_test_0", "userName=tddl\nminPoolSize=10\ninitPoolSize=15\nmaxPoolSize=20\nidleTimeout=10\nblockingTimeout=5\npreparedStatementCacheSize=15\nconnectionProperties=rewriteBatchedStatements=true&characterEncoding=UTF8&connectTimeout=1000&autoReconnect=true&socketTimeout=12000");
				
				//global key
				meta.put("com.taobao.tddl.atom.global.127_0_0_1_3307_tddl_test_0", "ip=127.0.0.1\nport=3307\ndbName=tddl_test_0\ndbType=mysql\ndbStatus=RW");
				//app key
				meta.put("com.taobao.tddl.atom.app.test_app.127_0_0_1_3307_tddl_test_0", "userName=tddl\nminPoolSize=10\ninitPoolSize=15\nmaxPoolSize=20\nidleTimeout=10\nblockingTimeout=5\npreparedStatementCacheSize=15\nconnectionProperties=rewriteBatchedStatements=true&characterEncoding=UTF8&connectTimeout=1000&autoReconnect=true&socketTimeout=12000");
				
				//pass key
				meta.put("com.taobao.tddl.atom.passwd.tddl_test_0.mysql.tddl", "encPasswd=tddl");
			}
			
			//group key
			meta.put("com.taobao.tddl.jdbc.group_V2.4.1_test_group_key1", "127_0_0_1_3306_tddl_test_1:R10W10,127_0_0_1_3307_tddl_test_1:R0W0");
			{   //global key
				meta.put("com.taobao.tddl.atom.global.127_0_0_1_3306_tddl_test_1", "ip=127.0.0.1\nport=3306\ndbName=tddl_test_1\ndbType=mysql\ndbStatus=RW");
				//app key
				meta.put("com.taobao.tddl.atom.app.test_app.127_0_0_1_3306_tddl_test_1", "userName=tddl\nminPoolSize=10\ninitPoolSize=15\nmaxPoolSize=20\nidleTimeout=10\nblockingTimeout=5\npreparedStatementCacheSize=15\nconnectionProperties=rewriteBatchedStatements=true&characterEncoding=UTF8&connectTimeout=1000&autoReconnect=true&socketTimeout=12000");
				
				//global key
				meta.put("com.taobao.tddl.atom.global.127_0_0_1_3307_tddl_test_1", "ip=127.0.0.1\nport=3307\ndbName=tddl_test_1\ndbType=mysql\ndbStatus=RW");
				//app key
				meta.put("com.taobao.tddl.atom.app.test_app.127_0_0_1_3307_tddl_test_1", "userName=tddl\nminPoolSize=10\ninitPoolSize=15\nmaxPoolSize=20\nidleTimeout=10\nblockingTimeout=5\npreparedStatementCacheSize=15\nconnectionProperties=rewriteBatchedStatements=true&characterEncoding=UTF8&connectTimeout=1000&autoReconnect=true&socketTimeout=12000");
				
				//pass key
				meta.put("com.taobao.tddl.atom.passwd.tddl_test_1.mysql.tddl", "encPasswd=tddl");
			}
		
		}
		
		
	}

	@Override
	public String getData(long timeout, String strategy) {
		System.out.println(this.dataId);
		String result = meta.get(this.dataId);
		return result;
	}

	@Override
	public String getNullableData(long timeout, String strategy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListener(ConfigDataListener configDataListener, Executor executor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListeners(List<ConfigDataListener> configDataListenerList, Executor executor) {
		// TODO Auto-generated method stub

	}

}
