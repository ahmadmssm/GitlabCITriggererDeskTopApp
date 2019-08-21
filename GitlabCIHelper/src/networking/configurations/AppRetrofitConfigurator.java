package networking.configurations;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;


public class AppRetrofitConfigurator extends RetrofitConfigurator {

    @Override
    protected int getDefaultTimeOut() {
        return 200;
    }

    @Override
    protected String getBaseURL() {
        return "https://gitlab.com/api/v4/projects/";
    }

    @Override
    protected Converter.Factory getConverterFactory() {
        return GsonConverterFactory.create(new GsonUtils().getCustomGsonConverter());
    }

    @Override
    protected OkHttpClient.Builder getOkHttpBuilder() {
        return super
                .getOkHttpBuilder()
                .addInterceptor(getRequestInterceptor());
    }

    private Interceptor getRequestInterceptor() {
        return chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("PRIVATE-TOKEN", getGitlabAuthToken())
                    .method(original.method(),original.body())
                    .build();
            return chain.proceed(request);
        };
    }


    private String getGitlabAuthToken() {
        try {
            return FileUtils.readGitLabProfile().getGitLabAccessToken();
        }
        catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
