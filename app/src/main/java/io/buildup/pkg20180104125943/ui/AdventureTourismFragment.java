

package io.buildup.pkg20180104125943.ui;

import android.os.Bundle;

import io.buildup.pkg20180104125943.R;

import java.util.ArrayList;
import java.util.List;

import buildup.MenuItem;

import buildup.actions.StartActivityAction;
import buildup.util.Constants;
import io.buildup.pkg20180104125943.ds.ProductsDSItem;

/**
 * AdventureTourismFragment menu fragment.
 */
public class AdventureTourismFragment extends buildup.ui.MenuFragment {
    private ProductsDSItem productsDSItem;
    /**
     * Default constructor
     */
    public AdventureTourismFragment(){
        super();
    }

    // Factory method
    public static AdventureTourismFragment newInstance(Bundle args) {
        AdventureTourismFragment fragment = new AdventureTourismFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        productsDSItem = getArguments() != null && getArguments().containsKey(ProductsDSItem.class.getName()) ? 
        	(ProductsDSItem)getArguments().getParcelable(ProductsDSItem.class.getName()) : 
        	new ProductsDSItem();
    }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("Bungee")
            .setIcon(R.drawable.png_bungeejumping277)
            .setAction(new StartActivityAction(BungeeMenuItem1Activity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Paragliding")
            .setIcon(R.drawable.png_paraglider105)
            .setAction(new StartActivityAction(ParaglidingMenuItem1Activity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Rafting")
            .setIcon(R.drawable.png_wildwaterrafting569)
            .setAction(new StartActivityAction(RaftingMenuItem1Activity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Mountain Biking")
            .setIcon(R.drawable.png_mountsin474)
            .setAction(new StartActivityAction(MountainBikingActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Ultalight")
            .setIcon(R.drawable.png_ultralight383)
            .setAction(new StartActivityAction(UltalightActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Zip lining")
            .setIcon(R.drawable.png_footerroper273)
            .setAction(new StartActivityAction(ZipliningActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Rock Climbing")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(RockClimbingActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Canyoning")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(CanyoningActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Jungle Safari")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(JungleSafariActivity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_grid;
    }

    @Override
    public int getItemLayout() {
        return R.layout.adventuretourism_item;
    }
}
