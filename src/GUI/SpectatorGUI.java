package GUI;

import javax.swing.*;
import java.awt.*;

public class SpectatorGUI {

    private JFrame frame;
    private JLabel questionLabel;
    private JLabel countdownLabel;
    private JLabel playerLabel;

    public SpectatorGUI() {
        frame = new JFrame("Mode Spectateur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        questionLabel = new JLabel("Question en cours", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(questionLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 2));

        countdownLabel = new JLabel("Temps restant: 10", JLabel.CENTER);
        countdownLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        playerLabel = new JLabel("Joueur qui a trouvé la réponse: Aucun", JLabel.CENTER);
        playerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        infoPanel.add(countdownLabel);
        infoPanel.add(playerLabel);

        frame.add(infoPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    public void updateQuestion(String question) {
        questionLabel.setText(question);
    }

    public void updateCountdown(int seconds) {
        countdownLabel.setText("Temps restant: " + seconds);
    }

    public void updatePlayer(String playerName) {
        playerLabel.setText("Joueur qui a trouvé la réponse: " + playerName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SpectatorGUI();
            }
        });
    }
}
