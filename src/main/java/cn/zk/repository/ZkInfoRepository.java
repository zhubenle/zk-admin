package cn.zk.repository;

import cn.zk.entity.ZkInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

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

    /**
     * 根据alias获取ZkInfo
     *
     * @param alias
     *         别名
     *
     * @return ZkInfo
     */
    ZkInfo getZkInfoByAliasEquals(String alias);

    /**
     * 更新连接状态
     *
     * @param hosts
     *         连接地址
     * @param connState
     *         连接状态
     */
    @Transactional(rollbackOn = {Exception.class})
    @Modifying
    @Query(value = "update ZkInfo z set z.connState = :#{#connState} where z.hosts = :#{#hosts}")
    void updateConnStateByHosts(@Param("hosts") String hosts, @Param("connState") String connState);

}
