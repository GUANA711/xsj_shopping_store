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
    //返回数据的总条数
    private int total;
    private List<?> selectList;
}
