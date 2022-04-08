package src.thread;

import org.junit.Test;

/**
 * <p>
 * 中断线程的方式
 * Thread.interrupt()给线程发送唤醒信号,设置该线程的中断状态位为true.
 * 如果线程处于WAITING或TIMED_WAITING状态,就会抛出InterruptedException;
 * 否则,线程什么都不处理,但在后续,指定到wait(),sleep(),join()时,会立即抛出InterruptedException
 * 更确切的说，如果线程被Object.wait, Thread.join和Thread.sleep三种方法之一阻塞，此时调用该线程的interrupt()方法，那么该线程将抛出一个 InterruptedException中断异常，从而提早地终结被阻塞状态。
 * 如果线程没有被阻塞，这时调用 interrupt()将不起作用，直到执行到wait(),sleep(),join()时,才马上会抛出 InterruptedException
 *
 * Thread.interrupted()是静态方法：内部实现是调用的当前线程的isInterrupted()，并且会重置当前线程的中断状态为false
 * Thread.isInterrupted()是实例方法，读取当前线程的中断状态，不会重置当前线程的中断状态
 * </p>
 *
 * @author messi
 * @package thread
 * @description
 * @date 2022-04-03 17:14
 * @verison V1.0.0
 */
public class InterruptTest {

    @Test
    public void testInterrupt() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("start Thread");
            for (int i = 0; i < 1000000; i++) {
                if (i == 1000000 -1) {
                    System.out.println(1);
                }
            }
            System.out.println("start sleep");
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end Thread");
        });
        thread.start();
        //Thread.sleep(1000);
        thread.interrupt();
        Thread.sleep(5000);
    }

}
