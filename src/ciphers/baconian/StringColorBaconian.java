package ciphers.baconian;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lib.ColorBox;

public class StringColorBaconian {
	private Baconian<Boolean, Character> cipher;
	private Color color1, color2;
	private String alphabet;
	
	public StringColorBaconian(Color color1, Color color2, String alphabet) {
		this.color1 = color1;
		this.color2 = color2;
		this.alphabet = alphabet;
		Character[] chars = new Character[alphabet.length()];
		for(int i=0; i<chars.length; i++) {
			chars[i] = alphabet.charAt(i);
		}
		Boolean[] booleans = {Boolean.TRUE, Boolean.FALSE};
		cipher = new Baconian<Boolean, Character>(booleans, chars);
	}
	
	public JPanel getCode(char c) {
		JPanel panel = new JPanel(new GridLayout(1,cipher.getBitLength()));
		Boolean[] colors = cipher.encode(c).toArray(new Boolean[0]);
		for(int i=0; i<cipher.getBitLength(); i++) {
			ColorBox box = new ColorBox();
			if(colors[i]) {
				box.setColor(color1);
			}
			else {
				box.setColor(color2);
			}
			panel.add(box);
		}
		panel.setVisible(true);
		return panel;
	}
	
	public JPanel getCode(String s) {
		s = this.cleanString(s);
		JPanel panel = new JPanel(new GridLayout(s.length(),1));
		for(int i=0; i<s.length(); i++) {
			panel.add(getCode(s.charAt(i)));
		}
		return panel;
	}

	public Color getColor1() {
		return color1;
	}

	public void setColor1(Color color1) {
		this.color1 = color1;
	}

	public Color getColor2() {
		return color2;
	}

	public void setColor2(Color color2) {
		this.color2 = color2;
	}
	
	public JPanel getKeyPanel() {
		JPanel panel = new JPanel(new GridLayout(alphabet.length(), 1));
		for(int i=0; i<alphabet.length(); i++) {
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new GridLayout(1, 2));
			subPanel.add(this.getCode(alphabet.charAt(i)));
			subPanel.add(new JLabel(alphabet.charAt(i)+""));
			panel.add(subPanel);
		}
		return panel;
	}
	
	public String cleanString(String string) {
		StringBuilder s = new StringBuilder();
		for(int i=0; i<string.length(); i++) {
			if(alphabet.indexOf(string.charAt(i))!=-1) {
				s.append(string.charAt(i));
			}
		}
		return s.toString();
	}
	
	public String getBooleanString(char c) {
		Boolean[] results = cipher.encode(c).toArray(new Boolean[0]);
		String s = "";
		for(int i=0; i<cipher.getBitLength(); i++) {
			if(results[i]) {
				s+="true, ";
			}
			else {
				s+="false, ";
			}
		}
		return s.substring(0,s.length()-2);
	}
	
	public String getBooleanMatrix(String s) {
		s = this.cleanString(s);
		String out = "{{";
		for(int i=0; i<s.length(); i++) {
			out+=this.getBooleanString(s.charAt(i));
			if(i<s.length()-1) {
				out+="},\n{";
			}
		}
		return out+="}}";
	}
	
	public JPanel getTranslatePanel() {
		return new TranslatePanel(this);
	}
	
	public static class TranslatePanel extends JPanel implements ActionListener {
		
		JTextField input, output;
		StringColorBaconian cipher;
		JPanel colorOutput;
		
		private TranslatePanel(StringColorBaconian cipher) {
			super();
			this.cipher = cipher;
			this.setLayout(new FlowLayout());
			input = new JTextField();
			output = new JTextField();
			this.add(input);
			JButton button = new JButton();
			button.addActionListener(this);
			button.setText("Translate");
			this.add(button);
			this.add(output);
			this.revalidate();
			this.repaint();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			output.setText(cipher.getBooleanMatrix(input.getText()));
			if(colorOutput!=null) {
				this.remove(colorOutput);
			}
			colorOutput = cipher.getCode(input.getText());
			this.add(colorOutput);
			this.revalidate();
			this.repaint();
		}
		
	}
}
