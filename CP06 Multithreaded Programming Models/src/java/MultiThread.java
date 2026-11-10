public class MultiThread implements Runnable {
    private int threadNumber;

    public MultiThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread [" + threadNumber + "] count: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread " + threadNumber + " was interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MultiThread(1));
        Thread t2 = new Thread(new MultiThread(2));
        
        t1.start();
        t2.start();
    }
}
