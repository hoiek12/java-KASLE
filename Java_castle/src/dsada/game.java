package dsada;

import java.awt.Color;
import javax.swing.JPanel;
import test.mycanvas;

public class game extends JPanel {

	private mainframe F;
	private mycanvas c;
	public game(mainframe f) {
		F=f;
		this.setBackground(Color.black);
	}
	
	public void remove()
	{
		F.contentPane.remove(this);
		F.gotomainpage();
	}

}
