package org.example.utils;

import org.example.App;
import org.example.config.GlobelConfig;
import org.example.constants.Constants;

import java.awt.*;

public class RobotUtils {

    private static Robot r = GlobelConfig.robot;

    public static void presskey(int k) throws InterruptedException {
        r.keyPress(k);
        r.delay(Constants.pressTime);
        r.keyRelease(k);
        Thread.sleep(Constants.IntervalTime);
    }
}
