
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
    private PreparedStatement st;
    private ResultSet rs;

    Post(String _text, User _Author) {
        text = _text;
        date = Calendar.getInstance();
        Author = _Author;
        post_childs = null;
    }

    Post(Object _img, User _Author) {
        img = _img;
        date = Calendar.getInstance();
        Author = _Author;
        post_childs = null;
    }

    public void addPost() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sd.db");
            statement = connection.createStatement();
            String INSERT_RECORD = "insert into posts (author, object, date, time) values(?,?,?,?)";

            st = connection.prepareStatement(INSERT_RECORD);
            st.setString(1, Author.getName());
            st.setString(2, text);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime().getTime());
            st.setDate(3, sqlDate);
            java.sql.Time sqlTime = new java.sql.Time(date.getTime().getTime());
            st.setTime(4, sqlTime);

            
            st.executeUpdate();
            rs = statement.executeQuery("select last_insert_rowid();");
            ID = rs.getInt(1);
            
            connection.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public void editPost(String _new, String _ID) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sd.db");
            statement = connection.createStatement();
            String EDIT_RECORD = "update posts set object='" + _new + "' where id=" + _ID + ";";
            // ainda tem q se adicionar um failsafe!!!
            statement.executeUpdate(EDIT_RECORD);

            connection.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public void delPost(String to_del, String _ID) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sd.db");
            statement = connection.createStatement();
            String DELETE_RECORD = "delete from posts where id=" + _ID + ";";
            // ainda tem q se adicionar um failsafe!!!
            statement.execute(DELETE_RECORD);

            connection.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public void delImg(String readUTF) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void reply(Post newPost) {
        post_childs = newPost;
    }

    public void IM() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
