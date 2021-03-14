package com.imooc.ad.dao;

import com.imooc.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by lwj32 on 2021/2/21.
 */
public interface AdUserRepository extends JpaRepository<AdUser,Long> {
    /**
     *  根据用户名查询用户信息
     * @param userName
     * @return
     */
    AdUser findByUsername(String userName);
}
