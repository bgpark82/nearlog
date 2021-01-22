package com.bgpark.nearlog.common.support;

import java.time.LocalDateTime;

public interface Response<T> {

    int getCode();
    T getData();
    LocalDateTime getTimestamp();

    static <T> Response<T> ok(T data) { return new TypeResponse(200, data);}
}
