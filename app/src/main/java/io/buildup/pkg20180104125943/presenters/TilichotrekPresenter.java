
package io.buildup.pkg20180104125943.presenters;

import io.buildup.pkg20180104125943.R;
import io.buildup.pkg20180104125943.ds.ProductsDSItem;

import java.util.List;

import buildup.ds.CrudDatasource;
import buildup.ds.Datasource;
import buildup.mvp.presenter.BasePresenter;
import buildup.mvp.presenter.ListCrudPresenter;
import buildup.mvp.view.CrudListView;

public class TilichotrekPresenter extends BasePresenter implements ListCrudPresenter<ProductsDSItem>,
      Datasource.Listener<ProductsDSItem>{

    private final CrudDatasource<ProductsDSItem> crudDatasource;
    private final CrudListView<ProductsDSItem> view;

    public TilichotrekPresenter(CrudDatasource<ProductsDSItem> crudDatasource,
                                         CrudListView<ProductsDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(ProductsDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<ProductsDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(ProductsDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(ProductsDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(ProductsDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}
