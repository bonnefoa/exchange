import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.io.*;

/*	***************	*/
/*	STOCK OPTIONS	*/
/*	***************	*/

class StockOption implements Remote {
        String title;
        String company;
        float quote;
        public StockOption(String tt, String co, float qt) {
          super();
          title = tt;
          company = co;
          quote = qt;
        }
        public String getTitle() {
                return title;
        }
        public String getCompany() {
                return company;
        }
        public float getQuote() {
                return quote;
        }
        public void setQuote(float value) {
                quote = value;
        }
}

/*	***********	*/
/*	CONTRACTS	*/
/*	***********	*/

interface ClientContract extends Remote {
	public void warn(String mess) throws RemoteException;
}

interface ExchangeContract extends Remote {
	public final static String host = "rmi://localhost/";
	public static final String EXCHANGE_IDENTITY = "SE_Service";
	public void connect(String nom) throws RemoteException;
}

/*	***************	*/
/*	IMPLEMENTATIONS	*/
/*	***************	*/

class ExchangeSkeleton extends UnicastRemoteObject implements ExchangeContract, Runnable {
	ArrayList clients = new ArrayList();
	boolean end = false;
	public ExchangeSkeleton() throws RemoteException {
		super();
	}
	public void start() {
		new Thread(this).start();
	}
	public void connect(String nm) throws RemoteException {
		try {
			ClientContract cc = (ClientContract) Naming.lookup(nm);
			synchronized(clients) {
				clients.add(cc);
			}
		} catch(Exception e) {
                  System.err.println(e);
        }
	}
	public void run() {
		Iterator it;
		Random rand = new Random();
		while (!end) {
			try {
				Thread.sleep(3*1000);
			} catch(InterruptedException e) {
				System.out.println("Shutdown...");
				end = true;
			}
			synchronized(clients) {
				it = clients.iterator();
				while (it.hasNext()) {
					String mesg = "Stock option's quote is going " + (rand.nextFloat() > 0.5 ? "up" : "down");
					try {
						((ClientContract) it.next()).warn(mesg);
					} catch(RemoteException e) {
						it.remove();
					}
				}
			}
		}
	}
}

/*	*******	*/
/*	SERVER	*/
/*	*******	*/

class ExchangeServer {
	public static void main(String[] arg) {
		try {
			ExchangeSkeleton esk = new ExchangeSkeleton();
			Naming.bind(ExchangeContract.EXCHANGE_IDENTITY,esk);
			System.out.println("Exchange server is running...");
			esk.start();
		} catch(Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}

/*	*******	*/
/*	CLIENT	*/
/*	*******	*/

class ExchangeClient extends UnicastRemoteObject implements ClientContract {
	public ExchangeClient() throws RemoteException{
		super();
	}
	public static void main(String[] arg) throws IOException, NotBoundException {
		new ExchangeClient().roll(arg[0]);
	}
	private void roll(String nm) throws IOException, NotBoundException {
		System.out.println("Client "+ nm + " is running...");
		try {
			Naming.bind(nm,this);
		} catch(AlreadyBoundException e) {
			Naming.rebind(nm,this);
		}
		ExchangeContract ec = (ExchangeContract) Naming.lookup(ExchangeContract.host + ExchangeContract.EXCHANGE_IDENTITY);
		ec.connect(nm);
	}
	public void warn(String mess) throws RemoteException {
		System.out.println(mess);
	}
}