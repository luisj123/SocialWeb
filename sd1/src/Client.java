
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread {

    private boolean run;
    private String IP;
    private int port;
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        IP = "127.0.0.1";
        port = 7000;
        run = true;
    }

    public void showMenu() {

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

    @Override
    public void run() {
        try {
            s = new Socket(InetAddress.getByName(IP), port);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());

        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
        
        while (run) {

            showMenu();
            try {
                out.writeUTF("postTxt");
                out.writeUTF("user");
                in.readUTF();
            } catch (IOException e) {
                run = false;
                System.out.println("Erro: " + e.getMessage());
            }
        }

    }
}
