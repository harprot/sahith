
public class MbRc {
	public static String message;
	public static int number;
	
	public void send(MessageBuffer msg)
	{
		message = msg.message;
		number = msg.number;
		System.out.println("Sent Message from Producer: = " + message + " \n Number: = " + number);
	}
	
	public MessageBuffer receive()
	{
		MessageBuffer msg = new MessageBuffer(message,number);
		return msg;
	}
	
	public void reply(MessageBuffer msg) {
		message = msg.message;
		number = msg.number;
	}
}
