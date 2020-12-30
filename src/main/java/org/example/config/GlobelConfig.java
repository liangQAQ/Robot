package org.example.config;

import java.awt.*;

public class GlobelConfig {

    //开关控制
    public static volatile Boolean SWITCHFLAG = false;

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
