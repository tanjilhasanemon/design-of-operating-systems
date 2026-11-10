import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteDate extends Remote {
    String getDate() throws RemoteException;
}
