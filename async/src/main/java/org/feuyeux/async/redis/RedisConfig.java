package org.feuyeux.async.redis;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yukong.lxx
 * @date 2018/12/19 2:25 PM
 */
@Slf4j
@Data
public class RedisConfig {
    private String host;
    private Integer port;
    private String pwd;
    private int soTimeout = 100000;
    private int connectionTimeout = 100000;
    private int maxTotal = 128;
    private int minIdle = 16;
    private int maxIdle = 128;
    private int maxWaitMillis = 3000;
    private int asyncGetTimeout = 5000;
}
