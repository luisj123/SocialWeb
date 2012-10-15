
import java.util.Calendar;

public class Post {

    private String text;
    private Object img;
    private Calendar date;
    private Object ID;
    private Post post_childs;
    
    
    Post(String _text){
        text = _text;
        date = Calendar.getInstance();
        // ID = ;
    }
    
    Post(Object _img){
        img = _img;
        date = Calendar.getInstance();
        // ID = ;
    }
    
}
