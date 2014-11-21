package me.cocodrum.processpool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author xuhongfeng
 */
public class ProcessPoolConfig extends GenericObjectPoolConfig {

    private String[] command;

    public static ProcessPoolConfig defaultConfig(String[] command) {
        ProcessPoolConfig config = new ProcessPoolConfig();
        config.command(command);

        config.setMaxTotal(100);
        config.setMaxIdle(20);
        config.setMinIdle(5);

        config.setTimeBetweenEvictionRunsMillis(6000L);
        config.setTestWhileIdle(true);

        return config;
    }

    public void command(String... cmd) {
        this.command = cmd;
    }

    public String[] getCommand() {
        return command;
    }
}
