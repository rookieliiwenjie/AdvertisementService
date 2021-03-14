package com.imooc.ad.service.Impl;

import com.imooc.ad.constant.ConstantsCode;
import com.imooc.ad.dao.AdUserRepository;
import com.imooc.ad.entity.AdUser;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.service.IUserService;
import com.imooc.ad.utils.CommonUtils;
import com.imooc.ad.vo.CreateUserRequest;
import com.imooc.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lwj32
 * @date 2021/2/22
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository adUserRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository adUserRepository) {
        this.adUserRepository = adUserRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws AdException {
        if (!createUserRequest.validate()) {
            throw new AdException(ConstantsCode.REQUEST_PARAM_ERROR.getCode());
        }
        AdUser oldUser = adUserRepository.
                findByUsername(createUserRequest.getUsername());
        if (oldUser != null) {
            throw new AdException(ConstantsCode.SAME_NAME_ERROR.getCode());
        }

        AdUser newUser = adUserRepository.save(new AdUser(
                createUserRequest.getUsername(),
                CommonUtils.md5(createUserRequest.getUsername())
        ));

        return new CreateUserResponse(
                newUser.getId(), newUser.getUsername(), newUser.getToken(),
                newUser.getCreateTime(), newUser.getUpdateTime()
        );
    }
}
