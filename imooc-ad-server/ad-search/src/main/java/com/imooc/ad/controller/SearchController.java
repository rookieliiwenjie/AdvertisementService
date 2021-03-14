package com.imooc.ad.controller;

import com.imooc.ad.annotation.IgnoreResponseAdvice;
import com.imooc.ad.client.SponsorClient;
import com.imooc.ad.client.vo.AdPlan;
import com.imooc.ad.client.vo.AdPlanGetRequest;
import com.imooc.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author lwj32
 * @date 2021/3/4
 */
@RestController
@Slf4j
public class SearchController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SponsorClient sponsorClient;

    @IgnoreResponseAdvice
        @PostMapping("/getPlan")
    public CommonResponse<List<AdPlan>> getAdPlan(@RequestBody AdPlanGetRequest adPlanGetRequest) {
        return restTemplate.postForEntity("http://euraka-client-ad-spanor/ad-sponsor/get/adPlan", adPlanGetRequest, CommonResponse.class).getBody();

    }

    @PostMapping("/getPlan2")
    public CommonResponse<List<AdPlan>> getAdPlan2(@RequestBody AdPlanGetRequest adPlanGetRequest) {
        return sponsorClient.getAdPlans(adPlanGetRequest);

    }

}
