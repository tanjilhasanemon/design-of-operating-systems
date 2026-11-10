import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            RemoteDate stub = (RemoteDate) registry.lookup("DateServer");
            System.out.println("[RMIClient] Connecting to remote object...");
            System.out.println(stub.getDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
