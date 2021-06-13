package com.a2tong.j3bot.message;

/**
 * TODO
 *
 * @author liwei04
 * @time 2021年06月10日 11:48
 */
public class ChatMessage {

    public static ChatMessage buildMessageForReturn(ChatMessage receiveMessage,MSG_TYPE type,Object context) {
        ChatMessage msg = new ChatMessage();
        msg.setTarget(receiveMessage.getSource());
        msg.setMsgType(type);
        msg.setTargetType(receiveMessage.getSourceType());
        msg.setMsg(context);
        return msg;
    }

    public static enum MSG_TYPE {
        TEXT("text");
        private final String value;

        MSG_TYPE(String value) {
            this.value = value;
        }

        String getValue() {
            return this.value;
        }

    }

    public static enum TARGET_TYPE {
        FRIEND("friend"), GROUP("group");
        private final String value;

        TARGET_TYPE(String value) {
            this.value = value;
        }

        String getValue() {
            return this.value;
        }
    }

    private Object msg;

    private MSG_TYPE msgType;

    private TARGET_TYPE targetType;

    private TARGET_TYPE sourceType;

    //发送者qq
    private long source;

    private long target;


    public long getSource() {
        return source;
    }

    public void setSource(long source) {
        this.source = source;
    }

    public TARGET_TYPE getSourceType() {
        return sourceType;
    }

    public void setSourceType(TARGET_TYPE sourceType) {
        this.sourceType = sourceType;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public MSG_TYPE getMsgType() {
        return msgType;
    }

    public void setMsgType(MSG_TYPE msgType) {
        this.msgType = msgType;
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
