import java.net.*;
import java.io.*;
import java.util.*;

/*
 * La classe Client
 * rappresenta il client che:
 * si connette al server TCP
 * invia le credenziali
 * invia una lista di numeri
 * riceve e stampa la media calcolata dal server
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            // LOGIN
            System.out.print("Username: ");
            out.println(scanner.nextLine());

            System.out.print("Password: ");
            out.println(scanner.nextLine());

            String risposta = in.readLine();
            System.out.println("Server: " + risposta);

            if (!risposta.equals("LOGIN OK")) {
                socket.close();
                return;
            }

            // INVIO NUMERI
            System.out.print("Inserisci numeri separati da spazio: ");
            out.println(scanner.nextLine());

            // RICEZIONE MEDIA
            String risultato = in.readLine();
            System.out.println("Server: " + risultato);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}