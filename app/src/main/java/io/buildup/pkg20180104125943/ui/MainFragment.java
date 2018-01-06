
package io.buildup.pkg20180104125943.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import io.buildup.pkg20180104125943.R;
import buildup.ds.Datasource;
import android.widget.TextView;
import buildup.actions.NavigateToAction;
import buildup.util.ColorUtils;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20180104125943.ds.ProductsDSItem;
import io.buildup.pkg20180104125943.ds.ProductsDS;

public class MainFragment extends buildup.ui.DetailFragment<ProductsDSItem>  {

    private Datasource<ProductsDSItem> datasource;
    private SearchOptions searchOptions;

    public static MainFragment newInstance(Bundle args){
        MainFragment card = new MainFragment();
        card.setArguments(args);

        return card;
    }

    public MainFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchOptions = SearchOptions.Builder.searchOptions().build();
    }

    @Override
    public Datasource getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = ProductsDS.getInstance(searchOptions);
        return datasource;
    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.main_custom;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ProductsDSItem item, View view) {
        
        TextView view0 = (TextView) view.findViewById(R.id.view0);
        view0.setText("Are you a traveller");
        
        
        TextView view1 = (TextView) view.findViewById(R.id.view1);
        view1.setText("Do you like to travel alot?");
        
        
        TextView view2 = (TextView) view.findViewById(R.id.view2);
        view2.setText("You have just come to a perfect place for your tours selections");
        
        
        TextView view3 = (TextView) view.findViewById(R.id.view3);
        view3.setText("Would you like to go on a adveture ? ");
        ColorUtils.tintIcon(view3.getCompoundDrawables()[2], R.color.textColor, getActivity());
        bindAction(view3, new NavigateToAction(ProductsDSItem.class.getName(), AdventureTourismActivity.class, getItem()));
        
        TextView view4 = (TextView) view.findViewById(R.id.view4);
        view4.setText("Would you like to go on tours ?");
        ColorUtils.tintIcon(view4.getCompoundDrawables()[2], R.color.textColor, getActivity());
        bindAction(view4, new NavigateToAction(ProductsDSItem.class.getName(), TilichotrekActivity.class, getItem()));
    }

    @Override
    protected void onShow(ProductsDSItem item) {
    }
}
