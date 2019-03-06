package com.zql.VO;

import lombok.Data;

/**
 * Created by 张启磊 on 2019-3-6.
 */
@Data
public class ResultVo<T> {
    private String code;
    private String msg;
    private T data;
}
