package Client;

import java.io.DataOutputStream;
import java.io.IOException;

public class ServiceFunctions 
{
	public static void send (String str, DataOutputStream out) throws IOException
	{
		out.writeUTF(str);
		out.flush();	
	}
}
