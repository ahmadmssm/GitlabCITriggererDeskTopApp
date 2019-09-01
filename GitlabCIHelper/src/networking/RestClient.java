package networking;

import common.Constants;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import models.Project;
import networking.configurations.AppRetrofitConfigurator;

import java.util.List;


public class RestClient {

    private AppRetrofitConfigurator retrofitConfigurator;

    @SuppressWarnings("WeakerAccess")
    public RestClient() {
        retrofitConfigurator = new AppRetrofitConfigurator();
        retrofitConfigurator.buildRetrofit();
    }

    @SuppressWarnings("WeakerAccess")
    public final RestEndPoints getEndpoints() {
        return retrofitConfigurator.getRetrofitClient(RestEndPoints.class);
    }


    private io.reactivex.Scheduler getRxBGThread() {
        return Schedulers.newThread();
    }

    private io.reactivex.Scheduler getRxUIThread() {
        return JavaFxScheduler.platform();
    }

    public final Single<List<Project>> getMyProjects(int page) {
        return getEndpoints()
                .getMyGitlabProjects(page)
                .subscribeOn(getRxBGThread())
                .observeOn(getRxUIThread());
    }

    public final Completable setEnvironmentVariable(long projectId, String environmentVariable, String value) {
        return getEndpoints()
                .setGitlabEnvironmentVariable(projectId, environmentVariable, value)
                .subscribeOn(getRxBGThread())
                .observeOn(getRxUIThread());
    }

    public final Completable setGitlabEmail(long projectId, String gitlabEmail) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.GIT_EMAIL, gitlabEmail);
    }

    public final Completable setGitlabUserName(long projectId, String gitlabName) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.GIT_USER_NAME, gitlabName);
    }

    public final Completable setGitlabAccessToken(long projectId, String gitlabToken) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.GITLAB_ACCESS_TOKEN, gitlabToken);
    }

    public final Completable setDebugMode(long projectId) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.PACKAGE_FOR, Constants.APK_MODE.DEBUG);
    }

    public final Completable packageDebugVersion(long projectId) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.RELEASE_MODE, "-");
    }

    public final Completable setInternalReleaseMode(long projectId) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.PACKAGE_FOR, Constants.APK_MODE.INTERNAL_RELEASE);
    }

    public final Completable packageInternalMajorReleaseVersion(long projectId) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.RELEASE_MODE, Constants.RELEASE_MODE.INTERNAL_MAJOR);
    }

    public final Completable packageInternalMinorReleaseVersion(long projectId) {
        return setEnvironmentVariable(projectId,  Constants.GITLAB_ENV_VARS.RELEASE_MODE, Constants.RELEASE_MODE.INTERNAL_MINOR);
    }

    public final Completable packageInternalFixReleaseVersion(long projectId) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.RELEASE_MODE, Constants.RELEASE_MODE.INTERNAL_FIX);
    }
    //
    public final Completable setCustomerReleaseMode(long projectId) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.PACKAGE_FOR, Constants.APK_MODE.PRODUCTION_RELEASE);
    }

    public final Completable packageCustomerMajorReleaseVersion(long projectId) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.RELEASE_MODE, Constants.RELEASE_MODE.CUSTOMER_MINOR);
    }

    public final Completable packageCustomerMinorReleaseVersion(long projectId) {
        return setEnvironmentVariable(projectId,  Constants.GITLAB_ENV_VARS.RELEASE_MODE, Constants.RELEASE_MODE.CUSTOMER_MINOR);
    }

    public final Completable packageCustomerFixReleaseVersion(long projectId) {
        return setEnvironmentVariable(projectId, Constants.GITLAB_ENV_VARS.RELEASE_MODE, Constants.RELEASE_MODE.CUSTOMER_FIX);
    }
}
