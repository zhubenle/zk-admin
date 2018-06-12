package cn.zk.service.impl;

import cn.zk.constant.DelEnum;
import cn.zk.entity.ZkInfo;
import cn.zk.repository.ZkInfoRepository;
import cn.zk.service.ZkInfoService;
import cn.zk.util.DateUtils;
import com.sun.tools.javac.util.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public void saveZkInfo(ZkInfo zkInfo) {
        Assert.checkNonNull(zkInfo, "保存ZkInfo时，zkInfo不能为空");
        Date now = DateUtils.getCurrentDateTime();
        zkInfo.setCreateTime(now);
        zkInfo.setUpdateTime(now);
        zkInfo.setDel(DelEnum.UN_DEL.getValue());
        zkInfoRepository.save(zkInfo);
    }
}
