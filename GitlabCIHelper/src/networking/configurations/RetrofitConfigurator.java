package networking.configurations;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;
import retrofit2.Converter.Factory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


@SuppressWarnings({"WeakerAccess", "unused", "UnusedReturnValue"})
public abstract class RetrofitConfigurator {

    protected Retrofit retrofit;


    public RetrofitConfigurator() {}


    protected Builder getOkHttpBuilder() {
        int timeOut = getDefaultTimeOut();
        return new Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS);
    }

    protected abstract int getDefaultTimeOut();

    protected retrofit2.Retrofit.Builder getRetrofitBuilder() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(this.getBaseURL())
                .addConverterFactory(this.getConverterFactory())
                .client(this.getOkHttpBuilder().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public final RetrofitConfigurator buildRetrofit() {
        if (retrofit == null) retrofit = this.getRetrofitBuilder().build();
        return this;
    }

    public <APIsInterface> APIsInterface getRetrofitClient(Class<APIsInterface> restAPIsInterface) {
        return retrofit.create(restAPIsInterface);
    }

    protected abstract String getBaseURL();

    protected abstract Factory getConverterFactory();

}
