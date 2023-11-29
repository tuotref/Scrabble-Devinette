
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class QuizGUI {

	private JFrame frame;
	private JButton playButton;
	private JButton spectatorButton;

	public QuizGUI() {
		frame = new JFrame("SCRABBLE DEVINETTE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLayout(new BorderLayout());

		JLabel titleLabel = new JLabel("SCRABBLE DEVINETTE", JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		frame.add(titleLabel, BorderLayout.NORTH);

		playButton = new JButton("PLAY");
		playButton.setFont(new Font("Arial", Font.PLAIN, 16));
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startQuiz();
			}
		});

		spectatorButton = new JButton("ACCEDEZ EN MODE SPECTATEUR");
		spectatorButton.setFont(new Font("Arial", Font.PLAIN, 12));
		spectatorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterSpectatorMode();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));
		buttonPanel.add(playButton);
		buttonPanel.add(spectatorButton);

		frame.add(buttonPanel, BorderLayout.CENTER);

		frame.setVisible(true);
	}



	private void startQuiz() {
		// Code pour lancer le Quiz en mode joueur
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI.Quiz quiz = new GUI.Quiz();
				quiz.nextQuestion(); // Utilisez nextQuestion() pour commencer le quiz
			}
		});
	}



	private void enterSpectatorMode() {
		// Code pour accéder au mode spectateur
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Socket clientSocket = new Socket("localhost", 12345); // Mettez l'adresse et le port appropriés
					Client.QuizSpectator spectator = new Client.QuizSpectator(clientSocket);
					Thread spectatorThread = new Thread(spectator);
					spectatorThread.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new QuizGUI();
			}
		});
	}
}
