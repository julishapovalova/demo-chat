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

public class CPanelRA extends JPanel
{
	JTextField txtLog ;
	public CPanelRA() throws UnknownHostException, IOException
	{
		setLayout(null);
		setBounds(5, 550, 550, 50);

		JLabel lblLog = new JLabel ("Login:");
		lblLog.setName("lblLog");
		txtLog = new JTextField();
		txtLog.setName("inputNickName");
		JButton btnLogIn = new JButton ("Log in");
		btnLogIn.setName("btnLogIn");
		JButton btnLogOut = new JButton ("Log out");
		btnLogOut.setName("btnLogOut");

		lblLog.setBounds(10, 550, 80, 40);
		txtLog.setBounds(100, 550, 190, 40);
		btnLogIn.setBounds(300, 550, 120, 40);	
		btnLogOut.setBounds(450, 550, 120, 40);

		btnLogIn.addActionListener( new LoginAction() );
		btnLogOut.addActionListener(new LogoutAction() );


		add(lblLog);
		add(btnLogIn);
		add(btnLogOut);
		add(txtLog);

	}


	class LoginAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try 
			{
				RA.getI().login( txtLog.getText() );

			} catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
	}

	class LogoutAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try 
			{
				RA.getI().logout( txtLog.getText() );
			} catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
	}

}
