import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ProcessBuilderDemo {
    public static void main(String[] args) {
        try {
            // Determine command depending on OS
            String[] command = System.getProperty("os.name").toLowerCase().contains("win") 
                ? new String[]{"cmd.exe", "/c", "dir"} 
                : new String[]{"ls", "-la"};

            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
