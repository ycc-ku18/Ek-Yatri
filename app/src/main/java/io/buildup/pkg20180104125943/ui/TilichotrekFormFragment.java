
package io.buildup.pkg20180104125943.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import buildup.ui.FormFragment;
import buildup.util.StringUtils;
import buildup.views.ImagePicker;
import buildup.views.TextWatcherAdapter;
import io.buildup.pkg20180104125943.ds.ProductsDSItem;
import io.buildup.pkg20180104125943.ds.ProductsDSService;
import io.buildup.pkg20180104125943.presenters.TilichotrekFormFormPresenter;
import io.buildup.pkg20180104125943.R;
import java.io.IOException;
import java.io.File;

import static android.net.Uri.fromFile;
import buildup.ds.Datasource;
import buildup.ds.CrudDatasource;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20180104125943.ds.ProductsDSItem;
import io.buildup.pkg20180104125943.ds.ProductsDS;

public class TilichotrekFormFragment extends FormFragment<ProductsDSItem> {

    private CrudDatasource<ProductsDSItem> datasource;
    private ProductsDSItem productsDSItem;
    public static TilichotrekFormFragment newInstance(Bundle args){
        TilichotrekFormFragment fr = new TilichotrekFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public TilichotrekFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new TilichotrekFormFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

        
        productsDSItem = getArguments() != null && getArguments().containsKey(ProductsDSItem.class.getName()) ? 
        	(ProductsDSItem)getArguments().getParcelable(ProductsDSItem.class.getName()) : 
        	new ProductsDSItem();
    }

    @Override
    protected ProductsDSItem newItem() {
        ProductsDSItem newItem = new ProductsDSItem();
        return newItem;
    }

    private ProductsDSService getRestService(){
        return ProductsDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.tilichotrekform_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ProductsDSItem item, View view) {
        
        bindString(R.id.productsds_name, item.name, false, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.name = s.toString();
            }
        });
        
        
        bindString(R.id.productsds_price, item.price, false, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.price = s.toString();
            }
        });
        
        
        bindString(R.id.productsds_rating, item.rating, false, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.rating = s.toString();
            }
        });
        
        
        bindImage(R.id.productsds_picture,
            item.picture != null ?
                getRestService().getImageUrl(item.picture) : null,
            false,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.picture = null;
                    item.pictureUri = null;
                    ((ImagePicker) getView().findViewById(R.id.productsds_picture)).clear();
                }
            }
        );
        
        
        bindImage(R.id.productsds_thumbnail,
            item.thumbnail != null ?
                getRestService().getImageUrl(item.thumbnail) : null,
            false,
            1,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.thumbnail = null;
                    item.thumbnailUri = null;
                    ((ImagePicker) getView().findViewById(R.id.productsds_thumbnail)).clear();
                }
            }
        );
        
    }

    @Override
    public Datasource<ProductsDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = ProductsDS.getInstance(new SearchOptions());
        return datasource;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            ProductsDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
                imageUri = data.getData();
                switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            picker = (ImagePicker) getView().findViewById(R.id.productsds_picture);
                            break;
                        
                        
                        case 1:   // thumbnail field
                            item.thumbnailUri = imageUri;
                            item.thumbnail = "cid:thumbnail";
                            picker = (ImagePicker) getView().findViewById(R.id.productsds_thumbnail);
                            break;
                        
                    default:
                        return;
                }

                picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
                switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            picker = (ImagePicker) getView().findViewById(R.id.productsds_picture);
                            imageUri = fromFile(picker.getImageFile());
                        		item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            break;
                        
                        
                        case 1:   // thumbnail field
                            picker = (ImagePicker) getView().findViewById(R.id.productsds_thumbnail);
                            imageUri = fromFile(picker.getImageFile());
                        		item.thumbnailUri = imageUri;
                            item.thumbnail = "cid:thumbnail";
                            break;
                        
                    default:
                        return;
                }
                picker.setImageUri(imageUri);
            }
        }
    }
}
