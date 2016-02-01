import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DrinkLynxServer {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private int portNumber;
	private Date Time = new Date();
	private Double lat, lng;
	SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss a] ");

	public DrinkLynxServer(int port) {
		this.portNumber = port;
	}

	public void listen() throws IOException {

		System.out.println("Server is listening on port: " + portNumber);

		while (true) {
			try {
				serverSocket = new ServerSocket(portNumber);
				clientSocket = serverSocket.accept();
				String clientName = clientSocket.getInetAddress().getHostName();
				System.out.println(sdf.format(Time) + " New Client Connected: "	+ clientName);
				
				DataOutputStream output= new DataOutputStream(clientSocket.getOutputStream());
				DataInputStream input= new DataInputStream(clientSocket.getInputStream());	
				BufferedReader in = new BufferedReader(new InputStreamReader(input));
				while ((String inputLine = in.readLine()) != null) {
					System.out.println("Received from client: " + inputLine);
					output.writeUTF(inputLine + " FROM SERVER");
					System.out.println("ITEM SENT");
				}
				output.close();
				in.close();				
				clientSocket.close();
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			} finally {
				serverSocket.close();
				System.out.println("Closing server socket...");
			}
		}
	}
}
