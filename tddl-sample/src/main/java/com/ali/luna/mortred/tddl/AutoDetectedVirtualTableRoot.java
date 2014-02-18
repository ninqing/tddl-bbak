package com.ali.luna.mortred.tddl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;

import com.taobao.tddl.common.exception.TddlException;
import com.taobao.tddl.rule.TableRule;
import com.taobao.tddl.rule.VirtualTableRoot;

/**
 * 继承自TDDL VirtualTableRoot的自定义TabelROOT. 功能说明： 自动查找并加载classpath下的所有的TableRule文件
 * 
 * @author tongfeng.dhy
 */
public class AutoDetectedVirtualTableRoot extends VirtualTableRoot implements ApplicationContextAware {

    public static final String TABLERULE_CLASS = "com.taobao.tddl.interact.rule.TableRule";

    private ApplicationContext context;

    /**
     * 执行初始化.
     * 
     * @throws TddlException
     */
    @Override
    public void init() throws TddlException {
        Map<String, TableRule> vts = new HashMap<String, TableRule>();
        String[] tbeanNames = context.getBeanNamesForType(TableRule.class);
        for (String name : tbeanNames) {
            Object obj = context.getBean(name);
            vts.put(name, (TableRule) obj);
        }
        setTableRules(vts);
        super.init();
    }

    /**
     * 加载TableRule类Class.
     * 
     * @return
     */
    private Class<?> getClz() {
        Class<?> c2;
        try {
            c2 = ClassUtils.forName(TABLERULE_CLASS);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        return c2;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
