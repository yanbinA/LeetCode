package src.oom.stackFrame;

/**
 * <p>
 * 局部变量表槽复用对垃圾回收的影响
 * </p>
 *
 * @author messi
 * @package oom.stackFrame
 * @description 局部变量表槽复用对垃圾回收的影响
 * @date 2022-02-28 22:57
 * @verison V1.0.0
 */
public class StackFrame {
    //-verbose:gc
    //public static void main(String[] args) {
    //    byte[] bytes = new byte[64 * 1024 * 1024];
    //    System.gc();
    //}
    public static void main(String[] args) {
        {
            byte[] bytes = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }

}
