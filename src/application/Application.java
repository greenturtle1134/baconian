package application;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ciphers.baconian.StringColorBaconian;

public class Application {

	public static void main(String[] args) {
		
		StringColorBaconian cipher = new StringColorBaconian(Color.RED, Color.GREEN, "abcdefghijklmnopqrstuvwxyz");
		JPanel keyPanel = cipher.getKeyPanel();
		
		JFrame frame = new JFrame("Key");
		frame.setVisible(true);
		frame.setSize(400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(keyPanel);
		frame.repaint();
		frame.revalidate();
		
		JFrame frame2 = new JFrame("Translate");
		frame2.setVisible(true);
		frame2.setSize(400, 200);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setContentPane(cipher.getTranslatePanel());
		frame2.repaint();
		frame2.revalidate();
	}

}
