package future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Shop {
    private final Random random = new Random();
    private String name;

    public Shop(String name) {
        this.name = name;
    }



    //该方法的内部实现会查询商店的数据库，但也有可能执行一些其他耗时的任务，比如联系其他外部服务（比如，商店的供应商，或者跟制造商相关的推广折扣）
    public double getPrices(String product) {
        return calculatePrice(product);
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
    }

    static List<Shop> shops=Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("FIVE"));

    public static List<String> findPrices(String product) {
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.name, shop.getPrices(product)))
                .collect(Collectors.toList());
    }

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    });

    public static List<String> findPricesAsync(String product) {
        List<CompletableFuture<String>> completableFutureList = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.name, shop.getPrices(product)), executor))
                .collect(Collectors.toList());
        return completableFutureList.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        long start=System.nanoTime();
        System.out.println(findPricesAsync("myPhone27S"));
        long duration=(System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in "+duration+" msecs");
    }

}
