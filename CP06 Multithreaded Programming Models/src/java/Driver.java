public class Driver {
    public static void main(String[] args) {
        System.out.println("Main thread running. Starting worker threads...");
        Thread t1 = new Thread(() -> System.out.println("Worker thread executing..."));
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread terminating.");
    }
}
