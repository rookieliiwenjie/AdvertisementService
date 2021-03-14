package com.imooc.ad.dao;

import com.imooc.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lwj32 on 2021/2/21.
 */
public interface AdPlanRepository extends JpaRepository<AdPlan,Long> {
    /**
     * 通过id和userid查询
     * @param id
     * @param userId
     * @return
     */
    AdPlan findByIdAndUserId(Long id,Long userId);

    /**
     * T通过id和user
      * @param id
     * @param userId
     * @return
     */
    List<AdPlan> findAllByIdInAndUserId(List<Long> id,Long userId);

    /**
     * 通过userId和计划名称查询
     * @param userId
     * @param planName
     * @return
     */
    AdPlan findByUserIdAndPlanName(Long userId,String planName);

    List<AdPlan> findAllByPlanStatus(Integer status);

}
