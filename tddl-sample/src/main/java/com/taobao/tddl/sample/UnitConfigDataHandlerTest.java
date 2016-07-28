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
		meta.put("com.taobao.tddl.jdbc.group_V2.4.1_test_group_key", "dbk1:R10W10,dbk2:R0W0");
		meta.put("com.taobao.tddl.atom.global.dbk2", "dbk1:R10W10,dbk2:R0W0");
		meta.put("com.taobao.tddl.atom.global.dbk1",
				"ip=127.0.0.1\nport=3306\ndbName=test1\ndbType=mysql\ndbStatus=RW");
		meta.put("com.taobao.tddl.atom.global.dbk2",
				"ip=127.0.0.1\nport=3307\ndbName=test1\ndbType=mysql\ndbStatus=RW");
		meta.put("com.taobao.tddl.atom.app.test_app.dbk2",
				"userName=tddl\nminPoolSize=1\nmaxPoolSize=2\nidleTimeout=1000\nblockingTimeout=1000\nconnectionProperties=rewriteBatchedStatements=true&characterEncoding=UTF8&connectTimeout=1000&autoReconnect=true&socketTimeout=12000");
		meta.put("com.taobao.tddl.atom.app.test_app.dbk1",
				"userName=tddl\nminPoolSize=1\nmaxPoolSize=2\nidleTimeout=1000\nblockingTimeout=1000\nconnectionProperties=rewriteBatchedStatements=true&characterEncoding=UTF8&connectTimeout=1000&autoReconnect=true&socketTimeout=12000");
		meta.put("com.taobao.tddl.atom.passwd.test1.mysql.tddl",
				"encPasswd=test");
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
