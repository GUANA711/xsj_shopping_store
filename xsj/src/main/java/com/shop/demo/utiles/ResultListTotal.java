package com.shop.demo.utiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Alice
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultListTotal {

    private int code=1;//返回数据是否成功，1表示不成功，0表示成功
    private long total;//返回数据的总条数
    private List<?> selectList;//返回数据
}
