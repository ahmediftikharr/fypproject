package com.uog.smartlibraryfyp;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchBookFragment extends BaseFragment implements View.OnClickListener {
    //String search_books_url = "http://fypsmartlibrary.000webhostapp.com/search_book.php";
    String search_books_url = "http://samighumman.com/search_book.php";
    private ProgressDialog progressDialog;
    private List<Book> array = new ArrayList<Book>();
    private ListView listView;
    private SearchBookAdapter searchBookAdapter;

    EditText mEditTextSearchBook;
    Button mButtonSearchBook;

    public SearchBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_book, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        listen();
    }


    private void init() {
        listView = (ListView) getView().findViewById(R.id.list_view_search_books);
        searchBookAdapter = new SearchBookAdapter(getContext(), R.layout.search_book_layout);
        listView.setAdapter(searchBookAdapter);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");

        mEditTextSearchBook = (EditText) getView().findViewById(R.id.et_search_book);
        mButtonSearchBook = (Button) getView().findViewById(R.id.btn_search_book);


    }

    private void listen() {
        mButtonSearchBook.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_search_book) {
            progressDialog.show();
            Book obj=new Book();
            searchBookAdapter.clearData();
            loadList();
        }
    }

    private void loadList() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, search_books_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        int count = 0;
                        String book_name = "";
                        String book_author = "";
                        String book_edition = "";
                        String book_shelf = "";
                        String book_rack = "";
                        String book_quantity = "";
                        String book_status = "";
                        String book_category = "";
                        JSONArray jsonArray = null;
                        JSONObject jsonObject;
                        try {
                            jsonArray = new JSONArray(response);
                            while (count < response.length()) {
                                jsonObject = jsonArray.getJSONObject(count);
                                book_name = jsonObject.getString("book_name");
                                book_author = jsonObject.getString("book_author");
                                book_edition = jsonObject.getString("book_edition");
                                book_shelf = jsonObject.getString("book_shelf");
                                book_rack = jsonObject.getString("book_rack");
                                book_quantity = jsonObject.getString("book_quantity");
                                book_status = jsonObject.getString("book_status");


                                Book book = new Book();
                                book.setBook_name(book_name);
                                book.setBook_author(book_author);
                                book.setBook_edition(book_edition);
                                book.setBook_shelf(book_shelf);
                                book.setBook_rack(book_rack);
                                book.setBook_quantity(book_quantity);
                                book.setBook_status(book_status);


                                Bitmap bitmap = null;
                                if (book_category.equals("Computer science")) {
                                    bitmap = BitmapFactory.decodeResource(getResources(),
                                            R.drawable.computer);
                                } else if (book_category.equals("Philosophy and psychology")) {
                                    bitmap = BitmapFactory.decodeResource(getResources(),
                                            R.drawable.philosophy);
                                } else if (book_category.equals("Religion")) {
                                    bitmap = BitmapFactory.decodeResource(getResources(),
                                            R.drawable.religion);
                                } else if (book_category.equals("Social Sciences")) {
                                    bitmap = BitmapFactory.decodeResource(getResources(),
                                            R.drawable.social_science);
                                } else if (book_category.equals("Language")) {
                                    bitmap = BitmapFactory.decodeResource(getResources(),
                                            R.drawable.language);
                                } else if (book_category.equals("Science")) {
                                    bitmap = BitmapFactory.decodeResource(getResources(),
                                            R.drawable.science);
                                } else if (book_category.equals("Technology and applied science")) {
                                    bitmap = BitmapFactory.decodeResource(getResources(),
                                            R.drawable.technology);
                                } else if (book_category.equals("Arts and recreation")) {
                                    bitmap = BitmapFactory.decodeResource(getResources(),
                                            R.drawable.arts);
                                } else if (book_category.equals("Literature")) {
                                    bitmap = BitmapFactory.decodeResource(getResources(),
                                            R.drawable.literature);
                                } else if (book_category.equals("History and geography")) {
                                    bitmap = BitmapFactory.decodeResource(getResources(),
                                            R.drawable.history);
                                }

                                book.setImg_book_type(bitmap);


                                searchBookAdapter.add(book);


                                count++;
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        searchBookAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                String name=mEditTextSearchBook.getText().toString();
                params.put("name",name);
                return params;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                Book obj=(Book)listView.getItemAtPosition(i);

                String book_name = obj.getBook_name();
                String book_author = obj.getBook_author();
                String book_edition = obj.getBook_edition();
                String book_shelf = obj.getBook_shelf();
                String book_rack = obj.getBook_rack();
                String book_quantity = obj.getBook_quantity();
                String book_status = obj.getBook_status();


                SharedPreferences pref = getContext().getSharedPreferences("smart_library", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("book_name",""+book_name);
                editor.putString("book_author",""+book_author);
                editor.putString("book_edition",""+book_edition);
                editor.putString("book_shelf",""+book_shelf);
                editor.putString("book_rack",""+book_rack);
                editor.putString("book_quantity",""+book_quantity);
                editor.putString("book_status",""+book_status);
                editor.putString("fragment","search_books");
                editor.commit();


                DetailsSearchBookFragment mFrag=new DetailsSearchBookFragment();
                mHelper.replaceFragment(R.id.frame_layout,mFrag,false);

            }
        });


    }

    @Override
    public void onBackPressed() {

            MenuFragment mFrag = new MenuFragment();
            mHelper.replaceFragment(R.id.frame_layout, mFrag, true);
        }
    }
