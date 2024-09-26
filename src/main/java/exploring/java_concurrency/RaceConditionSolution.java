package exploring.java_concurrency;

public class RaceConditionSolution {

    public static void main(String[] args) throws InterruptedException {

        var conta1 = new Conta();

        Runnable regraDeNegocio = () -> {
            for (int i = 0; i < 2000; i++) {
                conta1.creditar();
            }
        };

        var thread1 = new Thread(regraDeNegocio);
        var thread2 = new Thread(regraDeNegocio);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Valor final: " + conta1.getTotal());
    }

    static class Conta {

        private double total = 0;

        // utilize locks para controlar o acesso a estado mutável
        public synchronized void creditar() {
            total++;
        }

        public double getTotal() {
            return total;
        }
    }


}
