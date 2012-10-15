
public class main {
    

    // Para correr o servidor ou cliente recorremos aos argumentos que inicializamos o programa
    
    public static void main(String args[]){
        
        if(args[0].equals("server")){
            new Server().start();
            
        }
        else if(args[0].equals("client")){
            new Client().run();
        }
    }
    
            
}
