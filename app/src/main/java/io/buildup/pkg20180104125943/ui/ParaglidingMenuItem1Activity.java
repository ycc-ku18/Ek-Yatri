

package io.buildup.pkg20180104125943.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.buildup.pkg20180104125943.R;

import buildup.ui.BaseListingActivity;
/**
 * ParaglidingMenuItem1Activity list activity
 */
public class ParaglidingMenuItem1Activity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        setTitle(getString(R.string.paraglidingMenuItem1Activity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return ParaglidingMenuItem1Fragment.class;
    }

}
