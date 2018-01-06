package io.buildup.pkg20180104125943.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import buildup.behaviors.FabBehaviour;
import buildup.behaviors.SelectionBehavior;
import buildup.ds.Datasource;
import buildup.ds.restds.AppNowDatasource;
import buildup.ui.ListGridFragment;
import buildup.util.Constants;
import buildup.util.image.ImageLoader;
import buildup.util.image.PicassoImageLoader;
import buildup.util.StringUtils;
import buildup.util.ViewHolder;
import io.buildup.pkg20180104125943.ds.ProductsDSItem;
import io.buildup.pkg20180104125943.ds.ProductsDSService;
import io.buildup.pkg20180104125943.presenters.TilichotrekPresenter;
import io.buildup.pkg20180104125943.R;
import java.net.URL;
import java.util.List;
import static buildup.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20180104125943.ds.ProductsDSItem;
import io.buildup.pkg20180104125943.ds.ProductsDS;
import android.os.Parcelable;
import buildup.mvp.view.CrudListView;
import buildup.ds.CrudDatasource;
import buildup.dialogs.DeleteItemDialog;
import android.support.v4.app.DialogFragment;
import static buildup.util.NavigationUtils.generateIntentToDetailOrForm;

/**
 * "TilichotrekFragment" listing
 */
public class TilichotrekFragment extends ListGridFragment<ProductsDSItem> implements CrudListView<ProductsDSItem>, DeleteItemDialog.DeleteItemListener {

    private CrudDatasource<ProductsDSItem> datasource;
    private List<ProductsDSItem> selectedItemsForDelete;
    private ProductsDSItem productsDSItem;

    // "Add" button
    private FabBehaviour fabBehavior;

    public static TilichotrekFragment newInstance(Bundle args) {
        TilichotrekFragment fr = new TilichotrekFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        productsDSItem = getArguments() != null && getArguments().containsKey(ProductsDSItem.class.getName()) ? 
        	(ProductsDSItem)getArguments().getParcelable(ProductsDSItem.class.getName()) : 
        	new ProductsDSItem();
        setPresenter(new TilichotrekPresenter(
            (CrudDatasource) getDatasource(),
            this
        ));
        // Multiple selection
        SelectionBehavior<ProductsDSItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<ProductsDSItem>() {
            @Override
            public void onSelected(List<ProductsDSItem> selectedItems) {
                selectedItemsForDelete = selectedItems;
                DialogFragment deleteDialog = new DeleteItemDialog(TilichotrekFragment.this);
                deleteDialog.show(getActivity().getSupportFragmentManager(), "");
            }
        });
        addBehavior(selectionBehavior);

        // FAB button
        fabBehavior = new FabBehaviour(this, R.drawable.ic_add_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().addForm();
            }
        });
        addBehavior(fabBehavior);
        
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
        return R.layout.tilichotrek_item;
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
    protected void itemClicked(final ProductsDSItem item, final int position) {
        fabBehavior.hide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().detail(item, position);
            }
        });
    }

    @Override
    public void showDetail(ProductsDSItem item, int position) {
    }

    @Override
    public void showAdd() {
        startActivityForResult(
                generateIntentToDetailOrForm(null,
                        0,
                        getActivity(),
                        TilichotrekFormFormActivity.class,
                        new Parcelable[]{productsDSItem}
                ), Constants.MODE_CREATE
        );
    }

    @Override
    public void showEdit(ProductsDSItem item, int position) {
        startActivityForResult(
                generateIntentToDetailOrForm(item,
                        position,
                        getActivity(),
                        TilichotrekFormFormActivity.class,
                        new Parcelable[]{productsDSItem}
                ), Constants.MODE_EDIT
        );
    }

    @Override
    public void deleteItem(boolean isDeleted) {
        if (isDeleted) {
            getPresenter().deleteItems(selectedItemsForDelete);
        }

        selectedItemsForDelete.clear();
    }

}
