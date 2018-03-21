package Client.GUI;

import java.awt.Frame;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class CFrame extends JFrame
{
	Socket socket;
	
	
	public CFrame () throws UnknownHostException, IOException
	{
		
		setBounds(100, 100, 600, 700);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		add(new CPanel());
		add(new CPanelProfile());
		add(new CPanelMsg());
		add(new CPanelRA());
		setTitle("Chart");
		setVisible(true);
		
	}
}
