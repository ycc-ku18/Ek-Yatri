
package io.buildup.pkg20180104125943.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import buildup.ui.TabActivity;
import buildup.adapters.TabItem;

import buildup.actions.StartActivityAction;
import buildup.util.Constants;
import io.buildup.pkg20180104125943.R;

import java.util.ArrayList;
import java.util.List;

public class EkYatriMainActivity extends TabActivity {

    private final List<TabItem> tabItems = new ArrayList<>(); {
            tabItems.add(new TabItem(R.string.mainmenu_entry0, R.drawable.png_defaultmenuicon, MainFragment.class));
            tabItems.add(new TabItem(R.string.mainmenu_entry1, R.drawable.png_backpacker33063960720785, AdventureTourismFragment.class));
            tabItems.add(new TabItem(R.string.mainmenu_entry2, R.drawable.png_paraglider450, ContactFragment.class));
            tabItems.add(new TabItem(R.string.mainmenu_entry3, R.drawable.png_defaultmenuicon, IteniaryInformationFragment.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.app_name));
    }

    @Override
    public List<TabItem> getTabItems() {
        return tabItems;
    }

}
