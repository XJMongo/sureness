package com.usthe.sureness.sample.tom.dao;

import com.usthe.sureness.sample.tom.pojo.entity.AuthUserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author tomsun28
 * @date 16:43 2019-07-27
 */
public interface AuthUserDao extends JpaRepository<AuthUserDO, Long> {

    /**
     * Get user by username
     * @param username username
     * @return user
     */
    @Query("select au from AuthUserDO au where au.username = :username")
    Optional<AuthUserDO> findAuthUserByUsername(@Param("username") String username);

    /**
     * 查询当前用户所拥有的角色
     * Query the role of the current user
     * @param username username
     * @return role list
     */
    @Query("select ar.code from AuthRoleDO ar, AuthUserDO au, AuthUserRoleBindDO bind " +
            "where ar.id = bind.roleId and au.id = bind.userId and au.username = :username")
    List<String> findAccountOwnRoles(@Param("username") String username);
}
