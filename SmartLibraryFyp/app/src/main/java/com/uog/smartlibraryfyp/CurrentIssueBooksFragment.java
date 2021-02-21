package com.uog.smartlibraryfyp;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentIssueBooksFragment extends BaseFragment {

    //String current_books_url="http://fypsmartlibrary.000webhostapp.com/current_issue_books.php";
    String current_books_url="http://samighumman.com//current_issue_books.php";
    private ProgressDialog progressDialog;
    private List<Book> array=new ArrayList<Book>();
    private ListView listView;
    private BookAdapter bookAdapter;

    public CurrentIssueBooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_issue_books, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        loadList();
        listen();
    }



    private void init()
    {
        listView=(ListView) getView().findViewById(R.id.list_view_current_issue);
        bookAdapter=new BookAdapter(getContext(),R.layout.issued_book_layout);
        listView.setAdapter(bookAdapter);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    private void listen()
    {

    }

    private void loadList()
    {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(current_books_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject;
                    int count=0;
                    String book_name="";
                    String issued_date="";
                    String book_category="";
                    String issued_id="";
                    while (count<response.length())
                    {
                        jsonObject=response.getJSONObject(count);
                        book_name= jsonObject.getString("book_name");
                        issued_date= jsonObject.getString("issued_date");
                        book_category= jsonObject.getString("book_category");
                        issued_id= jsonObject.getString("issued_id");

                        Book book=new Book();
                        book.setBook_name(book_name);
                        book.setBook_issued_date(issued_date);


                        Bitmap bitmap = null;
                        if(book_category.equals("Computer science"))
                        {
                            bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.computer);
                        }
                        else if(book_category.equals("Philosophy and psychology"))
                        {
                            bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.philosophy);
                        }
                        else if(book_category.equals("Religion"))
                        {
                            bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.religion);
                        }
                        else if(book_category.equals("Social Sciences"))
                        {
                            bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.social_science);
                        }
                        else if(book_category.equals("Language"))
                        {
                            bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.language);
                        }
                        else if(book_category.equals("Science"))
                        {
                            bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.science);
                        }
                        else if(book_category.equals("Technology and applied science"))
                        {
                            bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.technology);
                        }
                        else if(book_category.equals("Arts and recreation"))
                        {
                            bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.arts);
                        }
                        else if(book_category.equals("Literature"))
                        {
                            bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.literature);
                        }
                        else if(book_category.equals("History and geography"))
                        {
                            bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.history);
                        }

                        book.setImg_book_type(bitmap);




                        book.setIssued_id(issued_id);



                        bookAdapter.add(book);
                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                bookAdapter.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                Book obj=(Book)listView.getItemAtPosition(i);
                String id=obj.getIssued_id();
                SharedPreferences pref = getContext().getSharedPreferences("smart_library", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("issued_id",""+id);
                editor.putString("fragment","current_issue");
                editor.commit();


                DetailsIssuedBookFragment mFrag=new DetailsIssuedBookFragment();
                mHelper.replaceFragment(R.id.frame_layout,mFrag,false);

            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) getView().findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            MenuFragment mFrag=new MenuFragment();
            mHelper.replaceFragment(R.id.frame_layout,mFrag,true);
        }
    }

}
