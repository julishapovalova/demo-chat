package Server.API;



import java.net.Socket;

public class Data 
{
      Socket s;
      String login;
      public Data(Socket s, String login) 
      {
		this.s = s;
		this.login = login;
	  }
}
