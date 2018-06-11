package cn.zk.service.impl;

import cn.zk.entity.User;
import cn.zk.repository.UserRepository;
import cn.zk.service.AdminService;
import cn.zk.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (Objects.isNull(user) || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())) {
            return Optional.empty();
        }
        User result = userRepository.findByEmailEquals(user.getEmail());
        if (Objects.isNull(result)) {
            log.warn("{}登录失败, 用户不存在", user.getEmail());
            return Optional.empty();
        }
        if (!DigestUtils.sha1Hex(user.getPassword()).equals(result.getPassword())) {
            log.warn("{}登录失败, 密码错误", user.getEmail());
            return Optional.empty();
        }
        return Optional.of(result);
    }
}
