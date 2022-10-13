package projekat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class IgracCita extends Thread{
	
	private String ime;
	private Socket socket;
	private BufferedReader in;
	Igrac igr;
	public IgracCita(Igrac i,String ime,Socket sock) {
		this.ime=ime;
		this.igr=i;
		try {
			
			this.in=new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
		System.out.println(this.in.readLine());
		 
		int br=0;
			while(true) {
			
				String odg1 = in.readLine();
				this.igr.karteNaStolu.clear();
				dodajNaSto(odg1);
				
				igr.isipisiKarteSaStola();
				
				Thread.sleep(100);
				if(br%12==0) {
				for(int i=0;i<6;i++) {
					String odg=in.readLine();
					if(odg==null)
						return;
					dodajURuke(odg);
				}
				}
				
				igr.isipisiKarteURuci();				
				System.out.println(in.readLine());
				br++;
				if(br==48)
					break;
				
				}
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			this.in.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dodajNaSto(String odg) {
		if(odg.equals("")) {
			System.err.println("TABLA!");
			return;
		}
		String niz1[]=odg.split("#");
		
		for(int i=0;i<niz1.length;i++) {
			String niz[]=niz1[i].split(" ");
			Karta k = new Karta(niz[1],Integer.parseInt(niz[0]));
			igr.karteNaStolu.add(k);
		}
	}
	
	public void dodajURuke(String odg) {
		
		String niz[]=odg.split(" ");
		Karta k = new Karta(niz[1],Integer.parseInt(niz[0]));
		igr.karteURuci.add(k);
		
	}
}
