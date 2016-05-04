
public class Homework {
	public static void main(String[] args) throws InterruptedException {
		//Creating a object for the producer
		final producer prod = new producer();
		
		//initialize Thread 1 
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				prod.produce(); 
			}
		});
		
		//initialize Thread 2
		Thread t2 = new Thread(new Runnable() {
		public void run() {
			try {
				prod.consumer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}

		});
		
		//Starting the threads
		t1.start();
		t2.start();
		
		//Joining the threads
		t1.join();
		t2.join();
	}
}