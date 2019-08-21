package utils;

import common.GitlabProfileInfoException;
import io.reactivex.Completable;
import models.GitLabProfile;
import networking.RestClient;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class GitlabEnvVariablesConfigurator {

    private GitLabProfile gitLabProfile;
    private RestClient restClient;


    @SuppressWarnings("unused")
    private GitlabEnvVariablesConfigurator() {}

    public GitlabEnvVariablesConfigurator(RestClient restClient) throws FileNotFoundException, URISyntaxException, GitlabProfileInfoException {
        this.restClient = restClient;
        gitLabProfile = FileUtils.readGitLabProfile();
        if (gitLabProfile.getGitLabName().isEmpty() ||
                gitLabProfile.getGitLabEmail().isEmpty() ||
                gitLabProfile.getGitLabAccessToken().isEmpty()) {
            throw new GitlabProfileInfoException();
        }
    }


    private Completable setGitlabUserName(long projectId) {
        return restClient.setGitlabUserName(projectId, gitLabProfile.getGitLabName());
    }

    private Completable setGitlabEmail(long projectId) {
        return restClient.setGitlabEmail(projectId, gitLabProfile.getGitLabEmail());
    }

    private Completable setGitlabAccessToken(long projectId) {
        return restClient.setGitlabAccessToken(projectId, gitLabProfile.getGitLabAccessToken());
    }

    public Completable setProfileInfoVars(long projectId) {
        return Completable.mergeArray(setGitlabUserName(projectId), setGitlabEmail(projectId), setGitlabAccessToken(projectId));
    }
}
