package exploring.java_concurrency;

import java.util.concurrent.TimeUnit;

public class WelcomeToExecutors {

    public static void main(String[] args) throws InterruptedException {
        var executor = java.util.concurrent.Executors.newFixedThreadPool(3);
        executor.execute(()->
                System.out.println("estou executando " + Thread.currentThread().getName()));

        executor.execute(()->
                System.out.println("estou executando " + Thread.currentThread().getName()));

        executor.execute(()->
                System.out.println("estou executando " + Thread.currentThread().getName()));

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

    }
}
