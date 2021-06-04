package sg.edu.rp.c346.id19004781.c302_p07_addressbook;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.*;


import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreateContactActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etMobile;
    private Button btnCreate;
    private AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etMobile = findViewById(R.id.etMobile);
        btnCreate = findViewById(R.id.btnCreate);
        client = new AsyncHttpClient();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCreateOnClick(v);
            }
        });

    }//end onCreate


    private void btnCreateOnClick(View v) {
        //TODO: call createContact.php to save new contact details
//        Toast.makeText(getApplicationContext(), "Contact details successfully created", Toast.LENGTH_SHORT).show();
//        finish();

        RequestParams params = new RequestParams();
        params.add("FirstName", etFirstName.getText().toString());
        params.add("LastName", etLastName.getText().toString());
        params.add("Mobile", etMobile.getText().toString());

        client.post("http://10.0.2.2/C302_P07/addContact.php", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Log.i("JSON Results: ", response.toString());

                    Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }//end onSuccess
        });
    }//end btnCreateOnClick
}