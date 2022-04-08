public class PrintInOrder {
    volatile int first;
    volatile int second;

    public PrintInOrder() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        first = 1;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second = first;
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        second = 2;
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {
        PrintInOrder print = new PrintInOrder();
        Thread thread1 = new Thread(() -> {
            try {
                print.first(()-> System.out.println("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                print.second(()-> System.out.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                print.third(()-> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread3.start();
        thread2.start();
        thread1.start();
    }
}
