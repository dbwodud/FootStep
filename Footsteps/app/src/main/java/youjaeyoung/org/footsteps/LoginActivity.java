package youjaeyoung.org.footsteps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dongseoul on 2017. 11. 13..
 */

public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText Id_Edit;
    private EditText Pass_Edit;
    private FirebaseAuth mAuth;
    private Button LoginBtn;
    private Button SignupBtn;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    public void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    public void createUser(String email,String password) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mDatabase;
        mDatabase = database.getReference();
        mAuth.createUserWithEmailAndPassword(Id_Edit.getText().toString(), Pass_Edit.getText().toString())
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
                                Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        // ...
                    }
                });
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth=FirebaseAuth.getInstance();

        Id_Edit = (EditText) findViewById(R.id.edit_userid);
        Pass_Edit = (EditText) findViewById(R.id.edit_userpass);

        LoginBtn=(Button)findViewById(R.id.btn_login);
        LoginBtn.setOnClickListener(this);
        SignupBtn=(Button)findViewById(R.id.btn_signup);
        SignupBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if(Id_Edit.getText().toString()!=""||Pass_Edit.getText().toString()!="") {
                    login(Id_Edit.getText().toString(), Pass_Edit.getText().toString());
                } else{

                }
                break;
            case R.id.btn_signup:
                if(Id_Edit.getText().toString()!=""||Pass_Edit.getText().toString()!="") {
                    createUser(Id_Edit.getText().toString(), Pass_Edit.getText().toString());
                }else{

                }
                break;
            default:
                break;
        }
    }
}
