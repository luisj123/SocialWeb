
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Post {

    private String text;
    private Object img;
    private Calendar date;
    private Object ID;
    private Post post_childs;
    private User Author;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    
    Post(String _text){
        text = _text;
        date = Calendar.getInstance();
        // ID = ;
        Author = new User("Antonio",null,null);
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
            DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");  
            formato.format(date);
            resultSet = statement.executeQuery("insert into post values ('','"+ Author.getID() +"','"+ text +"',"+formato+");");

        } catch (Exception e){
            System.out.println("Erro: "+e);
        }
    }
    
}
