package com.uog.smartlibraryfyp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity implements FragmentHelper {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void addFragment(int Layout_id, Fragment mFragment, boolean mClearBackStack) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        mTransaction.add(Layout_id, mFragment);
        if (mClearBackStack) {
            mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        mTransaction.commit();
    }

    @Override
    public void replaceFragment(int Layout_id, Fragment mFragment, boolean mClearBackStack) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(Layout_id, mFragment);
        mTransaction.addToBackStack(null);
        if (mClearBackStack) {
            mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        mTransaction.commit();
    }

}
