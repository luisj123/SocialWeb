
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
                new feedThread(clientSocket).start();
            }
        } catch (IOException e) {
            // Tem q terminar as threads abertas aqui!
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
    private String id;
    private User user;

    public clientThread(Socket _s) {
        s = _s;
        run = true;
    }

    @Override
    public void run() {
        String username_check, password_check;

        try {
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());

            while (run) {
                String rsp = in.readUTF();
                System.out.println(rsp);

                // NAO TEM A VER COM OS POSTS!
                if (rsp.equals("check_login")) {
                    username_check = in.readUTF();
                    password_check = in.readUTF();
                    user = new User(username_check);
                    out.writeInt(user.loginUser(username_check, password_check));
                } else if (rsp.equals("make_register")) {
                    username_check = in.readUTF();
                    password_check = in.readUTF();
                    user = new User(username_check);
                    out.writeInt(user.registerUser(username_check, password_check));
                } else if (rsp.equals("exit")) {
                    run = false;
                    out.close();
                    in.close();
                    s.close();
                } else {

                    // JA TEM A VER COM OS POSTS!
                    id = in.readUTF();
                    Post thePost = new Post(id, user);

                    if (rsp.equals("postTxt")) {
                        thePost.addPost();

                    } else if (rsp.equals("postImg")) {
                        thePost.addPost();

                    } else if (rsp.equals("editPost")) {
                        thePost.editPost(in.readUTF(), id);

                    } else if (rsp.equals("delPost")) {
                        thePost.delPost(in.readUTF(), id);

                    } else if (rsp.equals("delImg")) {
                        thePost.delImg(in.readUTF());

                    } else if (rsp.equals("replyPost")) {
                        Post newPost = new Post(id, user);
                        thePost.reply(newPost);

                    } else if (rsp.equals("sendIM")) {
                        thePost.IM();

                    }
                }
            }
        } catch (Exception e) {
            run = false;
            System.out.println("erro: " + e.getMessage());
        }
    }
}

class feedThread extends Thread {

    private Socket s;
    private boolean run;
    private DataInputStream in;
    private DataOutputStream out;

    public feedThread(Socket _s) {
        s = _s;
        run = true;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());

            while (run) {
                sleep(1000);
                
            }
        } catch (Exception e) {
            run = false;
            System.out.println("Erro: " + e);
        }
    }
}