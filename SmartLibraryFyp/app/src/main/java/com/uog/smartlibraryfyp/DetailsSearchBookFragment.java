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

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsSearchBookFragment extends BaseFragment implements View.OnClickListener {


    TextView mTextViewBookName,mTextViewBookAuthor,mTextViewBookEdition,mTextViewBookShelf,mTextViewBookRack,
            mTextViewBookQuantity,mTextViewBookStatus;
    Button mButtonBack;

    private ProgressDialog progressDialog;
    public DetailsSearchBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_search_book, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        listen();
        loadBookData();
    }

    private void init()
    {
        mTextViewBookName=(TextView)getView().findViewById(R.id.txt_book_name);
        mTextViewBookAuthor=(TextView)getView().findViewById(R.id.txt_book_author);
        mTextViewBookEdition=(TextView)getView().findViewById(R.id.txt_book_edition);
        mTextViewBookShelf=(TextView)getView().findViewById(R.id.txt_book_shelf);
        mTextViewBookRack=(TextView)getView().findViewById(R.id.txt_book_rack);
        mTextViewBookQuantity=(TextView)getView().findViewById(R.id.txt_book_quantity);
        mTextViewBookStatus=(TextView)getView().findViewById(R.id.txt_book_status);


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

        SharedPreferences pref = getContext().getSharedPreferences("smart_library", MODE_PRIVATE);


        String book_name = pref.getString("book_name","");
        String book_author = pref.getString("book_author","");
        String book_edition = pref.getString("book_edition","");
        String book_shelf = pref.getString("book_shelf","");
        String book_rack = pref.getString("book_rack","");
        String book_quantity = pref.getString("book_quantity","");
        String book_status = pref.getString("book_status","");

        mTextViewBookName.setText(""+book_name);
        mTextViewBookAuthor.setText("Author: "+book_author);
        mTextViewBookEdition.setText("Edition: "+book_edition);
        mTextViewBookShelf.setText("Shelf: "+book_shelf);
        mTextViewBookRack.setText("Rack: "+book_rack);
        mTextViewBookQuantity.setText("Quantity: "+book_quantity);
        mTextViewBookStatus.setText("Status: "+book_status);

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
            else if(previous_fragment.equals("search_books"))
            {
                SearchBookFragment mFrag=new SearchBookFragment();
                mHelper.replaceFragment(R.id.frame_layout,mFrag,false);
            }
        }
    }

    @Override
    public void onClick(View view)
    {
        int id=view.getId();
        if(id==R.id.btn_back_book_list)
        {
            SearchBookFragment mFrag=new SearchBookFragment();
            mHelper.replaceFragment(R.id.frame_layout,mFrag,true);
        }
    }
}
