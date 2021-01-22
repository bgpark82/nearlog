package com.bgpark.nearlog.api.controller;

import com.bgpark.nearlog.api.dto.PlanDto;
import com.bgpark.nearlog.api.service.PlanService;
import com.bgpark.nearlog.common.support.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    private Response<PlanDto.Make> make(@RequestBody PlanDto.Make req) {
        return Response.ok(planService.autoMake(req));
    }
}
