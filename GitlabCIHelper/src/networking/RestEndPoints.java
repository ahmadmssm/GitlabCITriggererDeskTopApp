package networking;

import io.reactivex.Completable;
import io.reactivex.Single;
import models.Project;
import retrofit2.http.*;

import java.util.List;

public interface RestEndPoints {
    @GET("?membership=true&per_page=100&simple=true")
    Single<List<Project>> getMyGitlabProjects(@Query("page") int pageNumber);

    @FormUrlEncoded
    @PUT("{project_id}/variables/{environment_variable}")
    Completable setGitlabEnvironmentVariable(@Path("project_id") long projectId,
                                             @Path("environment_variable") String environmentVariable,
                                             @Field("value") String value);
}
