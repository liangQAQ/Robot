package org.example.config;

import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

public class GlobelConfig {

    //开关控制
    public static volatile Boolean SWITCHFLAG = false;

    public static volatile ReentrantLock LOCK = new ReentrantLock();

    //机器人实体
    public static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
