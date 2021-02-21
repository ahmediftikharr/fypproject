package com.uog.smartlibraryfyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        loadMenuFragment();
    }

    private void loadMenuFragment()
    {
        MenuFragment mFrag=new MenuFragment();
        replaceFragment(R.id.frame_layout,mFrag,true);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.nav_menu)
        {
            MenuFragment mFrag=new MenuFragment();
            replaceFragment(R.id.frame_layout,mFrag,true);
        }
        else if(id==R.id.nav_my_profile)
        {
            ProfileFragment mFrag=new ProfileFragment();
            replaceFragment(R.id.frame_layout,mFrag,true);
        }
        else if(id==R.id.nav_my_books)
        {
            CurrentIssueBooksFragment mFrag=new CurrentIssueBooksFragment();
            replaceFragment(R.id.frame_layout,mFrag,true);
        }
        else if(id==R.id.nav_issued_books)
        {
            IssuedBooksFragment mFrag=new IssuedBooksFragment();
            replaceFragment(R.id.frame_layout,mFrag,true);
        }
        else if(id==R.id.nav_search)
        {
            SearchBookFragment mFrag=new SearchBookFragment();
            replaceFragment(R.id.frame_layout,mFrag,true);
        }
        else if(id==R.id.nav_share)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=com.thunkable.android.one4zawar.University_of_Gujrat_UOG");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else if(id==R.id.nav_logout)
        {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("smart_library", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("remember","no");
            editor.commit();


            Intent mIntent=new Intent(MenuActivity.this,LoginActivity.class);
            startActivity(mIntent);
            this.finish();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
