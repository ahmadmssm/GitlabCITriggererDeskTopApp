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
        String INTERNAL_RELEASE = "INTERNAL_RELEASE";
        String PRODUCTION_RELEASE = "PRODUCTION_RELEASE";
    }

    @interface RELEASE_MODE {
        String INTERNAL_MAJOR = "INTERNAL_MAJOR";
        String INTERNAL_MINOR = "INTERNAL_MINOR";
        String INTERNAL_FIX = "INTERNAL_FIX";
        String CUSTOMER_MAJOR = "CUSTOMER_MAJOR";
        String CUSTOMER_MINOR = "CUSTOMER_MINOR";
        String CUSTOMER_FIX = "CUSTOMER_FIX";
    }
}
