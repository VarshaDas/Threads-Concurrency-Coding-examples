package Executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//Implement a program that uses the ExecutorService to invoke multiple Callable tasks
// and obtain their results as a list of Future objects.
public class CallableFutureRealWorldProblem {
    public static void main(String[] args) {
        List<String> symbols = List.of("ABC", "PQR", "TFGF", "YEDS", "PFS");
        List<Future> futures = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        for (String symbol : symbols) {
            Callable<Double> stockSymbolTask = new StockPriceFetcher(symbol);
            System.out.println("submitting for "+symbol);
            Future<Double> future = executorService.submit(stockSymbolTask);
            System.out.println("Future = "+future);
            futures.add(future);
        }
        executorService.shutdown();

        for(int i = 0 ;i<5;i++){
            try {
//                while(!futures.get(i).isDone()) {
//                    Thread.sleep(1000);
//                    System.out.println("Waiting ");
//                }
                System.out.println("Stock from " + symbols.get(i) + " price = " + futures.get(i).get() + " future status " + futures.get(i));

            } catch(InterruptedException | ExecutionException e){
                System.out.println(e);
            }
        }
    }
}

    class StockPriceFetcher implements Callable<Double>{
        private String stockSymbol;
        public StockPriceFetcher(String stockSymbol) {
            this.stockSymbol = stockSymbol;
        }

        @Override
        public Double call() throws Exception {
            Thread.sleep(5000);
            return Math.random() * 100;
        }

}
