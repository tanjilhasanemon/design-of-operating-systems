public class ThreadCancellationDemo {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Working... step " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Worker thread was cancelled/interrupted safely.");
            }
        });

        worker.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}

        System.out.println("Main thread interrupting worker...");
        worker.interrupt();
    }
}
