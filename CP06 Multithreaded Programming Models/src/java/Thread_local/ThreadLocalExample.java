public class ThreadLocalExample {
    private static ThreadLocal<String> userContext = ThreadLocal.withInitial(() -> "Guest");

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            userContext.set("User_Alice");
            System.out.println("Context in " + Thread.currentThread().getName() + ": " + userContext.get());
        }, "Thread-A");

        Thread t2 = new Thread(() -> {
            userContext.set("User_Bob");
            System.out.println("Context in " + Thread.currentThread().getName() + ": " + userContext.get());
        }, "Thread-B");

        t1.start();
        t2.start();
    }
}
