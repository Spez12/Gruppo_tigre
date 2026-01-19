import java.net.*;
import java.io.*;
import java.util.*;

public class ServerMedia {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server avviato sulla porta 5000");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connesso");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            // FASE DI LOGIN
            String username = in.readLine();
            String password = in.readLine();

            if (username.equals("admin") && password.equals("1234")) {
                out.println("LOGIN OK");
            } else {
                out.println("LOGIN FALLITO");
                clientSocket.close();
                serverSocket.close();
                return;
            }

            // RICEZIONE NUMERI
            String numeri = in.readLine();
            String[] parti = numeri.split(" ");

            double somma = 0;
            int count = 0;

            for (String s : parti) {
                somma += Double.parseDouble(s);
                count++;
            }

            double media = somma / count;

            // INVIO RISULTATO
            out.println("Media: " + media);

            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
