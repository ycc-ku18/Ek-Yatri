

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
 * "ToursDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class ToursDS extends AppNowDatasource<ToursDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private ToursDSService service;

    public static ToursDS getInstance(SearchOptions searchOptions){
        return new ToursDS(searchOptions);
    }

    private ToursDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = ToursDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<ToursDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<ToursDSItem>>() {
                @Override
                public void onSuccess(List<ToursDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new ToursDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getToursDSItemById(id, new Callback<ToursDSItem>() {
                @Override
                public void success(ToursDSItem result, Response response) {
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
    public void getItems(final Listener<List<ToursDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<ToursDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryToursDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<ToursDSItem>>() {
            @Override
            public void success(List<ToursDSItem> result, Response response) {
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
    public void create(ToursDSItem item, Listener<ToursDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createToursDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createToursDSItem(item, callbackFor(listener));
        
    }

    private Callback<ToursDSItem> callbackFor(final Listener<ToursDSItem> listener) {
      return new Callback<ToursDSItem>() {
          @Override
          public void success(ToursDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(ToursDSItem item, Listener<ToursDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateToursDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateToursDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(ToursDSItem item, final Listener<ToursDSItem> listener) {
                service.getServiceProxy().deleteToursDSItemById(item.getIdentifiableId(), new Callback<ToursDSItem>() {
            @Override
            public void success(ToursDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<ToursDSItem> items, final Listener<ToursDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<ToursDSItem>>() {
            @Override
            public void success(List<ToursDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<ToursDSItem> items){
        List<String> ids = new ArrayList<>();
        for(ToursDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}
