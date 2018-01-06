
package io.buildup.pkg20180104125943.presenters;

import io.buildup.pkg20180104125943.R;
import io.buildup.pkg20180104125943.ds.ProductsDSItem;

import java.util.List;

import buildup.ds.CrudDatasource;
import buildup.ds.Datasource;
import buildup.mvp.presenter.BaseFormPresenter;
import buildup.mvp.view.FormView;

public class TilichotrekFormFormPresenter extends BaseFormPresenter<ProductsDSItem> {

    private final CrudDatasource<ProductsDSItem> datasource;

    public TilichotrekFormFormPresenter(CrudDatasource<ProductsDSItem> datasource, FormView<ProductsDSItem> view){
        super(view);
        this.datasource = datasource;
    }

    @Override
    public void deleteItem(ProductsDSItem item) {
        datasource.deleteItem(item, new OnItemDeletedListener());
    }

    @Override
    public void save(ProductsDSItem item) {
        // validate
        if (validate(item)){
            datasource.updateItem(item, new OnItemUpdatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    @Override
    public void create(ProductsDSItem item) {
        // validate
        if (validate(item)){
            datasource.create(item, new OnItemCreatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    private class OnItemDeletedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(ProductsDSItem  item) {
                        view.showMessage(R.string.item_deleted, true);
            view.close(true);
        }
    }

    private class OnItemUpdatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(ProductsDSItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_updated, true);
            view.close(true);
        }
    }

    private class OnItemCreatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(ProductsDSItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_created, true);
            view.close(true);
        }
    }

    private abstract class ShowingErrorOnFailureListener implements Datasource.Listener<ProductsDSItem > {
        @Override
        public void onFailure(Exception e) {
            view.showMessage(R.string.error_data_generic, true);
        }
    }

}
