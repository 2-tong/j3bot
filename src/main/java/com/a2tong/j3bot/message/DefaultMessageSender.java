package com.a2tong.j3bot.message;


import com.a2tong.j3bot.http.RestClient;
import com.a2tong.j3bot.message.chat.ChatMessage;

/**
 * TODO 默认消息发送类
 */
public class DefaultMessageSender implements MessageSender{

    RestClient restClient = new RestClient("http://127.0.0.1:9900/");

    @Override
    public MsgResponse sendText2Friend(ChatMessage msg) {
        return null;
    }

    @Override
    public MsgResponse sendText2Group(ChatMessage msg) {
        Message miraiMsg = Message.build(msg.getSender().getTarget(), (String) msg.getMsg());
        MsgResponse response;
        try {
            response = restClient.post("sendGroupMessage",miraiMsg,MsgResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            response = new MsgResponse();
            response.setCode(-1);
            response.setMs("发送请求异常");

        }
        return response;
    }

    /**
     * TODO 默认sessionKey设定方式
     */
    public static class Message{
        private static class MessageChain{
            String type;
            String text;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }

        static private String defaultKey;


        static public Message build(long target,String msg){
            MessageChain chain = new MessageChain();
            chain.setType("Plain");
            chain.setText(msg);

            return new Message(defaultKey,target,new MessageChain[]{chain});
        }

        private String sessionKey;
        private long target;
        private MessageChain[] messageChain;

        public Message(String sessionKey, long target, MessageChain[] messageChain) {
            this.sessionKey = sessionKey;
            this.target = target;
            this.messageChain = messageChain;
        }

        public static String getDefaultKey() {
            return defaultKey;
        }

        public static void setDefaultKey(String defaultKey) {
            Message.defaultKey = defaultKey;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public long getTarget() {
            return target;
        }

        public void setTarget(long target) {
            this.target = target;
        }

        public MessageChain[] getMessageChain() {
            return messageChain;
        }

        public void setMessageChain(MessageChain[] messageChain) {
            this.messageChain = messageChain;
        }
    }
}
