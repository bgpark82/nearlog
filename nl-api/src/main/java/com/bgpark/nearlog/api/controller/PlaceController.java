package com.bgpark.nearlog.api.controller;

import com.bgpark.nearlog.api.dto.PlaceDto;
import com.bgpark.nearlog.api.service.PlaceService;
import com.bgpark.nearlog.common.support.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/api/v1/place")
@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final TokenStore tokenStore;
    private final PlaceService placeService;

    @GetMapping
    public Response<List<PlaceDto.PlaceRes>> getSearchPlaces(OAuth2Authentication authentication) {
//        System.out.println(principal.getName());
//        OAuth2Authentication oauth = (OAuth2Authentication) principal;
//        User principal1 = (User) oauth.getPrincipal();
//        System.out.println(principal1);

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> additionalInformation = tokenStore.readAccessToken(details.getTokenValue()).getAdditionalInformation();
        System.out.println(authentication);
        System.out.println(additionalInformation);
        return null;
    }

    @GetMapping("/recommend")
    public Response<List<PlaceDto.Schedule>> getRecommend(@RequestBody PlaceDto.Recommend req) {
        return Response.ok(placeService.getRecommendPlace(req));
    }

    @PostMapping
    public Response<PlaceDto.Save> save(@RequestBody PlaceDto.Save req) {
        placeService.save(req);
        return Response.ok(req);
    }

}
