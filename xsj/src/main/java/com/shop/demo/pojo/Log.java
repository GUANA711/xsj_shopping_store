package com.shop.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alice
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private int logid;
    private String openid;
    private String nickname;
    private String Operation;
    private String Method;
    private String IP;
    private String Create_time;
}
