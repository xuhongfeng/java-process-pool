package me.cocodrum.processpool;

import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * @author xuhongfeng
 */
public class ProcessPool extends GenericObjectPool<Process> {

    public ProcessPool(ProcessPoolConfig config) {
        super(new ProcessFactory(config), config);

        AbandonedConfig abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        setAbandonedConfig(abandonedConfig);
    }
}
