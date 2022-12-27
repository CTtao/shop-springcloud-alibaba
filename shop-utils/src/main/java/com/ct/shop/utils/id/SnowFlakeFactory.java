package com.ct.shop.utils.id;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Tao
 * @description 雪花算法工厂
 */
public class SnowFlakeFactory {
    /**
     * 默认数据中心id
     */
    private static final long DEFAULT_DATACENTER_ID = 1L;
    /**
     * 默认的机器id
     */
    private static final long DEFAULT_MACHINE_ID = 1L;

    /**
     * 默认雪花算法key
     */
    private static final String DEFAULT_SNOW_FLAKE = "snow_flake";

    /**
     * 缓存的SnowFlake对象
     */
    private static ConcurrentMap<String,SnowFlake> snowFlakeCache = new ConcurrentHashMap<>(2);

    public static SnowFlake getSnowFlake(long datacenterId,long machineId){
        return new SnowFlake(datacenterId,machineId);
    }

    public static SnowFlake getSnowFlake(){
        return new SnowFlake(DEFAULT_DATACENTER_ID,DEFAULT_MACHINE_ID);
    }

    public static SnowFlake getSnowFlakeFromCache(){
        SnowFlake snowFlake = snowFlakeCache.get(DEFAULT_SNOW_FLAKE);
        if (snowFlake == null){
            snowFlake = new SnowFlake(DEFAULT_DATACENTER_ID,DEFAULT_MACHINE_ID);
            snowFlakeCache.put(DEFAULT_SNOW_FLAKE,snowFlake);
        }
        return snowFlake;
    }

    public static SnowFlake getSnowFlakeByDataCenterIdAndMachineIdFromCache(Long datacenterId,Long machineId){
        if (datacenterId > SnowFlake.getMaxDataCenterNum() || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > SnowFlake.getMaxMachineNum() || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        String key = DEFAULT_SNOW_FLAKE.concat("_").concat(String.valueOf(datacenterId)).concat("_").concat(String.valueOf(machineId));

        SnowFlake snowFlake = snowFlakeCache.get(key);
        if (snowFlake == null){
            snowFlake = new SnowFlake(datacenterId, machineId);
            snowFlakeCache.put(key,snowFlake);
        }
        return snowFlake;
    }

}
