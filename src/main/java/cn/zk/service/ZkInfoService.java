package cn.zk.service;

import cn.zk.entity.PathDataVO;
import cn.zk.entity.PathVO;
import cn.zk.entity.ZkInfo;

import java.util.List;

/**
 * <br/>
 * Created on 2018/6/12 15:35.
 *
 * @author zhubenle
 */
public interface ZkInfoService {

    /**
     * 获取所有的zk连接配置列表
     *
     * @return 列表
     */
    List<ZkInfo> listAll();

    /**
     * 添加ZkInfo
     *
     * @param zkInfo
     *         zk配置对象
     */
    void saveZkInfo(ZkInfo zkInfo);

    /**
     * 根据hosts更新zookeeper链接状态
     *
     * @param hosts
     *         连接地址
     * @param connState
     *         连接状态
     */
    void updateZkInfoConnStateByHosts(String hosts, String connState);


    /**
     * 删除zkInfo
     *
     * @param alias
     *         别名
     */
    void deleteZkInfoByAlias(String alias);

    /**
     * 重连zookeeper
     *
     * @param alias
     *         别名
     */
    void reconnectZk(String alias);

    /**
     * 获取zk指定路径下的子路径列表
     *
     * @param alias
     *         别名
     * @param pathId
     *         路径ID
     *
     * @return 子路径列表
     */
    List<PathVO> listZkChildrenPath(String alias, String pathId);

    /**
     * 删除路径
     *
     * @param alias
     *         别名
     * @param pathId
     *         路径ID
     * @param dataVersion
     *         路径数据版本
     */
    void deletePath(String alias, String pathId, Integer dataVersion);

    /**
     * 创建接节点
     *
     * @param alias
     *         别名
     * @param pathId
     *         节点路径
     * @param data
     *         节点数据
     * @param createMode
     *         节点类型
     *
     * @return 节点路径
     */
    String createPath(String alias, String pathId, String data, Integer createMode);

    /**
     * 更新节点
     *
     * @param alias
     *         别名
     * @param newPathId
     *         新节点路径
     * @param oldPathId
     *         旧节点路径
     * @param data
     *         节点数据
     * @param version
     *         节点数据版本
     * @param createMode
     *         节点类型
     *
     * @return 节点路径
     */
    String updatePath(String alias, String newPathId, String oldPathId, String data, Integer version, Integer createMode);

    /**
     * 获取指定path的数据
     *
     * @param alias
     *         别名
     * @param pathId
     *         路径
     *
     * @return 路径数据
     */
    PathDataVO getPathData(String alias, String pathId);

    /**
     * 复制指定节点粘贴到指定节点下
     *
     * @param alias
     *         别名
     * @param copy
     *         复制的节点
     * @param paste
     *         复制粘贴到的节点
     * @param newBasePaste
     *         粘贴后的新名称
     */
    void copyPastePath(String alias, String copy, String paste, String newBasePaste);
}
