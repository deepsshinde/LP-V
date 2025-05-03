import java.rmi.*;
import java.util.Scanner;

public class Client{
		public static void main ( String[] args){
			Scanner sc = new Scanner(System.in);

			try{
				String serverURL = "rmi://localhost/Server";
				ServerIntf serverIntf = (ServerIntf) Naming.lookup(serverURL);

				System.out.println("Enter Value of Number1");
				double n1 = sc.nextDouble();
				System.out.println("Enter Value of Number2");
				double n2 = sc.nextDouble();

				System.out.println("First Number is "+n1);
				System.out.println("Second Number is "+n2);

				System.out.println("--------------Result--------------");
				System.out.println("Addition is :" + serverIntf.addition(n1,n2));		
				System.out.println("Substraction  is :" + serverIntf.substraction(n1,n2));
				System.out.println("multiplication is :" + serverIntf.multiplication(n1,n2));
				System.out.println("Division is :" + serverIntf.division(n1,n2));
	
		}catch(Exception e){
			System.out.println("Exception occurred at client!" + e.getMessage());
		}

}
}