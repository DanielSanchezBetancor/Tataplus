import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Imagen extends JPanel{
	private Image imagen;

	public Imagen(Image imagen) {
		this.imagen = imagen;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagen != null) {
			g.drawImage(imagen, 0, 0, null);
		}
	}
}
