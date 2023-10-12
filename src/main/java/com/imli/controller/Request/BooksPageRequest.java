package com.imli.controller.Request;

import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class BooksPageRequest extends BaseRequest{
    private String bookName;
    private String bookStyle;
}
