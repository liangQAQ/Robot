package org.example;

import static com.sun.jna.platform.win32.WinUser.WM_CHAR;
import static org.junit.Assert.assertTrue;

import com.sun.jna.platform.win32.WinDef;
import org.example.hook.KeyBoardHook;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    // 创建 Robot 实例
    Robot robot;

    @Before
    public void testBefore(){
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testMouse() throws AWTException {
        // 执行完一个事件后再执行下一个
        robot.setAutoWaitForIdle(true);
        // 移动鼠标到指定屏幕坐标
        robot.mouseMove(700, 700);
        // 按下鼠标左键
        robot.mousePress(InputEvent.BUTTON1_MASK);
        // 延时100毫秒
        robot.delay(100);
        // 释放鼠标左键（按下后必须要释放, 一次点击操作包含了按下和释放）
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    @Test
    public void testPress(){
        new Thread(()-> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        robot.keyPress(KeyEvent.VK_6);
        robot.keyRelease(KeyEvent.VK_6);
    }

    @Test
    public void bindProgress() throws AWTException {
//        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "安装参数.txt - 记事本");
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "腾讯会议");
//        hwnd.getPointer().native@0x180afa
        User32.INSTANCE.SetForegroundWindow(hwnd);
        char a = 'a';
//        User32.INSTANCE.Send
        User32.INSTANCE.PostMessage(hwnd, WM_CHAR, new WinDef.WPARAM(a), new WinDef.LPARAM(a));
        User32.INSTANCE.SendMessage(hwnd, 1, new WinDef.WPARAM(a), new WinDef.LPARAM(a));
        User32.INSTANCE.SendMessage(hwnd, 2, new WinDef.WPARAM(a), new WinDef.LPARAM(a));
        User32.INSTANCE.SendMessage(hwnd, 3, new WinDef.WPARAM(a), new WinDef.LPARAM(a));
    }

    @Test
    public void show(){
        String windowName = "QQ";
        HWND hwnd = User32.INSTANCE.FindWindow(null,windowName);
        if (hwnd==null)System.out.println("Miss!");
        else {
            System.out.println("Hit!");
            boolean showed = User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE);
            System.out.println(windowName+(showed?"窗口之前可见.":"窗口之前不可见."));
        }
    }

    @Test
    public void test(){
        new Thread(new KeyBoardHook(KeyEvent.VK_F10,KeyEvent.VK_F11,KeyEvent.VK_F12)).start();
        try {
            Thread.sleep(5000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
