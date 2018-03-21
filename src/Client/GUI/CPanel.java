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

public class CPanel extends JPanel
{
	JTextArea msgArea;
	public static JTextArea userArea;
	public static ArrayList<String> list = new ArrayList<>();
	JTextField txtLog ;
	JTextField txtMsg;
	public CPanel() throws UnknownHostException, IOException
	{
		setLayout(null);

		JLabel lblMsg = new JLabel ("Messages");
		JLabel lblUser = new JLabel ("Users");
		JLabel lblMsend = new JLabel ("Message:");
		JLabel lblLog = new JLabel ("Login:");
		userArea = new JTextArea();
		userArea.setName("userArea");

		msgArea = new JTextArea();
		JScrollPane jscMsg = new JScrollPane(msgArea);
		JScrollPane jscUser = new JScrollPane(userArea);
		txtMsg = new JTextField();
		txtLog = new JTextField();
		txtLog.setName("txtLog");
		txtMsg.setName("txtMsg");

		JButton btnSend = new JButton ("Send Message");
		JButton btnLogIn = new JButton ("Log in");
		JButton btnLogOut = new JButton ("Log out");


		userArea.setEditable(false);
		msgArea.setEditable(false);
		lblUser.setBounds(10, 10, 80, 30);
		lblMsg.setBounds(170, 10, 80, 30);
		jscMsg.setBounds(170, 50, 400, 450);
		jscUser.setBounds(10, 50, 150, 450);
		lblLog.setBounds(10, 550, 80, 40);
		txtLog.setBounds(100, 550, 190, 40);
		btnLogIn.setBounds(300, 550, 120, 40);	
		btnLogOut.setBounds(450, 550, 120, 40);
		lblMsend.setBounds(10, 600, 80, 40);
		txtMsg.setBounds(100, 600, 340, 40);
		btnSend.setBounds(450, 600, 120, 40);

		btnLogIn.addActionListener( new LoginAction() );
		btnLogOut.addActionListener(new LogoutAction() );
		btnSend.addActionListener(new MsgAction() );


		add(lblLog);
		add(lblMsend);
		add(lblMsg);
		add(lblUser);
		add(jscUser);
		add(jscMsg);
		add(txtMsg);
		add(btnSend);
		add(btnLogIn);
		add(btnLogOut);
		add(txtLog);

		Chat.getI().addReceiveListener(new ChatListener());
		Profile.getI().addReceiveListener(new ProfileListener());
	}

	class ChatListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			msgArea.append(e.getActionCommand() + "\n");
		}
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

	class MsgAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try 
			{
				Chat.getI().seng( txtMsg.getText() );
			} catch (IOException e1) 
			{

				e1.printStackTrace();
			}

		}

	}
}
