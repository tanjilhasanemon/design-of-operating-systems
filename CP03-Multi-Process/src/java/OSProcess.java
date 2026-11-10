import java.io.IOException;

public class OSProcess {
    public static void main(String[] args) {
        try {
            System.out.println("Launching external OS process from Java...");
            ProcessBuilder pb = new ProcessBuilder("ping", "127.0.0.1");
            Process process = pb.start();
            System.out.println("Process launched successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
