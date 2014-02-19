package com.taobao.tddl.sample;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import com.taobao.tddl.common.exception.TddlException;
import com.taobao.tddl.executor.MatrixExecutor;
import com.taobao.tddl.executor.common.ExecutionContext;
import com.taobao.tddl.executor.cursor.ResultCursor;
import com.taobao.tddl.executor.rowset.IRowSet;
import com.taobao.tddl.matrix.config.MatrixConfigHolder;
import com.taobao.tddl.matrix.jdbc.TDataSource;

public class Sample {

    public static void main22(String[] args) throws TddlException {

        MatrixConfigHolder configHolder = new MatrixConfigHolder();
        Map cp = new HashMap();
        cp.put("ALLOW_TEMPORARY_TABLE", "True");
        configHolder.setConnectionProperties(cp);
        configHolder.setAppName("DEV_SUBWAY_MYSQL");
        configHolder.setRuleFilePath("rule.xml");
        // configHolder.setTopologyFilePath("test_matrix.xml");
        // configHolder.setSchemaFilePath("test_schema.xml");

        try {
            configHolder.init();
        } catch (TddlException e) {
            e.printStackTrace();
        }

        MatrixExecutor me = new MatrixExecutor();

        ExecutionContext context = new ExecutionContext();
        context.setExtraCmds(cp);
        context.setExecutorService(Executors.newCachedThreadPool());
        // ResultCursor rc = me.execute("select * from bmw_users limit 10",
        // context);

        {
            ResultCursor rc = me.execute("SELECT  SUM(c.cost) / SUM(c.click) AS ppc,c.thedate, c.memberid, c.campaignid, c.productlineid, c.adgroupid, SUM(c.impression), SUM(c.cost), SUM(c.click), SUM(c.click) / SUM(c.impression) AS ctr, b.title, a.onlinestate, a.reason FROM Lunaadgroup a join lunaadgroupinfo b on a.id=b.adgroupid  LEFT JOIN rpt_solar_adgroup_ob c ON a.id = c.adgroupid  GROUP BY c.thedate, c.memberid, c.campaignid, c.productlineid, c.adgroupid HAVING ppc > 1 ORDER BY ppc DESC",
                context);

            // ResultCursor rc =
            // me.execute("SELECT * FROM Lunaadgroup a join lunaadgroupinfo b on a.id=b.adgroupid  LEFT JOIN rpt_solar_adgroup_ob c ON a.id = c.adgroupid",
            // context);
            // ResultCursor rc =
            // me.execute("SELECT * FROM Lunaadgroup a join lunaadgroupinfo b on a.id=b.adgroupid where a.id in (23310,23311,23333,23334,23335,23336,23337,23338,23339,432234)",
            // context);
            // ResultCursor rc =
            // me.execute("SELECT * FROM Lunaadgroup a where a.id in (23310,23311,23333,23334,23335,23336,23337,23338,23339,432234)",
            // context);
            IRowSet row = null;
            while ((row = rc.next()) != null) {
                System.out.println(row);
            }

            System.out.println("ok");
        }

    }

    public static void main(String[] args) throws TddlException, SQLException {

        TDataSource ds = new TDataSource();

        ds.setAppName("DEV_SUBWAY_MYSQL");
        ds.setRuleFile("rule.xml");

        Map cp = new HashMap();
        cp.put("ALLOW_TEMPORARY_TABLE", "True");

        ds.setConnectionProperties(cp);

        ds.init();
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT  SUM(c.cost) / SUM(c.click) AS ppc,c.thedate, c.memberid, c.campaignid, c.productlineid, c.adgroupid, SUM(c.impression), SUM(c.cost), SUM(c.click), SUM(c.click) / SUM(c.impression) AS ctr, b.title, a.onlinestate, a.reason FROM Lunaadgroup a join lunaadgroupinfo b on a.id=b.adgroupid  LEFT JOIN rpt_solar_adgroup_ob c ON a.id = c.adgroupid where b.title like '%办公室%'  GROUP BY c.thedate, c.memberid, c.campaignid, c.productlineid, c.adgroupid HAVING ppc > 1 ORDER BY ppc DESC");

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

        System.out.println("done");
    }

}
