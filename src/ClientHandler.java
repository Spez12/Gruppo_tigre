import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * questo thread viene lanciato dal server a fronte di una richiesta di connessione
 * da parte di un client
 */
class ClientHandler implements Runnable {
    //socket per il flusso dei dati
    private Socket socket;
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            String inputLine;
            // 3. Protocollo di comunicazione: leggi finché il client non chiude o invia "BYE"
            while ((inputLine = in.readLine()) != null) {
                if ("BYE".equalsIgnoreCase(inputLine)) break;
                // Elaborazione (Business Logic)
                System.out.println(inputLine);
                String response = inputLine.toUpperCase();
                // Invio risposta
                out.println("ECHO: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { socket.close(); } catch (IOException e) { System.out.println(e.toString()); }
        }
    }
}