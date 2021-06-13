package com.a2tong.j3bot.config;

public class SysConfig {
    private String[] listenServers;
    private String[] unListenSerendipity;

    public String[] getListenServers() {
        return listenServers;
    }

    public void setListenServers(String[] listenServers) {
        this.listenServers = listenServers;
    }

    public String[] getUnListenSerendipity() {
        return unListenSerendipity;
    }

    public void setUnListenSerendipity(String[] unListenSerendipity) {
        this.unListenSerendipity = unListenSerendipity;
    }
}
