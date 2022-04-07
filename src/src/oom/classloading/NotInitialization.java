package src.oom.classloading;

/**
 * <p>
 * NotInitialization
 * </p>
 *
 * @author messi
 * @package oom.classloading
 * @description NotInitialization
 * @date 2022-01-16 16:28
 * @verison V1.0.0
 */
public class NotInitialization {
    public static void main(String[] args) {
        //被动引用,通过子类引用父类的静态字段,不会导致子类初始化
        //System.out.println(SubClass.value);
        //通过数组定义来引用类,不会触发此类的初始化
        SuperClass[] superClasses = new SuperClass[10];
    }
}
