package common;

public interface Constants {

    @interface GITLAB_ENV_VARS {
        String GITLAB_ACCESS_TOKEN = "GIT_ACCESS_TOKEN";
        String DO_NOTHING = "GIT_ACCESS_TOKEN";
        String GIT_EMAIL = "GIT_EMAIL";
        String GIT_USER_NAME = "GIT_USER_NAME";
        String PACKAGE_FOR = "PACKAGE_FOR";
        String RELEASE_MODE = "RELEASE_MODE";
    }

    @interface APK_MODE {
        String DEBUG = "DEBUG";
        String RELEASE = "RELEASE";
    }

    @interface RELEASE_MODE {
        String MAJOR = "MAJOR";
        String MINOR = "MINOR";
        String FIX = "FIX";
    }
}
