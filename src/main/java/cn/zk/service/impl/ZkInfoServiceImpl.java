package cn.zk.service.impl;

import cn.zk.app.config.CuratorClientProperties;
import cn.zk.entity.ZkInfo;
import cn.zk.manager.DefaultCuratorManager;
import cn.zk.manager.factory.CuratorManagerFactory;
import cn.zk.repository.ZkInfoRepository;
import cn.zk.service.ZkInfoService;
import org.springframework.util.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br/>
 * Created on 2018/6/12 15:35.
 *
 * @author zhubenle
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ZkInfoServiceImpl implements ZkInfoService {

    private final ZkInfoRepository zkInfoRepository;
    private final CuratorManagerFactory curatorManagerFactory;
    private final CuratorClientProperties curatorClientProperties;

    @Override
    public List<ZkInfo> listAll() {
        return zkInfoRepository.findAll();
    }

    @Override
    public void saveZkInfo(ZkInfo zkInfo) {
        Assert.notNull(zkInfo, "保存ZkInfo，zkInfo不能为空");
        zkInfoRepository.save(zkInfo);
        curatorManagerFactory.getManagerMap().put(zkInfo.getAlias(), new DefaultCuratorManager(zkInfo.getHosts(), curatorClientProperties));
    }

    @Override
    public void deleteZkInfoByAlias(String alias) {
        Assert.hasText(alias, "删除ZkInfo，alias不能为空");
        zkInfoRepository.deleteZkInfoByAliasEquals(alias);
        curatorManagerFactory.removeManager(alias);
        log.info("删除ZkInfo, alias={}", alias);
    }
}
