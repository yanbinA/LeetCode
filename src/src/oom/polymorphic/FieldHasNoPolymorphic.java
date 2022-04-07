package src.oom.polymorphic;

/**
 * <p>
 * 字段没有多态
 * </p>
 *
 * @author messi
 * @package oom.polymorphic
 * @description 字段没有多态
 * @date 2022-03-02 23:30
 * @verison V1.0.0
 */
public class FieldHasNoPolymorphic {
    static class Father {
        public int money = 1;
        public Father() {
            money = 2;
            showMeTheMoney();
        }
        public void showMeTheMoney() {
            System.out.println("I am Father, i hava $" + money);
        }
    }

    static class Son extends Father {
        public int money = 3;
        public Son() {
            money = 4;
            showMeTheMoney();
        }
        public void showMeTheMoney() {
            System.out.println("I am Son, i hava $" + money);
        }
    }

    public static void main(String[] args) {
        Father guy = new Son();
        System.out.println("The guy has $" + guy.money);
    }
}
