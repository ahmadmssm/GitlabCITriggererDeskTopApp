package models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class GitLabProfile {

    private String gitLabName;
    private String gitLabEmail;
    private String gitLabAccessToken;


    public String getGitLabName() {
        return gitLabName;
    }

    public void setGitLabName(String gitLabName) {
        this.gitLabName = gitLabName;
    }

    public String getGitLabEmail() {
        return gitLabEmail;
    }

    public void setGitLabEmail(String gitLabEmail) {
        this.gitLabEmail = gitLabEmail;
    }

    public String getGitLabAccessToken() {
        return gitLabAccessToken;
    }

    public void setGitLabAccessToken(String gitLabAccessToken) {
        this.gitLabAccessToken = gitLabAccessToken;
    }
}
