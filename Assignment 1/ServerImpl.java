import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{
public ServerImpl() throws RemoteException{
}

public double addition (double n1, double n2) throws RemoteException{
		return n1+n2;
}
		public double substraction (double n1, double n2) throws RemoteException{
		return n1-n2;
}
		public double multiplication (double n1, double n2) throws RemoteException{
		return n1*n2;
}
		public double division (double n1, double n2) throws RemoteException{
		if(n2 !=0){
		return n1/n2;
		}
		else{
		System.out.println("Can't divide a number by zero");
		}
		return n1/n2;
}

}