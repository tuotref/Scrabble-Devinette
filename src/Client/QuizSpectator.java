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

            // Votre logique pour la gestion des informations c�t� spectateur va ici
            out.println("Bienvenue dans le mode spectateur. Attendez les mises � jour...");

            // Exemple: Attendre les mises � jour du jeu
            while (true) {
                String update = in.readLine();
                if (update == null) {
                    break; // Le joueur a quitt�
                }

                // Exemple: Afficher la mise � jour du jeu c�t� spectateur
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
        // Logique pour traiter les mises � jour du jeu et mettre � jour l'interface spectateur
        // Exemple: Mettez � jour l'interface avec les informations re�ues du jeu
        System.out.println("Mise � jour du jeu pour les spectateurs : " + update);
        // TODO: Mettez � jour votre interface spectateur en cons�quence
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
