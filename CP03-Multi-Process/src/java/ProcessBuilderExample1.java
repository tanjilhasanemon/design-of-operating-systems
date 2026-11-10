import java.io.IOException;

public class ProcessBuilderExample1 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder();
        System.out.println("Operating System: " + System.getProperty("os.name"));
        System.out.println("Java Version: " + System.getProperty("java.version"));
        
        pb.command("echo", "Hello from Java ProcessBuilder");
        try {
            Process process = pb.start();
            int status = process.waitFor();
            System.out.println("Process completed with status: " + status);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error launching process: " + e.getMessage());
        }
    }
}
