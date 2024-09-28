package exploring.java_concurrency;

public class RaceConditionIssue {

    public static void main(String[] args) throws InterruptedException {

        /**
         * Simular condições de concorrência é muito difícil,
         * isto é, pode ser que para poder reproduzir algum cenário
         * talvez será necessário executar excessivamente ele para
         * pegar o cenário. Isso acontece por vários fatores,
         * como por exemplo o escalonador de threads pode ativar as
         * threads em tempos diferentes causando situações imprevisíveis.
         * Executando essa classe multiplas vezes, em um dado momento,
         * vc vai se deparar o problema de race condition
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
        var thread3 = new Thread(regraDeNegocio);

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Valor final esperado: 6000. O valor final após processamento foi: " + conta1.getTotal());
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
