package Server.API;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import com.google.gson.Gson;

import Server.StartServer;
import Server.API.Envelop;

public class Chat extends Thread
{
	private static volatile Chat ch = null;
	private Queue<String> qeu = null;
	private String str = null;
	

	private Chat()
	{
		qeu = new LinkedList<>();
		this.start();
	}

	public static synchronized Chat getI() throws UnknownHostException, IOException
	{
		if(ch == null)
			ch = new Chat();

		return ch;

	}





	void action(Envelop env)
	{
		

		Gson gg = new Gson();

		str = gg.toJson(env);
		
		new Chatmsg(str);
		System.out.println(str +" Action server mes");
	}
	
	class Chatmsg extends Thread
	{
		String str;
		public Chatmsg(String str)
		{
			this.str = str;
			this.start();
		}
		
		@Override
		public void run() {
		
			super.run();
			for(Data d : StartServer.list2)
			{
				
				try 
				{
					qeu.add(str);
					System.out.println("11Flag");
					DataOutputStream out = new DataOutputStream(d.s.getOutputStream());
					while (qeu.size() > 0) 
					{

						out.writeUTF(qeu.poll());
						out.flush();


					}
				} catch (Exception e) 
				{
					System.out.println("Exit Chat");
					e.printStackTrace();
					return;
				}
				
			}
		}
		
	}
	


	
}
