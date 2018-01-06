package io.buildup.pkg20180104125943.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import buildup.ds.Datasource;
import buildup.ui.ListGridFragment;
import buildup.util.image.ImageLoader;
import buildup.util.image.PicassoImageLoader;
import buildup.util.ViewHolder;
import io.buildup.pkg20180104125943.R;
import static buildup.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20180104125943.ds.Item;
import io.buildup.pkg20180104125943.ds.EmptyDatasource;
import static buildup.util.NavigationUtils.generateIntentToDetailOrForm;

/**
 * "JungleSafariFragment" listing
 */
public class JungleSafariFragment extends ListGridFragment<Item>  {

    private Datasource<Item> datasource;


    public static JungleSafariFragment newInstance(Bundle args) {
        JungleSafariFragment fr = new JungleSafariFragment();

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
        return R.layout.junglesafari_item;
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
        
        ImageLoader imageLoader = new PicassoImageLoader(view.getContext());
        ImageView image = ViewHolder.get(view, R.id.image);
        imageLoader.load(imageLoaderRequest()
                        .withResourceToLoad(R.drawable.png_defaultmenuicon)
                        .withTargetView(image)
                        .fit()
                        .build()
        );
        
        
    }


    @Override
    public void showDetail(Item item, int position) {
    }

}
