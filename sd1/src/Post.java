
import java.sql.*;
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
    private PreparedStatement st;
    
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
            String INSERT_RECORD = "insert into posts (author, object, date, time) values(?,?,?,?)";
    
            st = connection.prepareStatement(INSERT_RECORD);
            st.setString(1, Author.getID());
            st.setString(2, text);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime().getTime());
            st.setDate(3, sqlDate);
            java.sql.Time sqlTime = new java.sql.Time(date.getTime().getTime());
            st.setTime(4, sqlTime);
            
            st.executeUpdate();
            
            connection.close();

        } catch (Exception e){
            System.out.println("Erro: "+e);
        }
    }
    
}
