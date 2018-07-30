package cn.zk.manager;

import cn.zk.app.config.CuratorManagerProperties;
import cn.zk.common.AdminException;
import cn.zk.common.RespCode;
import cn.zk.manager.observer.ConnStateObserverDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.ZooTrace;

import java.util.List;
import java.util.Objects;
import java.util.Observable;

/**
 * <br/>
 * Created on 2018/6/11 16:06.
 *
 * @author zhubenle
 */
@Slf4j
public abstract class AbstractCuratorManager extends Observable implements ConnectionStateListener {

    protected final static String DEFAULT_CHARSET = "UTF-8";

    protected final CuratorFramework client;

    public AbstractCuratorManager(String zkHostPorts, CuratorManagerProperties properties) {
        super();
        log.debug("zookeeper【{}】连接初始化...", zkHostPorts);
        RetryPolicy retryPolicy = new RetryNTimes(properties.getMaxRetries(), 6000);
        client = CuratorFrameworkFactory.builder().connectString(zkHostPorts).sessionTimeoutMs(properties.getSessionTimeoutMs())
                .connectionTimeoutMs(properties.getConnectionTimeoutMs()).retryPolicy(retryPolicy).build();
        client.getConnectionStateListenable().addListener(this);
        client.start();
    }

    public void close() {
        ZooTrace.logTraceMessage(log, ZooTrace.getTextTraceLevel(), "关闭zookeeper连接...");
        log.info("关闭zookeeper连接...");
        if (Objects.nonNull(client)) {
            client.close();
        }
        deleteObservers();
    }

    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {
        setChanged();
        notifyObservers(new ConnStateObserverDTO(newState, client.getZookeeperClient().getCurrentConnectionString()));
        if (ConnectionState.LOST.equals(newState)) {
            close();
        }
    }

    public void checkState() {
        if (!client.getZookeeperClient().isConnected()) {
            throw new AdminException(RespCode.ERROR_10005);
        }
    }

    /**
     * 获取父路径下的子路径列表
     *
     * @param parentPath
     *         父路径
     *
     * @return 子路径列表
     */
    @SneakyThrows
    public List<String> listChildrenPath(String parentPath) {
        log.debug("获取路径{}子路径名称列表", parentPath);
        checkState();
        return client.getChildren().forPath(parentPath);
    }

    @SneakyThrows
    public void deletePath(String path, Integer version) {
        log.debug("删除路径{}, version={}", path, version);
        checkState();
        client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(version).forPath(path);
    }

    @SneakyThrows
    public String createPath(String path, String data, List<ACL> acls, int createMode) {
        log.debug("创建路径{}, data={}, createMode={}", path, data, createMode);
        checkState();
        return client.create().creatingParentsIfNeeded().forPath(path, data.getBytes());
    }

    /**
     * 检查指定路径是否存在
     *
     * @param path
     *         路径
     *
     * @return 是否存在
     */
    @SneakyThrows
    public boolean checkPathExist(String path) {
        log.debug("检查路径{}是否存在", path);
        checkState();
        return Objects.nonNull(getPathStat(path));
    }

    @SneakyThrows
    public Stat getPathStat(String path) {
        log.debug("获取路径{}的元信息", path);
        checkState();
        return client.checkExists().forPath(path);
    }

    @SneakyThrows
    public String getPathData(String path) {
        log.debug("获取路径{}的数据", path);
        checkState();
        byte[] data = client.getData().forPath(path);
        return Objects.nonNull(data) ? new String(data) : "";
    }

    @SneakyThrows
    public String getPathData(String path, Stat stat) {
        log.debug("获取路径{}的数据", path);
        checkState();
        byte[] data = client.getData().storingStatIn(stat).forPath(path);
        return Objects.nonNull(data) ? new String(data) : "";
    }

    @SneakyThrows
    public Stat setPathData(String path, String data, Integer version) {
        log.debug("设置路径{}的数据", path);
        checkState();
        return client.setData().withVersion(version).forPath(path, data.getBytes());
    }

    @SneakyThrows
    public Stat setACLs(String path, List<ACL> acls, Integer version) {
        log.debug("设置路径{}的ACL", path);
        checkState();
        return client.setACL().withACL(acls).forPath(path);
    }

    @SneakyThrows
    public List<ACL> getACLs(String path) {
        log.debug("获取路径{}的ACL", path);
        checkState();
        return client.getACL().forPath(path);
    }
}
