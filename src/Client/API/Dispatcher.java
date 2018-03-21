package Client.API;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import Msg.Envelop;

public class Dispatcher extends Thread
{
	private static volatile Dispatcher dd = null;
	Socket cs = null;

	public static synchronized Dispatcher getI() throws UnknownHostException, IOException
	{
		if(dd == null)
			dd = new Dispatcher();
		     
		return dd;

	}
	private Dispatcher() throws UnknownHostException, IOException 
	{
		
		cs = new Socket("127.0.0.1",8888);
		this.start();
	}
	public void send(Envelop msg) throws IOException
	{
		
		DataOutputStream out = new DataOutputStream(cs.getOutputStream());
		Gson gg = new Gson();
		String str = gg.toJson(msg);
		out.writeUTF(str);
		out.flush();
	}

	@Override
	public void run()
	{
		try 
		{
			Gson gg = new Gson();
			DataInputStream in = new DataInputStream(cs.getInputStream());

			while (true)
			{

				String msg = in.readUTF();
				System.out.println("msg  :   " + msg);
				if(msg.length() > 0)
				{
					
					Envelop env = gg.fromJson(msg, Envelop.class);
					
					switch(env.module)
					{
					case "ra":      
						RA.getI().action(env); 
						  
						break;
					case "profile": 
						Profile.getI().action(env); 
						
						break;
					case "chat":    
						Chat.getI().action(env);  
						
						break;
					}
					yield();
				}

			}
		}
		catch (IOException e) 
		{
		}
	}
}
