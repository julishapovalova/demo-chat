package Client.API;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Client.GUI.CPanel;
import Msg.Envelop;

public class Profile 
{
	private static volatile Profile pf = null;
	ActionListener al = null;

	private Profile()
	{
		//
	}

	public static synchronized Profile getI() throws UnknownHostException, IOException
	{
		if(pf == null)
			pf = new Profile();

		return pf;

	}
	public void addReceiveListener(ActionListener al)
	{
		this.al = al;
	}

	void action(Envelop env)
	{
		if(al != null)
		{
			ActionEvent e = new ActionEvent(this, 1 , env.message);
			al.actionPerformed(e);
			
		}
	}
}
