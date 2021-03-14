package com.imooc.ad.service;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.CreateUserRequest;
import com.imooc.ad.vo.CreateUserResponse;

/**
 * @author lwj32
 * @date 2021/2/22
 */
public interface IUserService {

    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws AdException;
}
