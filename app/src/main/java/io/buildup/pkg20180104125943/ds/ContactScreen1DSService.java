
package io.buildup.pkg20180104125943.ds;
import java.net.URL;
import io.buildup.pkg20180104125943.R;
import buildup.ds.RestService;
import buildup.util.StringUtils;

/**
 * "ContactScreen1DSService" REST Service implementation
 */
public class ContactScreen1DSService extends RestService<ContactScreen1DSServiceRest>{

    public static ContactScreen1DSService getInstance(){
          return new ContactScreen1DSService();
    }

    private ContactScreen1DSService() {
        super(ContactScreen1DSServiceRest.class);

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
