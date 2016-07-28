package com.taobao.tddl.sample;

import com.taobao.tddl.group.jdbc.TGroupDataSource;

public class GroupSample {
	public static void main(String[] args) {
		TGroupDataSource tg = new TGroupDataSource("test_group_key", "test_app");
		tg.init();
	}
}
