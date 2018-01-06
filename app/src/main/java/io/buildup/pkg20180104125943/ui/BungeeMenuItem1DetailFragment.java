
package io.buildup.pkg20180104125943.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import buildup.behaviors.ShareBehavior;
import buildup.ds.restds.AppNowDatasource;
import buildup.util.image.ImageLoader;
import buildup.util.image.PicassoImageLoader;
import buildup.util.StringUtils;
import io.buildup.pkg20180104125943.R;
import java.net.URL;
import static buildup.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import buildup.ds.Datasource;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20180104125943.ds.ProductsDSItem;
import io.buildup.pkg20180104125943.ds.ProductsDS;

public class BungeeMenuItem1DetailFragment extends buildup.ui.DetailFragment<ProductsDSItem> implements ShareBehavior.ShareListener {

    private Datasource<ProductsDSItem> datasource;
    public static BungeeMenuItem1DetailFragment newInstance(Bundle args){
        BungeeMenuItem1DetailFragment fr = new BungeeMenuItem1DetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public BungeeMenuItem1DetailFragment(){
        super();
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
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.bungeemenuitem1detail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ProductsDSItem item, View view) {
        
        TextView view0 = (TextView) view.findViewById(R.id.view0);
        view0.setText((item.name != null ? item.name : ""));
        
        
        TextView view2 = (TextView) view.findViewById(R.id.view2);
        view2.setText((item.price != null ? item.price : ""));
        
        
        TextView view3 = (TextView) view.findViewById(R.id.view3);
        view3.setText((item.rating != null ? item.rating : ""));
        
        
        ImageView view4 = (ImageView) view.findViewById(R.id.view4);
        URL view4Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view4Media != null){
            ImageLoader imageLoader = new PicassoImageLoader(view4.getContext(), false);
            imageLoader.load(imageLoaderRequest()
                                   .withPath(view4Media.toExternalForm())
                                   .withTargetView(view4)
                                   .fit()
                                   .build()
                    );
            
        } else {
            view4.setImageDrawable(null);
        }
        
        ImageView view5 = (ImageView) view.findViewById(R.id.view5);
        URL view5Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.thumbnail);
        if(view5Media != null){
            ImageLoader imageLoader = new PicassoImageLoader(view5.getContext(), false);
            imageLoader.load(imageLoaderRequest()
                                   .withPath(view5Media.toExternalForm())
                                   .withTargetView(view5)
                                   .fit()
                                   .build()
                    );
            
        } else {
            view5.setImageDrawable(null);
        }
    }

    @Override
    protected void onShow(ProductsDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }
    @Override
    public void onShare() {
        ProductsDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.name != null ? item.name : "") + "\n" +
                    (item.price != null ? item.price : "") + "\n" +
                    (item.rating != null ? item.rating : ""));

        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}
