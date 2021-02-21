package com.uog.smartlibraryfyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText mEditTextRoll,mEditTextPass;
    Button mButtonLogin;
    private ProgressDialog progressDialog;
    CheckBox mCheckBoxRemember;
    ImageView mImageViewInstagram,mImageViewFacebook,mImageViewTwitter;
    String login_url="https://samighumman.com/student_login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        listen();
    }

    private void init()
    {
        mEditTextRoll=(EditText)findViewById(R.id.et_roll);
        mEditTextPass=(EditText)findViewById(R.id.et_pass);

        mButtonLogin=(Button)findViewById(R.id.btn_login);
        mCheckBoxRemember=(CheckBox)findViewById(R.id.checkbox_rememberme);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        mImageViewInstagram=(ImageView)findViewById(R.id.insta_image);
        mImageViewFacebook=(ImageView)findViewById(R.id.facebook_image);
        mImageViewTwitter=(ImageView)findViewById(R.id.twitter_image);

    }

    private void listen()
    {
        mButtonLogin.setOnClickListener(this);
        mImageViewInstagram.setOnClickListener(this);
        mImageViewFacebook.setOnClickListener(this);
        mImageViewTwitter.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {
        int id=view.getId();
        if(id==R.id.btn_login)
        {
            progressDialog.show();
            StringRequest stringRequest=new StringRequest(Request.Method.POST, login_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response)
                        {
                            progressDialog.dismiss();
                            JSONArray jsonArray= null;
                            try {
                                jsonArray = new JSONArray(response);
                                JSONObject jsonObject=jsonArray.getJSONObject(0);
                                String status=jsonObject.getString("result");
                                if(status.equals("success"))
                                {
                                    String roll=jsonObject.getString("roll");
                                    String name=jsonObject.getString("name");

                                    SharedPreferences pref = getApplicationContext().getSharedPreferences("smart_library", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("roll",""+roll);
                                    editor.putString("name",""+name);

                                    if(mCheckBoxRemember.isChecked())
                                    {
                                        editor.putString("remember","yes");
                                    }

                                    editor.commit();


                                    Intent mIntent=new Intent(LoginActivity.this,MenuActivity.class);
                                    startActivity(mIntent);

                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String,String> params=new HashMap<String, String>();
                    String roll=mEditTextRoll.getText().toString();
                    String pass=mEditTextPass.getText().toString();
                    params.put("roll",roll);
                    params.put("pass",pass);
                    return params;
                }
            }
                    ;
            MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
        }



        else if(id==R.id.insta_image)
        {
            CurrentPage.CURRENT_PAGE=1;
            Intent mIntent=new Intent(LoginActivity.this,SocialMediaActivity.class);
            startActivity(mIntent);
        }
        else if(id==R.id.facebook_image)
        {
            CurrentPage.CURRENT_PAGE=2;
            Intent mIntent=new Intent(LoginActivity.this,SocialMediaActivity.class);
            startActivity(mIntent);
        }
        else if(id==R.id.twitter_image)
        {
            CurrentPage.CURRENT_PAGE=3;
            Intent mIntent=new Intent(LoginActivity.this,SocialMediaActivity.class);
            startActivity(mIntent);
        }


    }


    @Override
    public void onBackPressed()
    {
        this.finish();
        System.exit(0);
    }
}
