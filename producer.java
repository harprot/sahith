
public class producer {
	static boolean messageBufferFull = false;
	String operation;
	int value;
	
	public synchronized void produce() {
		
		int count = 0;
		MbRc msgbuff = new MbRc();
		MessageBuffer msg = new MessageBuffer("add",3);
		msgbuff.send(msg);
		messageBufferFull = true;
		notifyAll();
		while (messageBufferFull == true) {
           try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        MessageBuffer msg1 = msgbuff.receive();
        System.out.println("Received Answer by Producer task : " + msg1.number + "\n");
        if(count == 0)
        {
        	msg = new MessageBuffer("multiply", 1);
        	count++;
        }
        else if(count == 1)
        {
        	msg = new MessageBuffer("multiply", 2);
        	count++;
        }
        else if(count == 2) 
        {
        	msg = new MessageBuffer("subtract", 20);
        	count++;
        }

        else if(count == 3) 
        {
        	msg = new MessageBuffer("subtract", 100);
        	count++;
        }
        else if(count == 4) 
        {
        	msg = new MessageBuffer("add", 2);
        	count++;
        }
        else if(count == 5) 
        {
        	msg = new MessageBuffer("divide", 20);
        	count++;
        }
        else if(count == 6) 
        {
        	msg = new MessageBuffer("divide", 11);
        	count++;
        }
        else if(count == 7) 
        {
        	msg = new MessageBuffer("multiply", 3);
        	count++;
        }
        else if(count == 8) 
        {
        	System.exit(2);
        }

        msgbuff.send(msg);
        messageBufferFull = true;
        notifyAll();
		}

	}
	public synchronized void consumer() throws InterruptedException
	{
		Thread.sleep(2000);
		MbRc msgbuff = new MbRc();
		MessageBuffer m1 = msgbuff.receive();
		if(m1.message.compareTo("add")==0) {
			m1.number += 10;
			msgbuff.reply(m1);
			messageBufferFull =false;
			notifyAll();

		}
		else if(m1.message.compareTo("subtract")==0) {
			m1.number -= 10;
			msgbuff.reply(m1);
			messageBufferFull =false;
			notifyAll();
		}
		else if(m1.message.compareTo("multiply")==0 ) {
			m1.number *= 10;
			msgbuff.reply(m1);
			messageBufferFull =false;
			notifyAll();
		}
		else if(m1.message.compareTo("divide")==0) {
			m1.number /= 2;
			msgbuff.reply(m1);
			messageBufferFull =false;
			notifyAll();
		}
		while (messageBufferFull == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			m1 = msgbuff.receive();
			if(m1.message.compareTo("add")==0) {
				m1.number += 10;
				msgbuff.reply(m1);
				messageBufferFull =false;
			}
			else if(m1.message == "subtract") {
				m1.number -= 10;
				msgbuff.reply(m1);
				messageBufferFull =false;
			}
			else if(m1.message == "multiply") {
				m1.number *= 10;
				msgbuff.reply(m1);
				messageBufferFull =false;
			}
			else if(m1.message == "divide") {
				m1.number /= 2;
				msgbuff.reply(m1);
				messageBufferFull =false;
			}
			notifyAll();
		}
	}
}