

package io.buildup.pkg20180104125943.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.buildup.pkg20180104125943.R;

import buildup.ui.BaseListingActivity;
/**
 * TilichotrekActivity list activity
 */
public class TilichotrekActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        setTitle(getString(R.string.tilichotrekActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return TilichotrekFragment.class;
    }

}
