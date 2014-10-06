package me.cocodrum.processpool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author xuhongfeng
 */
public class ProcessPoolConfig extends GenericObjectPoolConfig {

    private String[] command;

    public void command(String... cmd) {
        this.command = cmd;
    }

    public String[] getCommand() {
        return command;
    }
}
