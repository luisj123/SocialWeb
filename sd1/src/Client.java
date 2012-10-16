
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class Client {

    private connect c;
    private boolean run;
    
    Client(){
        c = new connect("127.0.0.1",7000);
        run = true;
    }
    
    
    void run(){
         c.start();
         
         while(run){
             
             c.showMenu();
             try{
                c.out().writeUTF(null);
                c.in().readUTF();
             } catch (IOException e){
                 System.out.println("Erro: "+e.getMessage());
             }
         }
         
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
        
        
        public void showMenu(){
            
            System.out.println("--- SocialWeb ---");
            System.out.println(" 1. Novo Post");
            System.out.println(" 2. Editar Post");
            System.out.println(" 3. Eliminar Post");
            System.out.println(" 4. Nova Imagem");
            System.out.println(" 5. Eliminar Imagem");
            System.out.println(" 6. Responder a Post");
            System.out.println(" 7. Enviar IM");
            System.out.print(" > ");
            
        }
        
        
        public DataInputStream in(){
            return in;
        }
        
        public DataOutputStream out(){
            return out;
        }
        
    }
    
    
    
}
