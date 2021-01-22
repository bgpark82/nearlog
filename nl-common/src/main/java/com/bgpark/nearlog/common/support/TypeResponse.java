package com.bgpark.nearlog.common.support;

import java.time.LocalDateTime;

public class TypeResponse<T> implements Response<T> {

    private int code;
    private T data;
    private LocalDateTime timestamp;

    public TypeResponse(int code, T data) {
        this.code = code;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
