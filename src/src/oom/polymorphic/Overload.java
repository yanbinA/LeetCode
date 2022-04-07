package src.oom.polymorphic;

/**
 * <p>
 * 重载方法匹配优先级
 * </p>
 *
 * @author messi
 * @package oom.polymorphic
 * @description 重载方法匹配优先级
 * @date 2022-03-02 22:47
 * @verison V1.0.0
 */
public class Overload {
    //public static void sayHello(Object arg) {
    //    System.out.println("hello object");
    //}

    //public static void sayHello(int arg) {
    //    System.out.println("hello int");
    //}

    //public static void sayHello(long arg) {
    //    System.out.println("hello long");
    //}

    //public static void sayHello(Character arg) {
    //    System.out.println("hello Character" + arg.getClass());
    //}

    //public static void sayHello(char arg) {
    //    System.out.println("hello char");
    //}

    //public static void sayHello(char... arg) {
    //    System.out.println("hello char...");
    //}
    //
    //public static void sayHello(int... arg) {
    //    System.out.println("hello int...");
    //}

    public static void sayHello(Object... arg) {
        System.out.println("hello object...");
    }

    public static void main(String[] args) {
        Overload.sayHello('a');
    }


}
