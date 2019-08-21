package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import models.GitLabProfile;
import mainView.MainWindowView;
import networking.configurations.GsonUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileUtils {

    public static GitLabProfile readGitLabProfile() throws FileNotFoundException, URISyntaxException {
        // FileReader fileReader = new FileReader("/Users/ams/Desktop/GitlabCIHelper/gitlab_configs.json");
        FileReader fileReader = new FileReader(getProjectParentPath() +"/gitlab_configs.json");
        JsonReader reader = new JsonReader(fileReader);
        Gson gson = new GsonUtils().getCustomGsonConverter();
        return gson.fromJson(reader, GitLabProfile.class);
    }

    @SuppressWarnings("WeakerAccess")
    public static String getProjectParentPath() throws URISyntaxException {
        URI uri = MainWindowView.class.getProtectionDomain().getCodeSource().getLocation().toURI();
        String path = new File(uri).getPath();
        String[] pathChunks = path.split("/");
        ArrayList pathChunksArrayList = new ArrayList<>(Arrays.asList(pathChunks));
        int lastPartIndex = pathChunks.length - 1;
        pathChunksArrayList.remove(lastPartIndex);
        return String.join("/", pathChunksArrayList);
    }
}
