package cu.cdac.myfinalapproyect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText login, passw;
    Button btnLogin, btnSign;
    SignUpHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.editLogin);
        passw = findViewById(R.id.editPassw);
        btnLogin = findViewById(R.id.login);
        btnSign = findViewById(R.id.signig);

        helper = new SignUpHelper(this);
    }

    public void login(View view) {
        String strLogin, strPassw;
        strLogin = login.getText().toString();
        strPassw = passw.getText().toString();

        Boolean userByEmail = helper.getUserByEmail(strLogin, strPassw);
        Log.d("TTTT",userByEmail.toString()+" "+strLogin+"  "+strPassw);

        if(userByEmail){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
        }
    }

    public void siginup(View view) {
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }
}
