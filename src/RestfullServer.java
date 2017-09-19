import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RestfullServer
{
    private final int PORTNUMBER = 1024;

    public static void main(String[] args)
    {
        RestfullServer server = new RestfullServer();
        server.createServerSocket();
    }

    public void createServerSocket()
    {
        try (ServerSocket serverSocket = new ServerSocket(this.PORTNUMBER))
        {
            Socket clientSocket = serverSocket.accept();

            PrintWriter serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            String inputLine;
            while ((inputLine = clientIn.readLine()) != null)
            {
                System.out.println("Received message: " + inputLine + " from " + clientSocket.toString());
                serverOut.println(inputLine);
            }

            serverOut.close();
            clientIn.close();
            clientSocket.close();

        } catch (IOException e)
        {
            System.out.println("Exception caught when trying to listen on port " + this.PORTNUMBER + " or listening for a connection");
        }
    }
}
