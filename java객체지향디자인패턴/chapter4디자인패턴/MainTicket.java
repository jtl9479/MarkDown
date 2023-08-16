package java객체지향디자인패턴.chapter4디자인패턴;

class SingletonFatten implements ticket {

	private static int serial = 0;
	
	private static SingletonFatten singletonFatten;
	private SingletonFatten() {}
	
	public static SingletonFatten getSingletonFatten() {
		
		if ( singletonFatten == null ) {
			singletonFatten = new SingletonFatten();
		}
		return singletonFatten;
	}

	@Override
	public int getTicket() {
		serial ++;
		System.out.println(singletonFatten.toString() + " : " + serial);
		
		return serial;
	}
	
}

interface ticket {
	
	public int getTicket();
}

public class MainTicket {
	public static void main(String[] args) {
		for (int a = 0; a < 10; a++) {
			ticket ticket = SingletonFatten.getSingletonFatten();
			System.out.println(ticket.getTicket());
		}
	}
}



