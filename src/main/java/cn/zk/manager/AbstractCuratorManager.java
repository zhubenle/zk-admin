package cn.zk.manager;

import cn.zk.app.config.CuratorClientProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CreateBackgroundModeStatACLable;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.ZooTrace;

import java.util.List;
import java.util.Objects;

/**
 * <br/>
 * Created on 2018/6/11 16:06.
 *
 * @author zhubenle
 */
@Slf4j
public abstract class AbstractCuratorManager implements ConnectionStateListener {

    protected final static String DEFAULT_CHARSET = "UTF-8";

    protected final CuratorFramework client;

    public AbstractCuratorManager(String zkHostPorts, CuratorClientProperties properties) {
        log.info("zookeeper【{}】连接初始化...", zkHostPorts);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(properties.getBaseSleepTimeMs(), properties.getMaxRetries());
        client = CuratorFrameworkFactory.builder().connectString(zkHostPorts).sessionTimeoutMs(properties.getSessionTimeoutMs())
                .connectionTimeoutMs(properties.getConnectionTimeoutMs()).retryPolicy(retryPolicy).build();
        client.getConnectionStateListenable().addListener(this);
        client.start();
    }

    public void close() {
        ZooTrace.logTraceMessage(log, ZooTrace.getTextTraceLevel(), "关闭zookeeper连接...");
        if (Objects.nonNull(client)) {
            client.close();
        }
    }

    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {
        log.warn("连接状态改变, 最新连接状态: {}", newState);
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
        return client.getChildren().forPath(parentPath);
    }

    @SneakyThrows
    public void deletePath(String path, Integer version) {
        client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(version).forPath(path);
    }

    @SneakyThrows
    public String createPath(String path, String data, List<ACL> acls, int createMode) {
        CreateBackgroundModeStatACLable createBackgroundModeStatACLable = client.create().compressed();
        if (Objects.nonNull(acls)) {
            createBackgroundModeStatACLable.withACL(acls);
        }
        if (Objects.nonNull(createMode)) {
            createBackgroundModeStatACLable.withMode(CreateMode.fromFlag(createMode));
        }
        return createBackgroundModeStatACLable.forPath(path, data.getBytes(DEFAULT_CHARSET));
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
        return Objects.nonNull(getPathStat(path));
    }

    @SneakyThrows
    public Stat getPathStat(String path) {
        return client.checkExists().forPath(path);
    }

    @SneakyThrows
    public String getPathData(String path, Stat stat) {
        return new String(client.getData().storingStatIn(stat).forPath(path), DEFAULT_CHARSET);
    }
}
