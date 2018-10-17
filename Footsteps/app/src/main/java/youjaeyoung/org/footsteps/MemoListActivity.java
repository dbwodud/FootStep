package youjaeyoung.org.footsteps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.api.client.util.NullValue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by dongseoul on 2017. 12. 1..
 */

public class MemoListActivity extends Activity implements AdapterView.OnItemClickListener {

    private ArrayList<String> data = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;
    private ListView memo_list;
    private boolean del_check;

    @Override
    public void onCreate(Bundle savedInstanceStata) {
        super.onCreate(savedInstanceStata);
        setContentView(R.layout.memolist);
        Intent intent = getIntent();
        del_check = false;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        adapter.clear();
        adapter.notifyDataSetChanged();
        memo_list = (ListView) findViewById(R.id.Memo_List);
        memo_list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        memo_list.setOnItemClickListener(this);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("data").child(currentUser.getUid().toString());
        databaseReference.child("memo").addValueEventListener(new ValueEventListener() {
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
        memo_list.setAdapter(adapter);
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
        memo_list.setItemChecked(position, true);
        Intent intent = new Intent(getApplicationContext(),ViewMemo.class);
        intent.putExtra("Index",position);
        startActivity(intent);
    }
}

