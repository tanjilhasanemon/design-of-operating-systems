import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinThread extends RecursiveTask<Long> {
    private long n;

    public ForkJoinThread(long n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 1) return n;
        ForkJoinThread f1 = new ForkJoinThread(n - 1);
        f1.fork();
        ForkJoinThread f2 = new ForkJoinThread(n - 2);
        return f2.compute() + f1.join();
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinThread task = new ForkJoinThread(10);
        long result = pool.invoke(task);
        System.out.println("Fibonacci(10) result using ForkJoinPool: " + result);
    }
}
