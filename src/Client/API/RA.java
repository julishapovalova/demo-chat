package Client.API;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.lang.model.type.PrimitiveType;

import Msg.Envelop;

public class RA
{
	private static volatile RA ra = null;


	ActionListener al = null;

	public static synchronized RA getI() throws UnknownHostException, IOException
	{
		if(ra == null)
			ra = new RA();
		
		return ra;

	}


	private RA()
	{
		//
	}
	void action(Envelop env)
	{
		if(al != null)
		{
			
			ActionEvent e = new ActionEvent(this, 1 , env.message);
			al.actionPerformed(e);
		}
	}
	public void login(String str) throws UnknownHostException, IOException 
	{
		
		Envelop env = new Envelop();
		env.module = "profile";
		env.comand = "login";
		env.message = str;
		Dispatcher.getI().send(env);
	}
	public void logout(String str) throws UnknownHostException, IOException
	{
		
		Envelop env = new Envelop();
		env.module = "chat";
		env.comand = "Logout";
		env.message = str;
		Dispatcher.getI().send(env);
	}
}