package me.cocodrum.processpool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author xuhongfeng
 */
public class ProcessFactory extends BasePooledObjectFactory<Process> {

    private final ProcessPoolConfig config;
    private  final ProcessBuilder processBuilder;

    public ProcessFactory(ProcessPoolConfig config) {
        this.config = config;
        processBuilder = new ProcessBuilder(config.getCommand());
    }

    @Override
    public Process create() throws Exception {
        return processBuilder.start();
    }

    @Override
    public PooledObject<Process> wrap(Process process) {
        return new DefaultPooledObject<Process>(process);
    }

    @Override
    public void destroyObject(PooledObject<Process> p) throws Exception {
        p.getObject().destroy();
    }

    @Override
    public boolean validateObject(PooledObject<Process> p) {
        try {
            p.getObject().exitValue();
            return false;
        } catch (IllegalThreadStateException e) {
            return true;
        }
    }
}
