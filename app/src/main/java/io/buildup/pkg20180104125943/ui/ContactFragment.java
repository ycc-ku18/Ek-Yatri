
package io.buildup.pkg20180104125943.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import io.buildup.pkg20180104125943.R;
import buildup.ds.Datasource;
import android.widget.ImageView;
import android.widget.TextView;
import buildup.actions.ActivityIntentLauncher;
import buildup.actions.MailAction;
import buildup.actions.MapsAction;
import buildup.actions.PhoneAction;
import buildup.ds.restds.AppNowDatasource;
import buildup.util.ColorUtils;
import buildup.util.image.ImageLoader;
import buildup.util.image.PicassoImageLoader;
import buildup.util.StringUtils;
import java.net.URL;
import static buildup.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20180104125943.ds.ContactScreen1DSItem;
import io.buildup.pkg20180104125943.ds.ContactScreen1DS;

public class ContactFragment extends buildup.ui.DetailFragment<ContactScreen1DSItem>  {

    private Datasource<ContactScreen1DSItem> datasource;
    private SearchOptions searchOptions;

    public static ContactFragment newInstance(Bundle args){
        ContactFragment card = new ContactFragment();
        card.setArguments(args);

        return card;
    }

    public ContactFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchOptions = SearchOptions.Builder.searchOptions().build();
    }

    @Override
    public Datasource getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = ContactScreen1DS.getInstance(searchOptions);
        return datasource;
    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.contact_custom;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ContactScreen1DSItem item, View view) {
        
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
        view1.setText((item.address != null ? item.address : ""));
        ColorUtils.tintIcon(view1.getCompoundDrawables()[2], R.color.textColor, getActivity());
        bindAction(view1, new MapsAction(new ActivityIntentLauncher(), (item.address != null ? item.address : "")));
        
        TextView view2 = (TextView) view.findViewById(R.id.view2);
        view2.setText((item.phone != null ? item.phone : ""));
        ColorUtils.tintIcon(view2.getCompoundDrawables()[2], R.color.textColor, getActivity());
        bindAction(view2, new PhoneAction(new ActivityIntentLauncher(), (item.phone != null ? item.phone : "")));
        
        TextView view3 = (TextView) view.findViewById(R.id.view3);
        view3.setText((item.email != null ? item.email : ""));
        ColorUtils.tintIcon(view3.getCompoundDrawables()[2], R.color.textColor, getActivity());
        bindAction(view3, new MailAction(new ActivityIntentLauncher(), (item.email != null ? item.email : "")));
    }

    @Override
    protected void onShow(ContactScreen1DSItem item) {
    }
}
