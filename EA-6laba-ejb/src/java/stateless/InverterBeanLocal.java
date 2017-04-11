package stateless;

import java.rmi.RemoteException;
import javax.ejb.Local;

@Local
public interface InverterBeanLocal {
    
    public String invert(String string)
            throws RemoteException;
}
