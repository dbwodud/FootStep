package youjaeyoung.org.footsteps;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongseoul on 2017. 11. 13..
 */


public class SignUpActivity extends MainActivity {
    private EditText Email_Edit;
    private EditText Pass_Edit;
    private Button signUp_Btn;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    public void createUser(String email,String password) {
        mDatabase = database.getReference();
        mAuth.createUserWithEmailAndPassword(Email_Edit.getText().toString(), Pass_Edit.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            mDatabase.setValue(user.getUid().toString());
                            mDatabase.child(user.getUid().toString()).setValue("memo");
                            mDatabase.child(user.getUid().toString()).setValue("Calender");
                            if(!user.isAnonymous()){
                                Intent intent = new Intent(SignUpActivity.this,RecTextView.class);
                                startActivity(intent);
                            }
                        }

                        // ...
                    }
                });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        mAuth=FirebaseAuth.getInstance();

        Email_Edit = (EditText) findViewById(R.id.Edit_UserID);
        Pass_Edit = (EditText) findViewById(R.id.Edit_UserPASS);

        signUp_Btn=(Button)findViewById(R.id.SignUp_button);
        signUp_Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                createUser(Email_Edit.getText().toString(),Pass_Edit.getText().toString());
            }
        });
    }
}
