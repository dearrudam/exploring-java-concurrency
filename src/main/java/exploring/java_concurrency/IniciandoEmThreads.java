package exploring.java_concurrency;

public class IniciandoEmThreads {

    public static void main(String[] args) throws InterruptedException {
        Thread c=new Thread(()->
                System.out.println("estou executando " + Thread.currentThread().getName()));

        Thread c1=new Thread(()->
                System.out.println("estou executando " + Thread.currentThread().getName()));

        Thread c2=new Thread(()->
                System.out.println("estou executando " + Thread.currentThread().getName()));


        c.start();
        c.join();
        c1.start();
        c1.join();
        c2.start();
    }
}
