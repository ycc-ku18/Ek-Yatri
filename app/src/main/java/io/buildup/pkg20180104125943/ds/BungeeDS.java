

package io.buildup.pkg20180104125943.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import buildup.ds.SearchOptions;
import buildup.ds.restds.AppNowDatasource;
import buildup.util.StringUtils;
import buildup.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "BungeeDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class BungeeDS extends AppNowDatasource<BungeeDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private BungeeDSService service;

    public static BungeeDS getInstance(SearchOptions searchOptions){
        return new BungeeDS(searchOptions);
    }

    private BungeeDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = BungeeDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<BungeeDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<BungeeDSItem>>() {
                @Override
                public void onSuccess(List<BungeeDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new BungeeDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getBungeeDSItemById(id, new Callback<BungeeDSItem>() {
                @Override
                public void success(BungeeDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<BungeeDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<BungeeDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryBungeeDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<BungeeDSItem>>() {
            @Override
            public void success(List<BungeeDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"text1", "text2", "text3"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(BungeeDSItem item, Listener<BungeeDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createBungeeDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createBungeeDSItem(item, callbackFor(listener));
        
    }

    private Callback<BungeeDSItem> callbackFor(final Listener<BungeeDSItem> listener) {
      return new Callback<BungeeDSItem>() {
          @Override
          public void success(BungeeDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(BungeeDSItem item, Listener<BungeeDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateBungeeDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateBungeeDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(BungeeDSItem item, final Listener<BungeeDSItem> listener) {
                service.getServiceProxy().deleteBungeeDSItemById(item.getIdentifiableId(), new Callback<BungeeDSItem>() {
            @Override
            public void success(BungeeDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<BungeeDSItem> items, final Listener<BungeeDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<BungeeDSItem>>() {
            @Override
            public void success(List<BungeeDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<BungeeDSItem> items){
        List<String> ids = new ArrayList<>();
        for(BungeeDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}
