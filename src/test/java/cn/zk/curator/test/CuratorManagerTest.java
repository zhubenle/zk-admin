package cn.zk.curator.test;

import cn.zk.app.config.CuratorClientProperties;
import cn.zk.entity.PathVO;
import cn.zk.manager.DefaultCuratorManager;
import com.alibaba.fastjson.JSONObject;
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
        CuratorClientProperties clientProperties = new CuratorClientProperties();
        clientProperties.setBaseSleepTimeMs(60000);
        clientProperties.setConnectionTimeoutMs(60000);
        clientProperties.setMaxRetries(3);
        clientProperties.setSessionTimeoutMs(60000);
        DefaultCuratorManager curatorManager = new DefaultCuratorManager("127.0.0.1:2181", clientProperties);
        curatorManager.createPath("/search/manage/test", "a", null, 0);
    }

    @Test
    public void testJson() {
        PathVO pathVO = new PathVO("/", true);
        System.out.println(JSONObject.toJSONString(pathVO));
    }
}
