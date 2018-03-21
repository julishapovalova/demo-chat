package Server.API;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.google.gson.Gson;

public class ServerIO extends Thread
{
	ArrayList<Data> soc = null;
	Queue<String> qeu = new LinkedList<>();

	public ServerIO(ArrayList<Data> soc)
	{
		this.soc = soc;
		this.start();
	}

	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				for(Data d : soc)
				{
					DataInputStream in = new DataInputStream(d.s.getInputStream());

					if( in.available() > 0 )
					{
						String str = in.readUTF();
						System.out.println(str);
						Gson gg = new Gson();
						Envelop env = gg.fromJson(str, Envelop.class);
						if(env.comand.equals("Logout"))
						{
							System.out.println("Logout delete");
							Dispatcher.getI().send(str, d.s);
							soc.remove(d);
							break;
						}
						else if(d.login == null)
						{
							d.login = str;
						}
						Dispatcher.getI().send(str, d.s);
					}
				}

				Thread.sleep(50);
			}
		}
		catch (InterruptedException | IOException e)
		{
			e.printStackTrace();
			System.out.println("Exit");
		}
	}


}
