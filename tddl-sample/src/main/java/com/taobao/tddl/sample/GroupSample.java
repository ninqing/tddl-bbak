package com.taobao.tddl.sample;

import org.springframework.jdbc.core.JdbcTemplate;

import com.taobao.tddl.group.jdbc.TGroupDataSource;

public class GroupSample {
	public static void main(String[] args) throws Throwable {
		TGroupDataSource tg = new TGroupDataSource();
		tg.setAppName("test_app");
		tg.setDbGroupKey("test_group_key");
		tg.init();
		JdbcTemplate jt = new JdbcTemplate(tg);
		System.out.println(jt.update("insert into t1 values(12,'name1')"));
		tg.destroyDataSource();
	}
}
