package ejemplo;

public class EjemploMultihilos {
	
	public EjemploMultihilos(){
		Thread hilo1 = new Thread(){
			public void run(){
				//Cuerpo del hilo
				Thread hilo = new Thread(){
					public void run(){
						for(int i=0; i<100;i++){
							try {
								Thread.sleep(1000);
							}
							catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("Contador Hilo1: " +i);
						}
					}
				};
				hilo.start();				
			}
		};
		
		Thread hilo2 = new Thread(){
			public void run(){
				//Cuerpo del hilo
				for(int i=0; i<100;i++){
					try {
						Thread.sleep(2000);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Contador Hilo2: " +i);
				}
			}
		};
		hilo1.start();
		hilo2.start();
		//Otras instrucciones
	}
	public static void main(String[] args) {
		new EjemploMultihilos();
	}

}
