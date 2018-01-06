package io.buildup.pkg20180104125943.ui;

import android.os.Bundle;
import android.view.View;
import buildup.ds.Datasource;
import buildup.ui.ListGridFragment;
import buildup.util.ViewHolder;
import io.buildup.pkg20180104125943.R;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20180104125943.ds.Item;
import io.buildup.pkg20180104125943.ds.EmptyDatasource;
import static buildup.util.NavigationUtils.generateIntentToDetailOrForm;

/**
 * "UltalightFragment" listing
 */
public class UltalightFragment extends ListGridFragment<Item>  {

    private Datasource<Item> datasource;


    public static UltalightFragment newInstance(Bundle args) {
        UltalightFragment fr = new UltalightFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    protected SearchOptions getSearchOptions() {
        SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
        return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.ultalight_item;
    }

    @Override
    protected Datasource<Item> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = EmptyDatasource.getInstance(getSearchOptions());
        return datasource;
    }

    @Override
    protected void bindView(Item item, View view, int position) {
    }


    @Override
    public void showDetail(Item item, int position) {
    }

}
