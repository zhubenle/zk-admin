package cn.zk.manager;

import cn.zk.app.config.CuratorManagerProperties;

/**
 * <br/>
 * Created on 2018/6/11 16:46.
 *
 * @author zhubenle
 */
public class DefaultCuratorManager extends AbstractCuratorManager{

    public DefaultCuratorManager(String zkHostPorts, CuratorManagerProperties properties) {
        super(zkHostPorts, properties);
    }
}
