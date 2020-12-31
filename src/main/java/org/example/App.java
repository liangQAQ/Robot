package org.example;

import org.example.config.GlobelConfig;
import org.example.hook.KeyBoardHook;
import org.example.utils.RobotUtils;

import java.awt.event.KeyEvent;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final int attackCount = 119;

    public static void main( String[] args ) throws InterruptedException {
        init();
        int count = 0;
        while(true){
            if(GlobelConfig.SWITCHFLAG){
                if(count==0 || count > attackCount){
                    Thread.sleep(1000);
                    System.out.println("暂停攻击加buff");
                    RobotUtils.presskey(KeyEvent.VK_PAGE_UP);//喂宠物
                    Thread.sleep(1000);
                    RobotUtils.presskey(KeyEvent.VK_X); //开花
                    Thread.sleep(1000);
                    RobotUtils.presskey(KeyEvent.VK_DELETE); //蓝
                    Thread.sleep(1000);
                    count = 0;
                }
                RobotUtils.presskey(KeyEvent.VK_D);
                count++;
                System.out.println("攻击["+count+"]次");
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
