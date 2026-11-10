import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DateTimeServer {
    public static void main(String[] args) {
        try {
            RemoteDateImpl obj = new RemoteDateImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("DateServer", obj);
            System.out.println("[DateTimeServer] RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
