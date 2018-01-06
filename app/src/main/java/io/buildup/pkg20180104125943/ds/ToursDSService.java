
package io.buildup.pkg20180104125943.ds;
import java.net.URL;
import io.buildup.pkg20180104125943.R;
import buildup.ds.RestService;
import buildup.util.StringUtils;

/**
 * "ToursDSService" REST Service implementation
 */
public class ToursDSService extends RestService<ToursDSServiceRest>{

    public static ToursDSService getInstance(){
          return new ToursDSService();
    }

    private ToursDSService() {
        super(ToursDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://pods.hivepod.io";
    }

    @Override
    protected String getApiKey() {
        return "6p7F5WYq";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://pods.hivepod.io/app/5a4e253b78cda5040075be72",
                path,
                "apikey=6p7F5WYq");
    }

}
