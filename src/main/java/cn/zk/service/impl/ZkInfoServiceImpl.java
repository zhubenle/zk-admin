package cn.zk.service.impl;

import cn.zk.entity.ZkInfo;
import cn.zk.repository.ZkInfoRepository;
import cn.zk.service.ZkInfoService;
import com.sun.tools.javac.util.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br/>
 * Created on 2018/6/12 15:35.
 *
 * @author zhubenle
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ZkInfoServiceImpl implements ZkInfoService {

    private final ZkInfoRepository zkInfoRepository;

    @Override
    public List<ZkInfo> listAll() {
        return zkInfoRepository.findAll();
    }

    @Override
    public ZkInfo saveOrUpdateZkInfo(ZkInfo zkInfo) {
        Assert.checkNonNull(zkInfo, "保存ZkInfo时，zkInfo不能为空");
        return zkInfoRepository.saveAndFlush(zkInfo);
    }
}
