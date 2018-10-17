package youjaeyoung.org.footsteps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongseoul on 2017. 12. 8..
 */

public class ViewCalendar extends Activity implements View.OnClickListener{
    private int i;
    private int c;
    private EditText textview;
    private DatabaseReference DelregDB;
    private DatabaseReference EditregDB;
    @Override
    public void onCreate(Bundle savedInstanceStata) {
        super.onCreate(savedInstanceStata);
        setContentView(R.layout.view_calendar);
        ImageButton savebtn = (ImageButton) findViewById(R.id.btnSave);
        savebtn.setOnClickListener(this);
        ImageButton del = (ImageButton) findViewById(R.id.btndel);
        del.setOnClickListener(this);
        ImageButton menu = (ImageButton) findViewById(R.id.btnMenu);
        menu.setOnClickListener(this);
        ImageButton back = (ImageButton) findViewById(R.id.btnBack);
        back.setOnClickListener(this);
        menu.setOnClickListener(this);
        Intent intent = getIntent();
        textview = (EditText) findViewById(R.id.EditTextContents);
        i = intent.getIntExtra("Index",0);
        c = 0;
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("data").child(currentUser.getUid().toString());
        databaseReference.child("Calendar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d : dataSnapshot.getChildren()) {
                    if (c == i){
                        textview.setText(d.child("Contents").getValue().toString());
                        DelregDB=d.child("Contents").getRef();
                        EditregDB=d.getRef();
                        break;
                    }
                    else{
                        c++;
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                Intent intent = new Intent(getApplicationContext(),CalenderListActivity.class);
                startActivity(intent);
                break;
            case R.id.btndel:
                EditregDB.removeValue();
                intent = new Intent(getApplicationContext(),CalenderListActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSave:
                Map<String,String> map = new HashMap<String,String>();
                map.put("Contents",textview.getText().toString());
                EditregDB.setValue(map);
                intent = new Intent(getApplicationContext(),CalenderListActivity.class);
                startActivity(intent);
                break;
            case R.id.btnMenu:
                intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}