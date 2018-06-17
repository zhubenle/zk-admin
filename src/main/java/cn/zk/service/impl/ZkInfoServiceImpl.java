package cn.zk.service.impl;

import cn.zk.app.config.CuratorClientProperties;
import cn.zk.common.AdminException;
import cn.zk.common.RespCode;
import cn.zk.entity.PathVO;
import cn.zk.entity.ZkInfo;
import cn.zk.manager.DefaultCuratorManager;
import cn.zk.manager.factory.CuratorManagerFactory;
import cn.zk.repository.ZkInfoRepository;
import cn.zk.service.ZkInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        zkInfoRepository.save(zkInfo);
        curatorManagerFactory.getManagerMap().put(zkInfo.getAlias(), new DefaultCuratorManager(zkInfo.getHosts(), curatorClientProperties));
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteZkInfoByAlias(String alias) {
        if (StringUtils.isEmpty(alias)) {
            throw new AdminException(RespCode.ERROR_10004);
        }
        zkInfoRepository.deleteZkInfoByAliasEquals(alias);
        curatorManagerFactory.removeManager(alias);
        log.info("删除ZkInfo, alias={}", alias);
    }

    @Override
    public List<PathVO> listZkChildrenPath(String alias, String pathName, String pathId) {
        DefaultCuratorManager curatorManager = (DefaultCuratorManager) curatorManagerFactory.getManager(alias)
                .orElseThrow(() -> new AdminException(RespCode.ERROR_10003));
        List<PathVO> result = curatorManager.listChildrenPath(StringUtils.isEmpty(pathId) ? File.separator : pathId)
                .stream()
                .map(s -> new PathVO(s, curatorManager.getPathStat(pathId + File.separator + s).getNumChildren() > 0)
                        .withId(pathId + File.separator + s))
                .collect(Collectors.toList());

        if (StringUtils.isEmpty(pathId)) {
            List<PathVO> temp = new ArrayList<>();
            temp.add(new PathVO(File.separator, true).withOpen(true).withChildren(result).withId(pathId));
            result = temp;
        }
        return result;
    }
}
