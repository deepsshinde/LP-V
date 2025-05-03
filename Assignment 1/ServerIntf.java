import java.rmi.*;

interface ServerIntf extends Remote{
		public double addition (double n1, double n2) throws RemoteException;
		public double substraction (double n1, double n2) throws RemoteException;
		public double multiplication (double n1, double n2) throws RemoteException;
		public double division (double n1, double n2) throws RemoteException;
}