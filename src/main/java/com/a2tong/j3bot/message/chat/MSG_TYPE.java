package com.a2tong.j3bot.message.chat;

public enum MSG_TYPE {
    TEXT("text");
    private final String value;

    MSG_TYPE(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }

}
