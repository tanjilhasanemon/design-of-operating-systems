import java.net.*;
import java.io.*;

public class DateClient {
    public static void main(String[] args) {
        try {
            Socket sock = new Socket("127.0.0.1", 6013);
            BufferedReader bin = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String line;
            
            System.out.println("[DateClient] Data received from server:");
            while ((line = bin.readLine()) != null) {
                System.out.println(line);
            }
            
            sock.close();
        } catch (IOException ioe) { 
            System.err.println(ioe); 
        }
    }
}
