package com.uog.smartlibraryfyp;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {

    TextView mTextViewStudentName,mTextViewStudentRoll,mTextViewStudentMobile,mTextViewStudentDepartment,
            mTextViewStudentBatch,mTextViewStudentEmail,mTextViewStudentFine;

    private ProgressDialog progressDialog;

    String profile_url="https://samighumman.com/student_profile.php";


    public ProfileFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        init();
        setValues();
        listen();
    }



    private void init()
    {
        mTextViewStudentName=(TextView)getView().findViewById(R.id.txt_profile_name);
        mTextViewStudentRoll=(TextView)getView().findViewById(R.id.txt_profile_roll);
        mTextViewStudentMobile=(TextView)getView().findViewById(R.id.txt_profile_mobile);
        mTextViewStudentDepartment=(TextView)getView().findViewById(R.id.txt_profile_department);
        mTextViewStudentBatch=(TextView)getView().findViewById(R.id.txt_profile_batch);
        mTextViewStudentEmail=(TextView)getView().findViewById(R.id.txt_profile_email);
        mTextViewStudentFine=(TextView)getView().findViewById(R.id.txt_profile_fine);

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Profile...");
        progressDialog.show();
    }


    private void setValues()
    {

        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, profile_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();
                        JSONArray jsonArray= null;
                        try {
                            jsonArray = new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            String name=jsonObject.getString("name");
                            String fine=jsonObject.getString("fine");
                            String roll=jsonObject.getString("roll");
                            String mobile=jsonObject.getString("mobile");
                            String department=jsonObject.getString("department");
                            String batch=jsonObject.getString("batch");
                            String email=jsonObject.getString("email");


                            mTextViewStudentName.setText("Name: "+name);
                            mTextViewStudentFine.setText("Fine: "+fine);
                            mTextViewStudentRoll.setText("ID: "+roll);
                            mTextViewStudentMobile.setText("Mobile: "+mobile);
                            mTextViewStudentDepartment.setText("Department: "+department);
                            mTextViewStudentBatch.setText("Batch: "+batch);
                            mTextViewStudentEmail.setText("Email: "+email);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params=new HashMap<String, String>();
                SharedPreferences pref = getContext().getSharedPreferences("smart_library", MODE_PRIVATE);
                String roll=pref.getString("roll","");
                params.put("roll",roll);
                return params;
            }
        }
                ;
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


    }

    private void listen()
    {

    }

    @Override
    public void onBackPressed() {
        MenuFragment mFrag = new MenuFragment();
        mHelper.replaceFragment(R.id.frame_layout, mFrag, true);
    }
}
