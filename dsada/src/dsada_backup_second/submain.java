package dsada_backup_second;

import java.awt.Frame;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class submain extends JPanel {
	private JTextField textField;
	private mainframe F;

	public submain(mainframe f) {
		F=f;
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(54, 93, 288, 92);
		add(textField);
		textField.setColumns(10);
		setVisible(true);

	}
}
