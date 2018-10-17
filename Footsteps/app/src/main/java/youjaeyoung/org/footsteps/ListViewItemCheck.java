package youjaeyoung.org.footsteps;

import android.app.Activity;
import android.widget.CheckBox;

/**
 * Created by dongseoul on 2017. 12. 5..
 */

public class ListViewItemCheck extends Activity{
    private String text;

    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
}
