package com.a2tong.j3bot.message.chat;

public class MsgTarget {
    TARGET_TYPE targetType;
    long target;

    public MsgTarget(TARGET_TYPE targetType, long target) {
        this.targetType = targetType;
        this.target = target;
    }

    public MsgTarget() {
    }


    public TARGET_TYPE getTargetType() {
        return targetType;
    }

    public void setTargetType(TARGET_TYPE targetType) {
        this.targetType = targetType;
    }

    public long getTarget() {
        return target;
    }

    public void setTarget(long target) {
        this.target = target;
    }
}