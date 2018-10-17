package youjaeyoung.org.footsteps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by dongseoul on 2017. 12. 8..
 */

public class MenuActivity extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.menu);
        TextView txtRecog = (TextView) findViewById(R.id.txtRecog);
        TextView txtMemo = (TextView) findViewById(R.id.txtMemo);
        TextView txtCalender = (TextView) findViewById(R.id.txtCalendar);
        TextView txtLogout = (TextView) findViewById(R.id.txtLogout);
        txtRecog.setOnClickListener(this);
        txtMemo.setOnClickListener(this);
        txtCalender.setOnClickListener(this);
        txtLogout.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.txtRecog:
                Intent intent = new Intent(MenuActivity.this,RecTextView.class);
                startActivity(intent);
                break;
            case R.id.txtMemo:
                intent = new Intent(MenuActivity.this,MemoListActivity.class);
                startActivity(intent);
                break;
            case R.id.txtCalendar:
                intent = new Intent(MenuActivity.this,CalenderListActivity.class);
                startActivity(intent);
                break;
            case R.id.txtLogout:
                intent = new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
