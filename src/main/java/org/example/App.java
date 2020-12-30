package org.example;

import org.example.config.GlobelConfig;
import org.example.hook.KeyBoardHook;
import org.example.utils.RobotUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        init();
        while(true){
            if(GlobelConfig.SWITCHFLAG){
                RobotUtils.presskey(KeyEvent.VK_D);
            }else{
                //阻塞
                System.out.println("wait");
                Thread.sleep(10000);
            }
        }
    }

    /**
     * 初始化
     */
    private static void init() {
        try {
            //注册全局控制钩子
            new Thread(new KeyBoardHook(KeyEvent.VK_F10,KeyEvent.VK_F11,KeyEvent.VK_F12)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
