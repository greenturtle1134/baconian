package lib;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ColorBox extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int MARGIN = 5;
	private Color color;

	public ColorBox(Color color) {
		this.color = color;
	}

	public ColorBox() {
		new ColorBox(Color.WHITE);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		this.setForeground(color);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillRect(MARGIN, MARGIN, this.getWidth()-MARGIN*2, this.getHeight()-MARGIN*2);
	}
}
