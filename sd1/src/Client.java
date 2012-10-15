
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client {

    
    public static void main(String args[]){
        
        System.out.println("Este é o cliente!");
        connect c = new connect("localhost",6000);
        
        
        
        
    }
    
    
    
    
    
    static class connect extends Thread{
        
        private String IP;
        private int port;
        private Socket s;
        private DataInputStream in;
        private DataOutputStream out;
        
        connect(String _IP, int _port){
            IP = _IP;
            port = _port;
        }
        
        @Override
        public void run(){
            try{
                s = new Socket(InetAddress.getByName(IP),port);
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());
            }
            catch(Exception e){
                System.out.println("erro: " + e.getMessage());
            }
        }
        
        
    }
    
    
    
}
