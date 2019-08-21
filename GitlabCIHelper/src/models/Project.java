
package models;

import java.util.List;

@SuppressWarnings("unused")
public class Project {

    private int id;
    private String description;
    private String name;
    private String nameWithNamespace;
    private String path;
    private String pathWithNamespace;
    private String createdAt;
    private String defaultBranch;
    private List<Object> tagList = null;
    private String sshUrlToRepo;
    private String httpUrlToRepo;
    private String webUrl;
    private Object readmeUrl;
    private Object avatarUrl;
    private int starCount;
    private int forksCount;
    private String lastActivityAt;
    private Namespace namespace;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameWithNamespace() {
        return nameWithNamespace;
    }

    public void setNameWithNamespace(String nameWithNamespace) {
        this.nameWithNamespace = nameWithNamespace;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathWithNamespace() {
        return pathWithNamespace;
    }

    public void setPathWithNamespace(String pathWithNamespace) {
        this.pathWithNamespace = pathWithNamespace;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public List<Object> getTagList() {
        return tagList;
    }

    public void setTagList(List<Object> tagList) {
        this.tagList = tagList;
    }

    public String getSshUrlToRepo() {
        return sshUrlToRepo;
    }

    public void setSshUrlToRepo(String sshUrlToRepo) {
        this.sshUrlToRepo = sshUrlToRepo;
    }

    public String getHttpUrlToRepo() {
        return httpUrlToRepo;
    }

    public void setHttpUrlToRepo(String httpUrlToRepo) {
        this.httpUrlToRepo = httpUrlToRepo;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Object getReadmeUrl() {
        return readmeUrl;
    }

    public void setReadmeUrl(Object readmeUrl) {
        this.readmeUrl = readmeUrl;
    }

    public Object getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(Object avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public String getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(String lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

}
