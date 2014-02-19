package com.taobao.tddl.sample;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import com.taobao.tddl.common.exception.TddlException;
import com.taobao.tddl.executor.MatrixExecutor;
import com.taobao.tddl.executor.common.ExecutionContext;
import com.taobao.tddl.executor.cursor.ResultCursor;
import com.taobao.tddl.executor.rowset.IRowSet;
import com.taobao.tddl.matrix.config.MatrixConfigHolder;

public class ConfigHolderTest {

    public static void main(String[] args) throws TddlException {

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
        // long start = System.currentTimeMillis();
        // {
        // ResultCursor rc =
        // me.execute("SELECT b.thedate, b. memberid, b.campaignid, b.productlineid, b.adgroupid, sum(b.impression), sum(b.cost), sum(b.click),(sum(b.click)/sum(b.impression)) as ctr,(sum(b.cost) / sum(b.click)) as ppc,a.outsidekey, a.onlinestate, a.reason FROM Lunaadgroup a left join rpt_solar_adgroup_ob b on a.id=b.adgroupid WHERE a.outsidekey like '%taobao%' AND a.onlinestate in (1,2,3) AND b.impression>10 AND b.thedate between '2013-12-12' AND '2013-12-31' GROUP BY b.thedate, b.memberid, b.campaignid, b.productlineid, b.adgroupid HAVING ppc>1 ORDER BY  ppc DESC LIMIT   1,100",
        // context);
        //
        // IRowSet row = null;
        // while ((row = rc.next()) != null) {
        // System.out.println(row);
        // }
        //
        // System.out.println("ok");
        //
        // System.out.println(System.currentTimeMillis() - start);
        // }
    }

}
