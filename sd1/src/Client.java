
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

    private boolean run;
    private String IP;
    private int port;
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner sc;

    public Client() {
        IP = "localhost";
        port = 7000;
        run = true;
        sc = new Scanner(System.in);
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

    public int showLogin() {

        System.out.println("--- SocialWeb ---");


        while (run) {
            System.out.println(" 1. Login");
            System.out.println(" 2. Register");
            System.out.println(" 3. Exit");
            System.out.print(" > ");

            switch (sc.nextInt()) {
                case 1:
                    if (Login(0) == 1) {
                        return 1;
                    }
                    break;
                case 2:
                    if (Login(1) == 1) {
                        return 1;
                    }
                    break;
                case 3:
                    run = false;
                    break;
                default:
                    System.out.println("\n<<OPÇAO ERRADA!>>\n");
            }
        }
        return 0;
    }

    @Override
    public void run() {
        try {
            s = new Socket(InetAddress.getByName(IP), port);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            if (showLogin() == 1) {
                while (run) {
                    showMenu();
                    /*
                     * chama o MENU
                     */
                    int opcao = sc.nextInt();
                    sc = new Scanner(System.in);

                    switch (opcao) {
                        case 1:
                            out.writeUTF("postTxt");
                            System.out.print("Texto: ");
                            out.writeUTF(sc.nextLine());
                            break;
                        case 2:
                            out.writeUTF("editPost");
                            System.out.print("ID: ");
                            out.writeUTF(sc.nextLine());
                            System.out.print("Texto: ");
                            out.writeUTF(sc.nextLine());
                            break;
                        case 3:
                            out.writeUTF("delPost");
                            System.out.print("ID: ");
                            out.writeUTF(sc.nextLine());
                            break;
                        case 4:
                            out.writeUTF("postImg");
                            System.out.print("Imagem: ");
                            out.writeUTF(sc.nextLine());
                            break;
                        case 5:
                            out.writeUTF("delImg");
                            System.out.print("ID: ");
                            out.writeUTF(sc.nextLine());
                            break;
                        case 6:
                            out.writeUTF("replyPost");
                            System.out.print("ID: ");
                            out.writeUTF(sc.nextLine());
                            System.out.print("Texto: ");
                            out.writeUTF(sc.nextLine());
                            break;
                        case 7:
                            out.writeUTF("sendIM");
                            break;
                        case 8:
                            out.writeUTF("exit");
                            System.out.println("A terminar o programa!");
                            run = false;
                            break;
                        default:
                            System.out.println("\n<<OPÇAO ERRADA!>>\n");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            run = false;
            System.out.println("erro: " + e);
        }


    }

    private int Login(int flag) {
        String username, password;
        int id = 0;

        sc.nextLine();
        try {
            if (flag == 0) {
                System.out.println("Insira os seus dados \n");
                out.writeUTF("check_login");
            } else {
                System.out.println("Preencha com os seus dados\n");
                out.writeUTF("make_register");
            }
            System.out.print("Username: ");
            username = sc.nextLine();

            System.out.print("Password: ");
            password = sc.nextLine();

            out.writeUTF(username);
            out.writeUTF(password);
            id = in.readInt();
            System.out.println();

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return id;
    }
}
