package Server.API;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import Server.API.Envelop;

public class Dispatcher 
{
	private static volatile Dispatcher dd = null;
	

	public static synchronized Dispatcher getI() throws UnknownHostException, IOException
	{
		if(dd == null)
			dd = new Dispatcher();

		return dd;

	}
	private Dispatcher() throws UnknownHostException, IOException 
	{
		
	}
	

	public void send(String str,  Socket s)
	{
		try 
		{
			Gson gg = new Gson();
			Envelop env = gg.fromJson(str, Envelop.class);
			   
			switch(env.module)
			{
			   
			    case "profile":
			    	Profile.getI().action(env, s); 
			    	
			    	break;
			    case "chat":    
			    	Chat.getI().action(env); 
			    	
			    	break;
			}
		}catch (IOException e) 
		{
		}

	}
	
}
