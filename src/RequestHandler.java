import java.io.*;
import java.net.Socket;

public class RequestHandler implements Runnable
{
    private final Socket CLIENT;

    public RequestHandler(Socket client)
    {
        this.CLIENT = client;
    }

    @Override
    public void run()
    {
        try (BufferedReader clientIn = new BufferedReader(new InputStreamReader(this.CLIENT.getInputStream()));
             BufferedWriter serverOut = new BufferedWriter(new OutputStreamWriter(this.CLIENT.getOutputStream())))
        {
            System.out.println("Thread started with name: " + Thread.currentThread().getName());

            String userInput;
            while ((userInput = clientIn.readLine()) != null)
            {
                System.out.println(userInput);
                serverOut.write("You entered: " + userInput);
                serverOut.newLine();
                serverOut.flush();
            }

            serverOut.close();
            clientIn.close();
            this.CLIENT.close();

            System.out.println("Thread closed with name: " + Thread.currentThread().getName());

        } catch (IOException e)
        {
            System.out.println("I/O exception: " + e.toString());
        } catch (Exception e)
        {
            System.out.println("Exception in Thread Run. Exception: " + e.toString());
        }
    }
}
