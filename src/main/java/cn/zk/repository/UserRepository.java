package cn.zk.repository;

import cn.zk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * <br/>
 * Created on 2018/6/3 21:06.
 *
 * @author zhubenle
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据用户名查找用户
     *
     * @param username
     *         用户名
     *
     * @return 用户对象
     */
    User findByUsernameEquals(String username);

    /**
     * 更新用户
     *
     * @param user
     *         更新用户
     */
    @Transactional(rollbackOn = {Exception.class})
    @Modifying
    @Query(value = "update User u set u.username = :#{#user.username}, u.password = :#{#user.password}, " +
            "u.email = :#{#user.email}, u.updateTime = :#{#user.updateTime} where u.id = :#{#user.id}")
    void updateUserById(@Param("user") User user);
}
