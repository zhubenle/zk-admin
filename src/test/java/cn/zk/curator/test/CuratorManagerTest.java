package cn.zk.curator.test;

import cn.zk.app.config.CuratorManagerProperties;
import cn.zk.manager.DefaultCuratorManager;
import org.junit.Test;

/**
 * <br/>
 * Created on 2018/6/17 19:47.
 *
 * @author zhubenle
 */
public class CuratorManagerTest {

    @Test
    public void testGetPath() {
        CuratorManagerProperties clientProperties = new CuratorManagerProperties();
        clientProperties.setBaseSleepTimeMs(60000);
        clientProperties.setConnectionTimeoutMs(60000);
        clientProperties.setMaxRetries(3);
        clientProperties.setSessionTimeoutMs(60000);
        DefaultCuratorManager curatorManager = new DefaultCuratorManager("192.168.1.250:2181", clientProperties);
        curatorManager.getACLs("/dubbo/cn.iautos.gg.api.banking.pa.service.IGGAdminService/providers").forEach(acl -> {
            System.out.println(acl.getId().getId());
            System.out.println(acl.getId().getScheme());
            System.out.println(acl.getPerms());
        });
    }

    @Test
    public void testJson() {
    }
}
