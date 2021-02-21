package com.uog.smartlibraryfyp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends BaseFragment implements View.OnClickListener{


    RelativeLayout mRelativeLayoutSearch,mRelativeLayoutMyBooks,mRelativeLayoutIssuedBooks,mRelativeLayoutProfile;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) getView().findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            getActivity().finish();
            System.exit(0);
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        listen();
    }



    private void init()
    {
        mRelativeLayoutSearch=(RelativeLayout)getView().findViewById(R.id.card_layout_1);
        mRelativeLayoutMyBooks=(RelativeLayout)getView().findViewById(R.id.card_layout_2);
        mRelativeLayoutIssuedBooks=(RelativeLayout)getView().findViewById(R.id.card_layout_3);
        mRelativeLayoutProfile=(RelativeLayout)getView().findViewById(R.id.card_layout_4);
    }

    private void listen()
    {
        mRelativeLayoutSearch.setOnClickListener(this);
        mRelativeLayoutMyBooks.setOnClickListener(this);
        mRelativeLayoutIssuedBooks.setOnClickListener(this);
        mRelativeLayoutProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id=view.getId();
        if(id==R.id.card_layout_1)
        {
            SearchBookFragment mFrag=new SearchBookFragment();
            mHelper.replaceFragment(R.id.frame_layout,mFrag,true);
        }
        else if(id==R.id.card_layout_2)
        {
            CurrentIssueBooksFragment mFrag=new CurrentIssueBooksFragment();
            mHelper.replaceFragment(R.id.frame_layout,mFrag,false);
        }
        else if(id==R.id.card_layout_3)
        {
            IssuedBooksFragment mFrag=new IssuedBooksFragment();
            mHelper.replaceFragment(R.id.frame_layout,mFrag,false);
        }
        else if(id==R.id.card_layout_4)
        {
            ProfileFragment mFrag=new ProfileFragment();
            mHelper.replaceFragment(R.id.frame_layout,mFrag,false);
        }
    }

}
