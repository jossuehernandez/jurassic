package com.example.jos.shopcart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jos.shopcart.com.example.jos.shopcart.utils.Validator;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;


public class SignIn extends AppCompatActivity {

    private static final String TAG = "SignIn";
    private TextView mTextView;

    private TextView mEmail;
    private TextView mPasswd;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Firebase myFirebase;

    DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRootChild = mDatabaseReference.child("texto");

    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sign_in);
        mTextView = findViewById( R.id.txtMsg );
        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.fieldEmail);
        mPasswd = findViewById(R.id.fieldPasswd);

        Firebase.setAndroidContext(this);
        String DeviceID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        myFirebase = new Firebase("https://demofirebase-e47af.firebaseio.com/");

        Firebase myNewChild = myFirebase.child("Users");
        myNewChild.setValue("JosdesiTest");

        Firebase myNewChild2 = myFirebase.child("-LN8t4CfC7lr3HXqhucG");
        Map<String,Object> datos = new HashMap<>();
        datos.put("nombre","Jossssssssssssss");
        datos.put("apellido","Hedez");
        datos.put("telefono","555555555");
        datos.put("direccion","nte 11");
        myNewChild2.setValue( datos );







//        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null){

                    Intent intent = new Intent( SignIn.this, HomeActivity.class );
                    startActivity( intent );

                }
            }
        };


        SignInButton btnSignGoogleIn = findViewById( R.id.sign_google_button );
        btnSignGoogleIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });




        Button btnSignIn = findViewById( R.id.btnSignIn );

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();


            }
        });

        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sigIn();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent( SignIn.this, HomeActivity.class );
                            startActivity( intent );
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }

                        // ...
                    }
                });

    }


    private void sigIn() {




        String email = mEmail.getText().toString();
        String password = mPasswd.getText().toString();


        if( !Validator.Email.validate( email ) ){
            Toast.makeText(SignIn.this, "El email no es válido ", Toast.LENGTH_LONG).show();
            return;
        }
        else if( !Validator.Password.validate( password ) ){
            Toast.makeText(SignIn.this, "El password no es válido ", Toast.LENGTH_LONG).show();
            return;
        }




        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            //checking if success
            if (task.isSuccessful()) {

                Toast.makeText(SignIn.this, "Se ha registrado el usuario con el email: " + mEmail, Toast.LENGTH_LONG).show();
            } else {
                if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                    Toast.makeText(SignIn.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignIn.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                }
            }
        }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);


        Map<String,Object> datos = new HashMap<>();
        datos.put("nombre","Joss");
        datos.put("apellido","Hedez");
        datos.put("telefono","55678910");
        datos.put("direccion","nte 11");
        mDatabaseReference.child("usuario").setValue( datos );

        mRootChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String texto = dataSnapshot.getValue().toString();
                mTextView.setText( texto );
                Log.d(TAG, "____________" );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStop() {

        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void login(){

        String email = mEmail.getText().toString();
        String password = mPasswd.getText().toString();

        if( email.equals("") || password.equals("") ) return;

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

}
