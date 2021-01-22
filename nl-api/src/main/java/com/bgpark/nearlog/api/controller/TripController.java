package com.bgpark.nearlog.api.controller;

import com.bgpark.nearlog.api.dto.TripDto;
import com.bgpark.nearlog.api.service.TripService;
import com.bgpark.nearlog.common.support.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping
    public Response<TripDto.Save> save(@RequestBody TripDto.Save req) {
        tripService.save(req);
        return Response.ok(req);
    }

}
