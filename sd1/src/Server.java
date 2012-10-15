
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    
    private boolean run;
    private ServerSocket ss;
    private Socket clientSocket;
    
    @Override
    public void run(){
        
        while(run){
            try {
                ss = new ServerSocket();
                clientSocket = ss.accept();
                new client(clientSocket).start();
                
            } catch (IOException e) {
                System.out.println("erro: " + e.getMessage());
            }
            
            
            
            
            
        }
        
        
    }
    
    class client extends Thread{
        
        private Socket s;
        private DataInputStream in;
        private DataOutputStream out;
        
        client(Socket _s){
            s = _s;
        }
        
        @Override
        public void run(){
            try{
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());
            }
            catch(Exception e){
                System.out.println("erro: " + e.getMessage());
            }
        }
        
    }
    
    
    
    
}
