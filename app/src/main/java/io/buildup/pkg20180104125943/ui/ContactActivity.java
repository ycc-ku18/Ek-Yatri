

package io.buildup.pkg20180104125943.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import buildup.ui.BaseDetailActivity;

/**
 * ContactActivity detail activity
 */
public class ContactActivity extends BaseDetailActivity {

  	@Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return ContactFragment.class;
    }
}

