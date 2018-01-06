
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

public class ParaglidingMenuItem1DetailFragment extends buildup.ui.DetailFragment<ProductsDSItem> implements ShareBehavior.ShareListener {

    private Datasource<ProductsDSItem> datasource;
    public static ParaglidingMenuItem1DetailFragment newInstance(Bundle args){
        ParaglidingMenuItem1DetailFragment fr = new ParaglidingMenuItem1DetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public ParaglidingMenuItem1DetailFragment(){
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
        return R.layout.paraglidingmenuitem1detail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ProductsDSItem item, View view) {
        
        ImageView view0 = (ImageView) view.findViewById(R.id.view0);
        URL view0Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view0Media != null){
            ImageLoader imageLoader = new PicassoImageLoader(view0.getContext(), false);
            imageLoader.load(imageLoaderRequest()
                                   .withPath(view0Media.toExternalForm())
                                   .withTargetView(view0)
                                   .fit()
                                   .build()
                    );
            
        } else {
            view0.setImageDrawable(null);
        }
        
        TextView view1 = (TextView) view.findViewById(R.id.view1);
        view1.setText(String.format("$%s %s", item.price, item.rating));
        
    }

    @Override
    protected void onShow(ProductsDSItem item) {
        // set the title for this fragment
        getActivity().setTitle((item.name != null ? item.name : ""));
    }
    @Override
    public void onShare() {
        ProductsDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, String.format("$%s %s", item.price, item.rating));
        intent.putExtra(Intent.EXTRA_SUBJECT, (item.name != null ? item.name : ""));

        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}
