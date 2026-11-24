
package app3;

public class App3 {
    
    public static void main(String[] args) {
    	
    	Thread t1=new Thread(new Runnable(){
    		public void run(){
	
 for(int i=0;i<10;i++){
 	System.out.println("From thread 1 Hello "+ i);
 	/*try{
 		Thread.sleep(100);
 	}catch(InterruptedException e){
 		e.printStackTrace();
 	}*/
 	}
 }
    	});
    	Thread t2=new Thread(new Runnable(){
    		public void run(){
	
 for(int i=0;i<=9;i++){
 	System.out.println("From thread 2 Hello "+ i);
 	/*try{
 		Thread.sleep(100);
 	}catch(InterruptedException e){
 		e.printStackTrace();
 	}*/
 	}
 }
    	});
 	t1.setPriority(9);
        t2.setPriority(1);
    	t1.start();
    	t2.start();
    }
}