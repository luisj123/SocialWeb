
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class Client extends Thread {

    private boolean run;
    private String IP;
    private int port;
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        IP = "localhost";
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
        System.out.println(" 8. Logout");
        System.out.print(" > ");

    }
    
    public void showLogin() {

        System.out.println("--- SocialWeb ---");
        System.out.println(" 1. Login");
        System.out.println(" 2. Register");
        System.out.println(" 3. Exit");
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
        showLogin();
        while (run) {            
            try {
                out.writeUTF("");
                Scanner sc = new Scanner(System.in);
                int opcao=sc.nextInt();
                switch (opcao) {
                    case 1: opcao=1;
                        Login(0); 
                        showMenu();
                        break;
                    case 2: opcao=2;
                        Login(1);
                        break;
                    case 3: opcao=3;
                        return;
                    default: System.out.println("\n<<OPÇAO ERRADA!>>\n");
                        showLogin();
               }
            } catch (IOException e) {
                run = false;
                System.out.println("Erro: " + e.getMessage());
            }
        }

    }
    private String Login(int flag){
        String username,password,id="";
        Scanner sc = new Scanner(System.in);
        if(flag==0) {
            System.out.println("Login data\n");
            try{
            out.writeUTF("check_login");
            } catch (IOException e) {
                run = false;
                System.out.println("Erro: " + e.getMessage());
            }
        }
        else {
            System.out.println("Fill in data\n");
            try{
                out.writeUTF("make_register");
            } catch (IOException e) {
                run = false;
                System.out.println("Erro: " + e.getMessage());
            }
        }
        System.out.print("Username:");
        username=sc.nextLine();        
        
        System.out.print("Password:");
        password=sc.nextLine();
        try{
            out.writeUTF(username);
            out.writeUTF(password);
        } catch (IOException e) {
                run = false;
                System.out.println("Erro: " + e.getMessage());
        }
        // id = tools.readLine();
    
        return id;
    }
}
