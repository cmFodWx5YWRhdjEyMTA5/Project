package cu.cdac.myfinalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText login, passw;
    Button btnLogin, btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.editLogin);
        passw = findViewById(R.id.editPassw);
        btnLogin = findViewById(R.id.login);
        btnSign = findViewById(R.id.signig);

    }

    public void login(View view) {
        String strLogin, strPassw;
        strLogin = login.getText().toString();
        strPassw = passw.getText().toString();

    }

    public void siginup(View view) {
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }
}
