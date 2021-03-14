package com.imooc.ad.dao;

import com.imooc.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lwj32 on 2021/2/21.
 */
public interface AdUnitRepository extends JpaRepository <AdUnit,Long>{
    /**
     *
     * @param planId
     * @param unitName
     * @return
     */
    AdUnit findByPlanIdAndUnitName(Long planId,String unitName);

    List<AdUnit> findAllByUnitStatus(Integer status);
}
