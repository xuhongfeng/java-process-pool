package me.cocodrum.processpool.sample;

import me.cocodrum.processpool.ProcessPool;
import me.cocodrum.processpool.ProcessPoolConfig;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author xuhongfeng
 */
public class Md5ProcessPool {

    public static void main(String[] args) {
        ProcessPoolConfig config = new ProcessPoolConfig();
        config.command("java", "-cp", "/Users/xuhongfeng/tmp/testJNI/target/classes", "me.cocodrum.test.jni.TestJni");

        ProcessPool pool = new ProcessPool(config);

        try {
            final Process process = pool.borrowObject();
            try {
                DataOutputStream dos = new DataOutputStream(process.getOutputStream());
                dos.writeInt(1);
                dos.flush();
                dos.writeInt(2);
                dos.flush();

                new Thread() {
                    @Override
                    public void run() {
                        DataInputStream dis = new DataInputStream(process.getInputStream());
                        while (true) {
                            try {
                                int v = dis.readInt();
                                System.out.println("output : " + v);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                byte[] buf = new byte[1024];
                                int n = process.getErrorStream().read(buf);
                                String str = new String(buf, 0, n);
                                System.out.print(str);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                process.waitFor();
            } finally {
                pool.returnObject(process);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
