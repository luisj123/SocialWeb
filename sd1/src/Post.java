
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class Post {

    private String text;
    private Object img;
    private Calendar date;
    private Object ID;
    private Post post_childs;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    
    Post(String _text){
        text = _text;
        date = Calendar.getInstance();
        // ID = ;
        addPost();
    }
    
    Post(Object _img){
        img = _img;
        date = Calendar.getInstance();
        // ID = ;
    }

    private void addPost() {
        try{
            Class.forName("org.sqlite.JDBC");  
            connection = DriverManager.getConnection("jdbc:sqlite:sd.db");  
            statement = connection.createStatement();  
            resultSet = statement.executeQuery("Select * from Users");
        } catch (Exception e){
            System.out.println("Erro: "+e.getMessage());
        }
    }
    
}
