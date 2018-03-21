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

public class CPanelMsg extends JPanel
{
	JTextArea msgArea;
	JTextField txtMsg;
	public CPanelMsg() throws UnknownHostException, IOException
	{
		setLayout(null);
	
		setBounds(5, 0, 570, 550);

		JLabel lblMsg = new JLabel ("Messages");
		lblMsg.setName("lblMsg");
		msgArea = new JTextArea();
		msgArea.setName("msgArea");
		JScrollPane jscMsg = new JScrollPane(msgArea);
		txtMsg = new JTextField();
		txtMsg.setName("txtMsg");
		JButton btnSend = new JButton ("Send");
		btnSend.setName("btnSend");


		msgArea.setEditable(false);
		lblMsg.setBounds(170, 10, 80, 30);
		jscMsg.setBounds(170, 50, 400, 450);
		txtMsg.setBounds(170, 500, 310, 40);
		btnSend.setBounds(480, 500, 90, 40);

		btnSend.addActionListener(new MsgAction() );


		add(lblMsg);
		add(jscMsg);
		add(txtMsg);
		add(btnSend);

		Chat.getI().addReceiveListener(new ChatListener());
	}

	class ChatListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			msgArea.append(e.getActionCommand() + "\n");
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
