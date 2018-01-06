

package io.buildup.pkg20180104125943.ui;

import android.os.Bundle;

import io.buildup.pkg20180104125943.R;

import java.util.ArrayList;
import java.util.List;

import buildup.MenuItem;

import buildup.actions.StartActivityAction;
import buildup.util.Constants;

/**
 * IteniaryInformationFragment menu fragment.
 */
public class IteniaryInformationFragment extends buildup.ui.MenuFragment {
    /**
     * Default constructor
     */
    public IteniaryInformationFragment(){
        super();
    }

    // Factory method
    public static IteniaryInformationFragment newInstance(Bundle args) {
        IteniaryInformationFragment fragment = new IteniaryInformationFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("tilicho trek")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(TilichotrekActivity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public int getItemLayout() {
        return R.layout.iteniaryinformation_item;
    }
}
