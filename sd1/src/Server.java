
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private boolean run;
    private ServerSocket ss;
    private Socket clientSocket;

    Server() {
        run = true;
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(7000);

            while (run) {

                clientSocket = ss.accept();
                new clientThread(clientSocket).start();
            }
            
        } catch (IOException e) {
            run = false;
            System.out.println("erro: " + e.getMessage());
        }
    }
}

class clientThread extends Thread {

    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean run;
    private String post;

    public clientThread(Socket _s) {
        s = _s;
        run = true;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            while(run){
                String rsp = in.readUTF();
                post = in.readUTF();
                
                if(rsp.equals("postTxt")){
                    Post txt = new Post(post);
                }
                else if(rsp.equals("postImg")){
                    Post img = new Post(post);
                }
                else if(rsp.equals("editPost")){
                }
                else if(rsp.equals("delPost")){
                }
                else if(rsp.equals("delImg")){
                }
                else if(rsp.equals("replyPost")){
                }
                else if(rsp.equals("sendIM")){
                }
            }
        } catch (Exception e) {
            run = false;
            System.out.println("erro: " + e.getMessage());
        }
    }
}
