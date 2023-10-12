package com.imli.controller.Request;

import lombok.Data;

@Data
public class BaseRequest {
    private Integer page = 1;
    private Integer pageSize = 10;
}
