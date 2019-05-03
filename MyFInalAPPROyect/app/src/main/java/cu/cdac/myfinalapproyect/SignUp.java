package cu.cdac.myfinalapproyect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText editEmail, editPassword, editUserName;
        SignUpHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editUserName = findViewById(R.id.editSignUser);
        editEmail = findViewById(R.id.editSignEmail);
        editPassword = findViewById(R.id.editSignPassw);

        helper = new SignUpHelper(this);


    }


    public void submitData(View v) {
        String strUserName, strPassw, strEmail;
        strUserName = editUserName.getText().toString();
        strPassw = editPassword.getText().toString();
        strEmail = editEmail.getText().toString();

        helper.addUser(strUserName, strEmail, strPassw);
        Toast.makeText(this, "signUp Successfully", Toast.LENGTH_LONG).show();
    }
}
