package exploring.java_concurrency;

public class RaceConditionIssue {

    public static void main(String[] args) throws InterruptedException {

        /**
         * Simular condições de concorrência é muito difícil,
         * isto é, pode ser que para poder reproduzir algum cenário
         * talvez será necessário executar excessivamente ele para
         * pegar o cenário. Talvez, ao executar essa classe vc vai
         * deparar com o resultado experado, mas uma hora ele vai
         * se apresentar a você...
         *
         */

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

    /**
     * Não é thread-safe, pois não sincroniza o acesso tanto para
     * leitura como escrita de seu estado.
     */
    static class Conta {

        private double total = 0;

        public void creditar() {
            /**
             * A instrução abaixo pode "parecer" uma instrução
             * única, mas na verdade ela está executando as seguintes instruções
             *
             * var temp = total + 1
             * this.total = temp
             *
             * Pode ser que, concorrentemente, outra thread defina
             * a variável temp com um valor desatualizado do atributo
             * total, e isso acarretará em perca de informação. Por isso,
             * proteja o estado do seu objeto utilizando locks ou utilizando
             * blocos synchronized
             */
            total++;
        }

        public double getTotal() {
            return total;
        }
    }


}
