package Server.API;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import Server.StartServer;
import Server.API.Envelop;


public class Profile 
{
	private static volatile Profile pf = null;


	private Profile()
	{

	}

	public static synchronized Profile getI() throws UnknownHostException, IOException
	{
		if(pf == null)
			pf = new Profile();

		return pf;

	}
	 void action(Envelop env,  Socket s)
	{
		new Pmsg(env, s);
	}

	class Pmsg implements Runnable
	{
		Envelop env = null; 
		Socket s = null;
		public Pmsg(Envelop env,  Socket s)
		{
			this.env = env;
			this.s= s;
			new Thread(this).start();
		}
		@Override
		public void run()
		{
			System.out.println("Server Profile action");
			try 
			{
				Gson gg = new Gson();
				String str = gg.toJson(env);
				for(Data d : StartServer.list2)
				{
					if(d.s != s)
					{
						DataOutputStream out = new DataOutputStream(d.s.getOutputStream());
						
						out.writeUTF(str);
						out.flush(); 
					}
				}
				env.message = env.message + " Con";
				env.module = "chat";
				Chat.getI().action(env);
				for(Data k : StartServer.list2)
				{
					DataOutputStream out = new DataOutputStream(s.getOutputStream());
					out.writeUTF(k.login);
					out.flush();
				}

			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

}
