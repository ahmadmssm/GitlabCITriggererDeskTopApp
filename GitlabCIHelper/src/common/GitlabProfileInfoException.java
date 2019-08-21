package common;

public class GitlabProfileInfoException extends Exception {
    public String getMessage() {
        return "Invalid or empty Gitlab profile info !, please add your Gitlab name, email and access token to the gitlab_configs.json configuration file";
    }
}
