

package io.buildup.pkg20180104125943.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import buildup.ui.BaseDetailActivity;

/**
 * RaftingMenuItem1DetailActivity detail activity
 */
public class RaftingMenuItem1DetailActivity extends BaseDetailActivity {

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
        return RaftingMenuItem1DetailFragment.class;
    }
}

