package com.imooc.ad.service.Impl;

import com.imooc.ad.dao.AdCreativeRespository;
import com.imooc.ad.entity.AdCreative;
import com.imooc.ad.service.ICreativeService;
import com.imooc.ad.vo.CreativeRequest;
import com.imooc.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Qinyi.
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    private final AdCreativeRespository creativeRepository;

    @Autowired
    public CreativeServiceImpl(AdCreativeRespository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {

        AdCreative creative = creativeRepository.save(
                request.convertToEntity()
        );

        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
