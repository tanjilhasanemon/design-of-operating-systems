public class ThreadLocalDemo {
    private static ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Runnable task = () -> {
            int randomVal = (int)(Math.random() * 100);
            threadLocalValue.set(randomVal);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {}
            System.out.println("Thread " + Thread.currentThread().getName() + " local value: " + threadLocalValue.get());
        };

        Thread t1 = new Thread(task, "Worker-1");
        Thread t2 = new Thread(task, "Worker-2");

        t1.start();
        t2.start();
    }
}
