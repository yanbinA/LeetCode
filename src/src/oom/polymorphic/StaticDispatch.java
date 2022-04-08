package src.oom.polymorphic;

/**
 * <p>
 * 方法静态派分
 * </p>
 *
 * @author messi
 * @package oom.polymorphic
 * @description 方法静态派分
 * @date 2022-03-02 22:21
 * @verison V1.0.0
 */
public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human human) {
        System.out.println("hello human!");
    }
    public void sayHello(Man man) {
        System.out.println("hello man!");
    }
    public void sayHello(Woman woman) {
        System.out.println("hello woman!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch dispatch = new StaticDispatch();
        dispatch.sayHello(man);
        dispatch.sayHello(woman);
    }
}
