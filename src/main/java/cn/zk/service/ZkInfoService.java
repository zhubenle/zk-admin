package cn.zk.service;

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
     * 删除zkInfo
     *
     * @param alias
     *         别名
     */
    void deleteZkInfoByAlias(String alias);

    /**
     * 获取zk指定路径下的子路径列表
     *
     * @param alias
     *         别名
     * @param pathName
     *         路径
     * @param pathId
     *         路径ID
     *
     * @return 子路径列表
     */
    List<PathVO> listZkChildrenPath(String alias, String pathName, String pathId);
}
