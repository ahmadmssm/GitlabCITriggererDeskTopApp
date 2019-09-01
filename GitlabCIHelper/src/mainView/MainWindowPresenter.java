package mainView;

import common.GitlabProfileInfoException;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import utils.GitlabEnvVariablesConfigurator;
import models.Project;
import networking.RestClient;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public class MainWindowPresenter {

    private IMainWindowView view;
    private RestClient restClient;
    private GitlabEnvVariablesConfigurator gitlabEnvVariablesConfigurator;
    private int pageNumber;
    private boolean noMoreItemsToLoad = false;


    MainWindowPresenter(IMainWindowView view) {
        this.view = view;
        restClient = new RestClient();
        resetAndReloadProjects();
    }

    void resetAndReloadProjects() {
        try {
            gitlabEnvVariablesConfigurator = new GitlabEnvVariablesConfigurator(restClient);
            pageNumber = 0;
            noMoreItemsToLoad = false;
            //
            getMyGitlabProjects();
        }
        catch (FileNotFoundException | URISyntaxException | GitlabProfileInfoException e) {
            e.printStackTrace();
            view.onFailure(e.getMessage());
        }
    }

    void getMyGitlabProjects() {
        if (!noMoreItemsToLoad) {
            pageNumber ++;
            restClient
                    .getMyProjects(pageNumber)
                    .subscribe(new SingleObserver<List<Project>>() {
                        @Override
                        public void onSubscribe(Disposable disposable) {
                            view.showLoader();
                        }

                        @Override
                        public void onSuccess(List<Project> projects) {
                            view.hideLoader();
                            view.onGitlabProjectsList(projects);
                            if (projects.size() < 100) noMoreItemsToLoad = true;
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            view.hideLoader();
                            view.onGitlabProjectsListFailure(throwable.getMessage());
                        }
                    });
        }
        else {
            view.onNoMoreProjectsToLoad();
        }
    }

    void configurePipelineForDebugMode(long projectId) {
        gitlabEnvVariablesConfigurator
                .setProfileInfoVars(projectId)
                .andThen(Completable.mergeArray(restClient.setDebugMode(projectId), restClient.packageDebugVersion(projectId)))
                .andThen(restClient.setDebugMode(projectId))
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        view.showLoader();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoader();
                        view.onSetPipelineMode();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.hideLoader();
                        view.onSetPipelineModeFailure(throwable.getMessage());
                    }
                });
    }

    void configurePipelineForInternalMajorReleaseMode(long projectId) {
        gitlabEnvVariablesConfigurator
                .setProfileInfoVars(projectId)
                .andThen(Completable.mergeArray(restClient.setInternalReleaseMode(projectId), restClient.packageInternalMajorReleaseVersion(projectId)))
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        view.showLoader();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoader();
                        view.onSetPipelineMode();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.hideLoader();
                        view.onSetPipelineModeFailure(throwable.getMessage());
                    }
                });
    }

    void configurePipelineForInternalMinorReleaseMode(long projectId) {
        gitlabEnvVariablesConfigurator
                .setProfileInfoVars(projectId)
                .andThen(Completable.mergeArray(restClient.setInternalReleaseMode(projectId), restClient.packageInternalMinorReleaseVersion(projectId)))
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        view.showLoader();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoader();
                        view.onSetPipelineMode();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.hideLoader();
                        view.onSetPipelineModeFailure(throwable.getMessage());
                    }
                });
    }

    void configurePipelineForInternalFixReleaseMode(long projectId) {
        gitlabEnvVariablesConfigurator
                .setProfileInfoVars(projectId)
                .andThen(Completable.mergeArray(restClient.setInternalReleaseMode(projectId), restClient.packageInternalFixReleaseVersion(projectId)))
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        view.showLoader();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoader();
                        view.onSetPipelineMode();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.hideLoader();
                        view.onSetPipelineModeFailure(throwable.getMessage());
                    }
                });
    }
    //
    void configurePipelineForCustomerMajorReleaseMode(long projectId) {
        gitlabEnvVariablesConfigurator
                .setProfileInfoVars(projectId)
                .andThen(Completable.mergeArray(restClient.setCustomerReleaseMode(projectId), restClient.packageCustomerMajorReleaseVersion(projectId)))
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        view.showLoader();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoader();
                        view.onSetPipelineMode();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.hideLoader();
                        view.onSetPipelineModeFailure(throwable.getMessage());
                    }
                });
    }

    void configurePipelineForCustomerMinorReleaseMode(long projectId) {
        gitlabEnvVariablesConfigurator
                .setProfileInfoVars(projectId)
                .andThen(Completable.mergeArray(restClient.setCustomerReleaseMode(projectId), restClient.packageCustomerMinorReleaseVersion(projectId)))
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        view.showLoader();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoader();
                        view.onSetPipelineMode();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.hideLoader();
                        view.onSetPipelineModeFailure(throwable.getMessage());
                    }
                });
    }

    void configurePipelineForCustomerFixReleaseMode(long projectId) {
        gitlabEnvVariablesConfigurator
                .setProfileInfoVars(projectId)
                .andThen(Completable.mergeArray(restClient.setCustomerReleaseMode(projectId), restClient.packageCustomerFixReleaseVersion(projectId)))
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        view.showLoader();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoader();
                        view.onSetPipelineMode();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.hideLoader();
                        view.onSetPipelineModeFailure(throwable.getMessage());
                    }
                });
    }
}
