package cn.zk.manager.factory;

import cn.zk.manager.AbstractCuratorManager;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * <br/>
 * Created on 2018/6/12 18:38.
 *
 * @author zhubenle
 */
@Component
public class CuratorManagerFactory {

    private Map<String, AbstractCuratorManager> managerMap;

    /**
     * 获取manager
     *
     * @param alias
     *         key
     *
     * @return curator管理对象
     */
    public Optional<AbstractCuratorManager> getManager(String alias) {
        return Optional.ofNullable(managerMap.get(alias));
    }

    public void setManagerMap(Map<String, AbstractCuratorManager> managerMap) {
        this.managerMap = managerMap;
    }

    public Map<String, AbstractCuratorManager> getManagerMap() {
        return managerMap;
    }

    public void removeManager(String alias) {
        Optional.ofNullable(managerMap.remove(alias)).ifPresent(AbstractCuratorManager::close);
    }
}
