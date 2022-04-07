package src.oom.classloading;

/**
 * <p>
 * SuperClass
 * </p>
 *
 * @author messi
 * @package oom.classloading
 * @description SuperClass
 * @date 2022-01-16 16:26
 * @verison V1.0.0
 */
public class SuperClass {
    static {
        value = 321;
        //System.out.println(value);//类变量定义在静态语句块之后,编译器会提示"非法前向引用"
        System.out.println("SuperClass init!");
        if (true) {
            while (true){

            }
        }
    }

    public static int value;

    public static void main(String[] args) {
        System.out.println(SuperClass.value);
    }
}
