package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import Server.API.Data;
import Server.API.ServerIO;

public class StartServer
{
	
	public  static ArrayList<Data> list2 = new ArrayList<>();
	       
	public static void main(String[] args) throws IOException
	{
		ServerSocket ss = new ServerSocket(8888);
		ServerIO sio = new ServerIO(list2);
		while (true)
		{	
			list2.add(new Data(ss.accept(), null));
		}
	}

}


