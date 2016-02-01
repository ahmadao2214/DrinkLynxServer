import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import com.bbn.openmap.proj.coords.LatLonPoint;

public class DrinkLynxClient {
	
	Socket clientSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	
	private String HostName;
	private int PortNumber;
	private String StringData;
	
	public DrinkLynxClient(String HostName, int PortNumber, String StringData) {
		this.HostName = HostName;
		this.PortNumber = PortNumber;
		this.StringData = StringData;
	}
	
	public void connect() {
		
		try {
			clientSocket = new Socket(HostName, PortNumber);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			System.out.println(StringData);
			String inputLine = in.readLine();
			System.out.println(inputLine);
		} 
		catch (UnknownHostException e) {
			System.err.println("Unknown Host: " + HostName);
			e.printStackTrace();
			System.exit(-1);
		} 
		catch (IOException e) {
	    System.err.println("Couldn't get I/O for the connection to " + HostName);
	    e.printStackTrace();
	    System.exit(-1);
		}
		finally {
			try {
				System.out.println( "Done...exiting..." );
				out.close();
				in.close();
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}
