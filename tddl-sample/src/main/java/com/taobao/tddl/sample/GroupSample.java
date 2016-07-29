package com.taobao.tddl.sample;

import org.springframework.jdbc.core.JdbcTemplate;

import com.taobao.tddl.group.jdbc.TGroupDataSource;

public class GroupSample {
	public static void main(String[] args) throws Throwable {
		TGroupDataSource tg = new TGroupDataSource();
		tg.setAppName("test_app");
		tg.setDbGroupKey("test_group_key");
		tg.init();
		System.out.println(
				tg.getConnection().prepareStatement("insert into t1 values(1,'name1')").execute());
		tg.destroyDataSource();
	}
}
