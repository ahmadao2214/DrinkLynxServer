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
	SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss a] ");
	Bars db = new Bars();
	ArrayList<Double[]> barCoords = new ArrayList<Double[]>();
	private Double lat, lng;

	public DrinkLynxServer(int port) {
		this.portNumber = port;
	}

	public void listen() throws IOException {

		System.out.println("Server is listening on port: " + portNumber);

		while (true) {
			try {
				// Create Server
				serverSocket = new ServerSocket(portNumber);
				clientSocket = serverSocket.accept();

				String clientName = clientSocket.getInetAddress().getHostName();
				System.out.println(sdf.format(Time) + " New Client Connected: "	+ clientName);

				//ArrayList<String> radius = new ArrayList<String>();

				// Get Bar Name
				//InputStream inStream = clientSocket.getInputStream();
				//PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				//BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
				DataOutputStream output= new DataOutputStream(clientSocket.getOutputStream());
				DataInputStream input= new DataInputStream(clientSocket.getInputStream());	
				BufferedReader in = new BufferedReader(new InputStreamReader(input));
				String inputLine = "";
				while ((inputLine = in.readLine()) != null) {
					//inputLine = input.readUTF();
					System.out.println("Received from client: " + inputLine);
					output.writeUTF(inputLine + " FROM SERVER");
					System.out.println("ITEM SENT");
					//radius.add(inputLine);
				}
				
				output.close();
				in.close();
				System.out.println("OUT OF DA LOOP");

				/*BarRadius br = new BarRadius(radius.get(0), radius.get(1),radius.get(2), radius.get(3));
				barCoords = db.getCoords();
				for (Double[] latlng : barCoords) {
					lat = latlng[0];
					lng = latlng[1];
					for (String t : db.getTitles()) {
						//if (br.withinRange(lat, lng)) {
							System.out.println("" + lat + "," + lng + "," + t);
							out.println("" + lat + "," + lng + "," + t);
						//} else{
							//System.out.println("" + lat + "," + lng + "," + t + " not within range");
							//out.println("" + lat + "," + lng + "," + t	+ " not within range");
						//}
					}
				}*/

				// Close All The Things
				
				//in.close();	
				
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
