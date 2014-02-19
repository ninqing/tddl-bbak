package com.taobao.tddl.sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.taobao.tddl.common.exception.TddlException;
import com.taobao.tddl.matrix.jdbc.TDataSource;

public class Sample {

    public static void main(String[] args) throws TddlException, SQLException {

        TDataSource ds = new TDataSource();

        ds.setAppName("DEV_SUBWAY_MYSQL");
        ds.setRuleFile("rule.xml");

        Map cp = new HashMap();
        cp.put("ALLOW_TEMPORARY_TABLE", "True");

        ds.setConnectionProperties(cp);

        ds.init();

        System.out.println("init done");

        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT a.custId, a.id,b.adgroupid, SUM(c.cost) / SUM(c.click) AS ppc,c.thedate, c.memberid, c.campaignid, c.productlineid, c.adgroupid, SUM(c.impression), SUM(c.cost), SUM(c.click), SUM(c.click) / SUM(c.impression) AS ctr, b.title, a.onlinestate, a.reason FROM Lunaadgroup a join lunaadgroupinfo b on a.id=b.adgroupid  LEFT JOIN rpt_solar_adgroup_ob c ON a.id = c.adgroupid where a.custid='1102000884' and b.custid='1102000884' and b.title like '%办公室%'  GROUP BY c.thedate, c.memberid, c.campaignid, c.productlineid, c.adgroupid HAVING ppc > 1 ORDER BY ppc DESC");

        long start = System.currentTimeMillis();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            StringBuilder sb = new StringBuilder();
            int count = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= count; i++) {

                String key = rs.getMetaData().getColumnLabel(i);
                Object val = rs.getObject(i);
                sb.append("[" + rs.getMetaData().getTableName(i) + "." + key + "->" + val + "]");
            }
            System.out.println(sb.toString());
        }

        rs.close();
        ps.close();
        conn.close();

        System.out.println("done " + (System.currentTimeMillis() - start));
    }

}
