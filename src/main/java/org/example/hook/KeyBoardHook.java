package org.example.hook;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.example.config.GlobelConfig;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KeyBoardHook implements Runnable{

    private int start;
    private int stop;
    private int shutdown;

    private WinUser.HHOOK hhk;

    public KeyBoardHook(int start, int stop, int shutdown) {
        this.start = start;
        this.stop = stop;
        this.shutdown = shutdown;
    }

    //钩子回调函数
    private WinUser.LowLevelKeyboardProc keyboardProc = new WinUser.LowLevelKeyboardProc() {
        @Override
        public WinDef.LRESULT callback(int nCode, WinDef.WPARAM wParam, WinUser.KBDLLHOOKSTRUCT event) {
            // 输出按键值和按键时间
            if (nCode >= 0) {
                if(event.vkCode== shutdown){
                    System.out.println("结束");
                    setHookOff();
                }
                if(event.vkCode== start){
                    System.out.println("开始");
                    GlobelConfig.SWITCHFLAG = true;
                }
                if(event.vkCode== stop){
                    System.out.println("暂停");
                    GlobelConfig.SWITCHFLAG = false;
                }
            }
            return User32.INSTANCE.CallNextHookEx(hhk, nCode, wParam, null);
        }
    };

    public void run() {
        setHookOn();
    }
    // 安装钩子
    public void setHookOn(){
        System.out.println("全局控制钩子注册开始!");

        WinDef.HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        hhk = User32.INSTANCE.SetWindowsHookEx(User32.WH_KEYBOARD_LL, keyboardProc, hMod, 0);
        System.out.println("全局控制钩子注册成功!");
        System.out.println("开始:"+KeyEvent.getKeyText(start));
        System.out.println("关闭:"+KeyEvent.getKeyText(stop));
        System.out.println("停止:"+KeyEvent.getKeyText(shutdown));

        int result;
        WinUser.MSG msg = new WinUser.MSG();
        while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {
            if (result == -1) {
                setHookOff();
                break;
            } else {
                User32.INSTANCE.TranslateMessage(msg);
                User32.INSTANCE.DispatchMessage(msg);
            }
        }


    }
    // 移除钩子并退出
    public void setHookOff(){
        User32.INSTANCE.UnhookWindowsHookEx(hhk);
        System.exit(0);
    }

}
