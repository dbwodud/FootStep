package youjaeyoung.org.footsteps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

public class DataShow extends Activity{
    private ArrayAdapter<String> adapter;
    private ArrayList<String> data = new ArrayList<>();
    private int i;
    private int c;
    @Override
    public void onCreate(Bundle savedInstanceStata){
        super.onCreate(savedInstanceStata);
        setContentView(R.layout.datashow);

        Intent intent = getIntent();
        i = intent.getIntExtra("index",0);
        c = 0;

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

        ListView memo_list = (ListView) findViewById(R.id.Memo_List);
        memo_list.setAdapter(adapter);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("data").child(currentUser.getUid().toString());
        databaseReference.child("memo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d : dataSnapshot.getChildren()) {
                    if (c == i){
                        adapter.add(d.child("Title").getValue().toString());
                        adapter.add(d.child("Contents").getValue().toString());
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
}
