package com.ct.shop.utils.id;

/**
 * @author Tao
 * @description 雪花算法生成分布式序列号
 */
public class SnowFlake {
    /**
     * 起始的时间戳：2022年12月25日   9:30分
     */
    private static final long START_STAMP = 1671931837160L;

    /**
     * 每一部分占用的位数
     */
    private static final long SEQUENCE_BIT = 12; //序列号
    private static final long MACHINE_BIT = 5; //机器标识
    private static final long DATACENTER_BIT = 5; //数据中心

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId;
    private long machineId;
    private long sequence = 0L;
    private long lastStamp = -1L;

    public SnowFlake(long datacenterId,long machineId){
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0){
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0){
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    public synchronized long nextId(){
        long currStamp = getNewStamp();
        if (currStamp < lastStamp){
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }
        if (currStamp == lastStamp){
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列号已经达到最大
            if (sequence == 0L){
                currStamp = getNextMill();
            }
        } else {
            //不同毫秒内序列号置为0
            sequence = 0L;
        }
        lastStamp = currStamp;

        return (currStamp-START_STAMP) << TIMESTAMP_LEFT
                | datacenterId << DATACENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    private long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp){
            mill = getNewStamp();
        }
        return  mill;
    }


    private long getNewStamp(){
        return System.currentTimeMillis();
    }

    public static Long getMaxDataCenterNum(){
        return MAX_DATACENTER_NUM;
    }

    public static Long getMaxMachineNum(){
        return MAX_MACHINE_NUM;
    }
}
