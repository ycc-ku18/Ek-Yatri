package io.buildup.pkg20180104125943.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import buildup.behaviors.FabBehaviour;
import buildup.behaviors.SearchBehavior;
import buildup.behaviors.SelectionBehavior;
import buildup.ds.Datasource;
import buildup.ds.restds.AppNowDatasource;
import buildup.ui.ListGridFragment;
import buildup.util.ColorUtils;
import buildup.util.Constants;
import buildup.util.image.ImageLoader;
import buildup.util.image.PicassoImageLoader;
import buildup.util.StringUtils;
import buildup.util.ViewHolder;
import io.buildup.pkg20180104125943.R;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static buildup.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20180104125943.ds.ProductsDSItem;
import io.buildup.pkg20180104125943.ds.ProductsDS;
import buildup.dialogs.DeleteItemDialog;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import buildup.util.Constants;
import static buildup.util.NavigationUtils.generateIntentToDetailOrForm;

/**
 * "BungeeMenuItem1Fragment" listing
 */
public class BungeeMenuItem1Fragment extends ListGridFragment<ProductsDSItem> implements DeleteItemDialog.DeleteItemListener {

    private Datasource<ProductsDSItem> datasource;
    private List<ProductsDSItem> selectedItemsForDelete;

    
    ArrayList<String> price_values;
    
    ArrayList<String> rating_values;

    public static BungeeMenuItem1Fragment newInstance(Bundle args) {
        BungeeMenuItem1Fragment fr = new BungeeMenuItem1Fragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBehavior(new SearchBehavior(this));
        // Multiple selection
        SelectionBehavior<ProductsDSItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<ProductsDSItem>() {
            @Override
            public void onSelected(List<ProductsDSItem> selectedItems) {
                selectedItemsForDelete = selectedItems;
                DialogFragment deleteDialog = new DeleteItemDialog(BungeeMenuItem1Fragment.this);
                deleteDialog.show(getActivity().getSupportFragmentManager(), "");
            }
        });
        addBehavior(selectionBehavior);

        
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
        return R.layout.bungeemenuitem1_item;
    }

    @Override
    protected Datasource<ProductsDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = ProductsDS.getInstance(getSearchOptions());
        return datasource;
    }

    @Override
    protected void bindView(ProductsDSItem item, View view, int position) {
        
        ImageLoader imageLoader = new PicassoImageLoader(view.getContext(), false);
        ImageView image = ViewHolder.get(view, R.id.image);
        URL imageMedia = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(imageMedia != null){
            imageLoader.load(imageLoaderRequest()
                          .withPath(imageMedia.toExternalForm())
                          .withTargetView(image)
                          .fit()
        				  .build()
            );
        	
        }
        else {
            imageLoader.load(imageLoaderRequest()
                          .withResourceToLoad(R.drawable.ic_ibm_placeholder)
                          .withTargetView(image)
        				  .build()
            );
        }
        
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        title.setText((item.name != null ? item.name : ""));
        
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        subtitle.setText((item.dataField0 != null ? item.dataField0 : ""));
        
    }


    @Override
    public void showDetail(ProductsDSItem item, int position) {
        Intent intent = generateIntentToDetailOrForm(item,
            position,
            getActivity(),
            BungeeMenuItem1DetailActivity.class);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // inflate menu options and tint icon
        inflater.inflate(R.menu.filter_menu, menu);
        ColorUtils.tintIcon(menu.findItem(R.id.filter),
                            R.color.textBarColor,
                            getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.filter){
            Intent intent = new Intent(getActivity(), BungeeMenuItem1FilterActivity.class);

            // pass current values to filter activity
            Bundle args = new Bundle();
            args.putParcelable("search_options", getSearchOptions());
            
            intent.putStringArrayListExtra("price_values", price_values);
            
            intent.putStringArrayListExtra("rating_values", rating_values);
			intent.putExtras(args);
            // launch filter screen
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            // store the incoming selection
                        
            price_values = data.getStringArrayListExtra("price_values");
            
            rating_values = data.getStringArrayListExtra("rating_values");
            // apply filter to datasource
            clearFilters();

                        
            if(price_values != null && price_values.size() > 0)
                addStringFilter("price", price_values);
            
            if(rating_values != null && rating_values.size() > 0)
                addStringFilter("rating", rating_values);
            // and finally refresh the list
            refresh();

            // and redraw menu (to refresh tinted icons, like the search icon)
            getActivity().invalidateOptionsMenu();
        }
    }

    @Override
    public void deleteItem(boolean isDeleted) {
        if (isDeleted) {
            getPresenter().deleteItems(selectedItemsForDelete);
        }

        selectedItemsForDelete.clear();
    }

}
