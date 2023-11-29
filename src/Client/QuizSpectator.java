package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class QuizSpectator implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public QuizSpectator(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Votre logique pour la gestion des informations côté spectateur va ici
            out.println("Bienvenue dans le mode spectateur. Attendez les mises à jour...");

            // Exemple: Attendre les mises à jour du jeu
            while (true) {
                String update = in.readLine();
                if (update == null) {
                    break; // Le joueur a quitté
                }

                // Exemple: Afficher la mise à jour du jeu côté spectateur
                processGameUpdate(update);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processGameUpdate(String update) {
        // Logique pour traiter les mises à jour du jeu et mettre à jour l'interface spectateur
        // Exemple: Mettez à jour l'interface avec les informations reçues du jeu
        System.out.println("Mise à jour du jeu pour les spectateurs : " + update);
        // TODO: Mettez à jour votre interface spectateur en conséquence
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
