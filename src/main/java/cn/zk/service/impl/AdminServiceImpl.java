package cn.zk.service.impl;

import cn.zk.common.AdminException;
import cn.zk.common.RespCode;
import cn.zk.entity.User;
import cn.zk.repository.UserRepository;
import cn.zk.service.AdminService;
import cn.zk.util.DateUtils;
import cn.zk.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

/**
 * <br/>
 * Created on 2018/6/10 19:36.
 *
 * @author zhubenle
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> signIn(User user) {
        if (Objects.isNull(user) || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return Optional.empty();
        }
        User result = userRepository.findByUsernameEquals(user.getUsername());
        if (Objects.isNull(result)) {
            log.warn("{}登录失败, 用户不存在", user.getUsername());
            return Optional.empty();
        }
        if (!DigestUtils.sha1Hex(user.getPassword()).equals(result.getPassword())) {
            log.warn("{}登录失败, 密码错误", user.getUsername());
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public Page<User> listUserByPage(Integer page, Integer pageSize) {
        if (pageSize > 10) {
            pageSize = 10;
        }
        return userRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public void saveOrUpdateUser(User user) {
        if (Objects.nonNull(user.getId())) {
            user.setUpdateTime(DateUtils.getCurrentDateTime());
            userRepository.updateUserById(user);
            return;
        }
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new AdminException(RespCode.ERROR_10001));
    }

    @Override
    public void deleteUserById(Integer userId) {
        if (userId == 1) {
            throw new AdminException(RespCode.ERROR_10002);
        }
        userRepository.deleteById(userId);
    }
}
