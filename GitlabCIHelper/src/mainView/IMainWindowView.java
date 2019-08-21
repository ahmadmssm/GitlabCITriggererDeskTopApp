package mainView;

import models.Project;

import java.util.List;

public interface IMainWindowView {
    void onGitlabProjectsList(List<Project> projectsList);
    void onGitlabProjectsListFailure(String error);
    void onSetPipelineMode();
    void onSetPipelineModeFailure(String error);
    void onFailure(String error);
    void showLoader();
    void hideLoader();
    void onNoMoreProjectsToLoad();
}
