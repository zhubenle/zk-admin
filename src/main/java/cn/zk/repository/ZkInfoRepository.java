package cn.zk.repository;

import cn.zk.entity.ZkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <br/>
 * Created on 2018/6/12 14:49.
 *
 * @author zhubenle
 */
public interface ZkInfoRepository extends JpaRepository<ZkInfo, Integer> {
    /**
     * 根据别名删除ZkInfo
     *
     * @param alias
     *         别名
     */
    void deleteZkInfoByAliasEquals(String alias);
}
