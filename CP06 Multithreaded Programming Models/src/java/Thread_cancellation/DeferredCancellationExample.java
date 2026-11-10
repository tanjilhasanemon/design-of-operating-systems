public class DeferredCancellationExample {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            boolean done = false;
            while (!done) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Deferred cancellation detected. Cleaning up resources...");
                    done = true;
                } else {
                    System.out.println("Processing chunk safely...");
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        // Restore interrupted flag if sleep throws exception
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        worker.start();
        try { Thread.sleep(1200); } catch (InterruptedException e) {}
        worker.interrupt();
    }
}
