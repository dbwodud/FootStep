package youjaeyoung.org.footsteps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by dongseoul on 2017. 12. 1..
 */

public class CalenderListActivity extends Activity implements AdapterView.OnItemClickListener {

    private ArrayList<String> data = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;
    private ListView Calendar_list;
    private boolean del_check;

    @Override
    public void onCreate(Bundle savedInstanceStata) {
        super.onCreate(savedInstanceStata);
        setContentView(R.layout.calendarlist);
        Intent intent = getIntent();
        del_check = false;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        adapter.clear();
        adapter.notifyDataSetChanged();
        Calendar_list = (ListView) findViewById(R.id.Calender_List);
        Calendar_list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        Calendar_list.setOnItemClickListener(this);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("data").child(currentUser.getUid().toString());
        databaseReference.child("Calendar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    adapter.add(d.child("Contents").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Calendar_list.setAdapter(adapter);
        findViewById(R.id.btnMenu).setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void onItemClick(AdapterView<?> parent,View v,int position,long id){
        Calendar_list.setItemChecked(position, true);
        Intent intent = new Intent(getApplicationContext(),ViewCalendar.class);
        intent.putExtra("Index",position);
        startActivity(intent);
    }
}

