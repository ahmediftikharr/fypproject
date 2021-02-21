package com.uog.smartlibraryfyp;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class DetailsIssuedBookFragment extends BaseFragment implements View.OnClickListener{

    TextView mTextViewBookName,mTextViewBookCategory,mTextViewBookIssuedDate,mTextViewBookReturnedDate,mTextViewBookFine;
    Button mButtonBack;

    private ProgressDialog progressDialog;


    //String book_details_url="http://fypsmartlibrary.000webhostapp.com/book_details.php";
    String book_details_url="http://samighumman.com/book_details.php";

    public DetailsIssuedBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_issued_book, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        init();
        listen();
        loadBookData();
    }



    private void init()
    {
        mTextViewBookName=(TextView)getView().findViewById(R.id.txt_book_name);
        mTextViewBookCategory=(TextView)getView().findViewById(R.id.txt_book_category);
        mTextViewBookIssuedDate=(TextView)getView().findViewById(R.id.txt_book_issue_date);
        mTextViewBookReturnedDate=(TextView)getView().findViewById(R.id.txt_book_returned_date);
        mTextViewBookFine=(TextView)getView().findViewById(R.id.txt_book_fine);

        mButtonBack=(Button)getView().findViewById(R.id.btn_back_book_list);

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
    }

    private void listen()
    {
            mButtonBack.setOnClickListener(this);
    }

    private void loadBookData()
    {
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, book_details_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();
                        JSONArray jsonArray= null;
                        try {
                            jsonArray = new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);

                                String book_name=jsonObject.getString("book_name");
                                String book_category=jsonObject.getString("book_category");
                                String issued_date=jsonObject.getString("issued_date");
                                String returned_date=jsonObject.getString("returned_date");
                                String fine=jsonObject.getString("fine");


                                mTextViewBookName.setText(""+book_name);
                                mTextViewBookCategory.setText(""+book_category);
                                mTextViewBookIssuedDate.setText("Issued Date: "+issued_date);
                                mTextViewBookFine.setText("Fine : "+fine);
                                if(returned_date.equals("0000-00-00"))
                                {
                                    mTextViewBookReturnedDate.setText("Status: ACTIVE");
                                }
                                else
                                {
                                    mTextViewBookReturnedDate.setText("Returned Date: "+returned_date);
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
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {

                SharedPreferences pref = getContext().getSharedPreferences("smart_library", MODE_PRIVATE);
                String issued_id=pref.getString("issued_id","");

                Map<String,String> params=new HashMap<String, String>();
                params.put("issued_id",issued_id);
                return params;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


    }


    @Override
    public void onClick(View view)
    {
        int id=view.getId();
        if(id==R.id.btn_back_book_list)
        {

            SharedPreferences pref = getContext().getSharedPreferences("smart_library", MODE_PRIVATE);
            String previous_fragment=pref.getString("fragment","");

            if(previous_fragment.equals("current_issue"))
            {
                CurrentIssueBooksFragment mFrag=new CurrentIssueBooksFragment();
                mHelper.replaceFragment(R.id.frame_layout,mFrag,false);
            }
            else if(previous_fragment.equals("returned_books"))
            {
                IssuedBooksFragment mFrag=new IssuedBooksFragment();
                mHelper.replaceFragment(R.id.frame_layout,mFrag,false);
            }

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) getView().findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            SharedPreferences pref = getContext().getSharedPreferences("smart_library", MODE_PRIVATE);
            String previous_fragment=pref.getString("fragment","");

            if(previous_fragment.equals("current_issue"))
            {
                CurrentIssueBooksFragment mFrag=new CurrentIssueBooksFragment();
                mHelper.replaceFragment(R.id.frame_layout,mFrag,false);
            }
            else if(previous_fragment.equals("returned_books"))
            {
                IssuedBooksFragment mFrag=new IssuedBooksFragment();
                mHelper.replaceFragment(R.id.frame_layout,mFrag,false);
            }
        }
    }
}
