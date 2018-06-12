package cn.zk.service;

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
}
