import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private final int PORTNUMBER = 1024;

    public static void main(String[] args)
    {
        Server server = new Server();
        server.createServerSocket();
    }

    public void createServerSocket()
    {
        try (ServerSocket serverSocket = new ServerSocket(this.PORTNUMBER))
        {
            String inputLine;
            while (true)
            {
                Socket clientSocket = serverSocket.accept();
                Runnable thread = new RequestHandler(clientSocket);
                thread.run();
            }
        } catch (IOException e)
        {
            System.out.println("Exception caught when trying to listen on port " + this.PORTNUMBER + " or listening for a connection");
        }
    }
}
