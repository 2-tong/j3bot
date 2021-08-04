package com.a2tong.j3bot.message.chat;

public enum TARGET_TYPE {
    FRIEND("friend"), GROUP("group");
    private final String value;

    TARGET_TYPE(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}
