package cn.zk.manager;

import cn.zk.app.config.CuratorManagerProperties;
import cn.zk.manager.observer.ObserverDTO;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.state.ConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/>
 * Created on 2018/6/11 16:46.
 *
 * @author zhubenle
 */
public class DefaultCuratorManager extends AbstractCuratorManager{

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultCuratorManager.class);

    public DefaultCuratorManager(String zkHostPorts, CuratorManagerProperties properties) {
        super(zkHostPorts, properties);
    }

    @Override
    public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
        //未使用
    }

    @Override
    public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
        if (TreeCacheEvent.Type.NODE_ADDED.equals(event.getType()) || TreeCacheEvent.Type.NODE_UPDATED.equals(event.getType())
                || TreeCacheEvent.Type.NODE_REMOVED.equals(event.getType())) {
            ChildData childData = event.getData();
            String value = new String(childData.getData());
            String path = childData.getPath();
            setChanged();
            notifyObservers(new ObserverDTO(ObserverDTO.Type.CHILD_EVENT, path));
        }
    }

    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {
        setChanged();
        notifyObservers(new ObserverDTO(ObserverDTO.Type.CONN_STATE, newState, client.getZookeeperClient().getCurrentConnectionString()));
        if (ConnectionState.LOST.equals(newState)) {
            close();
        }
    }
}
