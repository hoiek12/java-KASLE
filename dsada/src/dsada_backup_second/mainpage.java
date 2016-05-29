package dsada_backup_second;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class mainpage extends JPanel {
	private mainframe F;
	/**
	 * Create the panel.
	 */
	public mainpage(mainframe f) {
		F=f;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 143, 280);
		add(panel);
		panel.setLayout(new GridLayout(3, 0, 0, 0));
		
		// button sector start
		
		JButton button = new JButton("move btn");
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F.changepaneltosubmain();
			}
		});
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F.changepaneltoquiz();
			}
		});
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		setVisible(true);
	}
}
