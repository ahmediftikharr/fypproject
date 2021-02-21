package com.uog.smartlibraryfyp;

import android.support.v4.app.Fragment;

public interface FragmentHelper
{

    public void addFragment(int Layout_id, Fragment mFragment, boolean mClearBackStack);
    public void replaceFragment(int Layout_id, Fragment mFragment, boolean mClearBackStack);

}
