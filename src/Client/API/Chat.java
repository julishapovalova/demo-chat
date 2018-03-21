package Client.API;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import Client.GUI.CPanel;
import Msg.Envelop;

public class Chat 
{
	private static volatile Chat ch = null;
	ActionListener al = null;
	
	private Chat()
	{
		//
	}
	
	public static synchronized Chat getI() throws UnknownHostException, IOException
	{
		if(ch == null)
			ch = new Chat();
		    
		return ch;

	}
	public void seng(String str) throws UnknownHostException, IOException
	{
		
		Envelop env = new Envelop();
		env.module = "chat";
		env.comand = "msg";
		env.message = str;
		Dispatcher.getI().send(env);
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
			
			if(env.comand.equals("Logout"))
			{
				
				for(int i = 0; i < CPanel.list.size(); i++)
				{
					if(CPanel.list.get(i).equals(env.message))
					{
						CPanel.list.remove(i);
						
						CPanel.userArea.setText(null);
						
						for(String k : CPanel.list)
						{
							CPanel.userArea.append(k + "\n");
						}
						
					}

				}
			}
		}

	}
}
