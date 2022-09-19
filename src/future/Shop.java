package future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Shop {
    private final Random random = new Random();

    //该方法的内部实现会查询商店的数据库，但也有可能执行一些其他耗时的任务，比如联系其他外部服务（比如，商店的供应商，或者跟制造商相关的推广折扣）
    public double getPrices(String product) {
        //
        return 0.0;
    }

    public Future<Double> getPricesAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
//        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
//        new Thread(() -> {
//            try {
//                completableFuture.complete(calculatePrice(product));
//            } catch (Exception e) {
//                completableFuture.completeExceptionally(e);
//            }
//        }).start();
//        return completableFuture;
    }




    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0)+product.charAt(1);
    }

    /**
     * 模拟延时
     */
    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("test exception");
    }

    public static void main(String[] args) {
        Shop shop = new Shop();
        Future<Double> product = shop.getPricesAsync("product");
        try {
            Double price = product.get();
            System.out.println(price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
