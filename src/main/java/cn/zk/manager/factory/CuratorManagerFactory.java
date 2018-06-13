package cn.zk.manager.factory;

import cn.zk.manager.AbstractCuratorManager;
import org.springframework.stereotype.Component;

import java.util.Map;

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
     * @param key
     *         key
     *
     * @return curator管理对象
     */
    public AbstractCuratorManager getManager(String key) {
        return managerMap.get(key);
    }

    public void setManagerMap(Map<String, AbstractCuratorManager> managerMap) {
        this.managerMap = managerMap;
    }
}
