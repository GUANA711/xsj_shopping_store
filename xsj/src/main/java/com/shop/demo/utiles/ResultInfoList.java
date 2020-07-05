package com.shop.demo.utiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfoList {
    private long total;
    private List<?> selectList;
}
