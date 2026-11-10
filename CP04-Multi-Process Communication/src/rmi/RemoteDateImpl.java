import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Date;

public class RemoteDateImpl extends UnicastRemoteObject implements RemoteDate {
    public RemoteDateImpl() throws RemoteException {
        super();
    }
    
    public String getDate() throws RemoteException {
        return "RMI Server Time: " + new Date().toString();
    }
}
