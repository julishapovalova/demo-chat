package Client.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.API.Chat;
import Client.API.Profile;
import Client.API.RA;

public class CPanelProfile extends JPanel
{
	public static JTextArea userArea;
	public static ArrayList<String> list = new ArrayList<>();
	public CPanelProfile() throws UnknownHostException, IOException
	{
		setLayout(null);
		setBounds(5, 0, 160, 500);

		JLabel lblUser = new JLabel ("Users");
		lblUser.setName("lblUser");
		userArea = new JTextArea();
		userArea.setName("userArea");
		JScrollPane jscUser = new JScrollPane(userArea);


		userArea.setEditable(false);
		lblUser.setBounds(10, 10, 80, 30);
		jscUser.setBounds(10, 50, 150, 450);


		add(lblUser);
		add(jscUser);

		Profile.getI().addReceiveListener(new ProfileListener());
	}

	class ProfileListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			list.add(e.getActionCommand());
			userArea.append(e.getActionCommand() + "\n");

		}

	}
}
