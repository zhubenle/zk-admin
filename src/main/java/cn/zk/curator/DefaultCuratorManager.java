package cn.zk.curator;

import cn.zk.app.config.CuratorClientProperties;

/**
 * <br/>
 * Created on 2018/6/11 16:46.
 *
 * @author zhubenle
 */
public class DefaultCuratorManager extends AbstractCuratorManager{

    public DefaultCuratorManager(String zkHostPorts, CuratorClientProperties properties) {
        super(zkHostPorts, properties);
    }
}
