package org.jkandasa.test;

import java.util.UUID;

import org.jkandasa.restclient.core.ClientResponse;
import org.jkandasa.restclient.core.jaxrs.Empty;
import org.jkandasa.restclient.github.model.Repository;
import org.jkandasa.restclient.github.model.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RepositoryTest extends BaseTest {

    private static Object[][] repos = null;

    private static final String REPO_NAME = "repo_" + UUID.randomUUID().toString();

    @Test
    public void testAuthentication() {
        ClientResponse<User> response = client.gitHub().user().selfInfo();
        Assert.assertTrue(response.isSuccess(), "Auth failed! " + response.getErrorMsg());
        Assert.assertNotNull(response.getEntity(), "Response is NULL! " + response.getErrorMsg());
    }

    @Test(dataProvider = "repo-list")
    public void testCreateRepo(Repository actual) {
        ClientResponse<Repository> response = client.gitHub().repository().createUserRepository(actual);
        Assert.assertTrue(response.isSuccess(), response.toString());
        Repository expected = response.getEntity();
        Assert.assertEquals(actual.getName(), expected.getName(), response.getEntity().getName());
        Assert.assertEquals(expected.getHasIssues(), getValue(actual.getHasIssues(), Boolean.TRUE));
        Assert.assertEquals(expected.getHasWiki(), getValue(actual.getHasWiki(), Boolean.TRUE));
        Assert.assertEquals(expected.getHasDownloads(), getValue(actual.getHasDownloads(), Boolean.TRUE));
    }

    @Test(dataProvider = "repo-list", dependsOnMethods = "testCreateRepo")
    public void testDeleteRepo(Repository actual) {
        ClientResponse<Empty> response = client.gitHub().repository()
                .delete(client.getClientInfo().getUsername().get(), actual.getName());
        Assert.assertTrue(response.isSuccess(), response.toString());
        ClientResponse<Repository> repoStatus = client.gitHub().repository()
                .get(client.getClientInfo().getUsername().get(), actual.getName());
        Assert.assertFalse(repoStatus.isSuccess(), repoStatus.getErrorMsg());
        Assert.assertTrue(repoStatus.getEntity() == null, "repository found! " + repoStatus);
    }

    @Test
    public void testCreateDuplicateEntry() {
        Repository repo = Repository.builder().name(REPO_NAME).build();
        createRepo(repo);
        ClientResponse<Repository> response = client.gitHub().repository().createUserRepository(repo);
        Assert.assertFalse(response.isSuccess(), response.toString());
        removeRepo(repo);
    }

    @Test
    public void testCreateEmptyEntry() {
        Repository repo = Repository.builder().build();
        ClientResponse<Repository> response = client.gitHub().repository().createUserRepository(repo);
        Assert.assertFalse(response.isSuccess(), response.toString());
    }

    private void createRepo(Repository repo) {
        if (!client.gitHub().repository().get(client.getClientInfo().getUsername().get(), repo.getName()).isSuccess()) {
            Assert.assertTrue(client.gitHub().repository().createUserRepository(repo).isSuccess());
        }
    }

    private void removeRepo(Repository repo) {
        Assert.assertTrue(
                client.gitHub().repository().delete(client.getClientInfo().getUsername().get(), repo.getName())
                        .isSuccess(), "Repo deletion failed! " + repo.getName());
    }

    private Boolean getValue(Boolean value, Boolean defaultValue) {
        return value == null ? defaultValue : value;
    }

    @DataProvider(name = "repo-list")
    public static Object[][] repositories() {
        if (repos == null) {
            repos = new Object[][] {
                    { Repository.builder().name("repo_" + UUID.randomUUID().toString()).build() },
                    { Repository.builder().name("repo_" + UUID.randomUUID().toString()).hasIssues(Boolean.FALSE)
                            .build() },
                    { Repository.builder().name("repo_" + UUID.randomUUID().toString()).hasIssues(Boolean.FALSE)
                            .hasPages(Boolean.FALSE).build() },
                    { Repository.builder().name("repo_" + UUID.randomUUID().toString()).hasIssues(Boolean.FALSE)
                            .hasWiki(Boolean.FALSE).hasDownloads(Boolean.FALSE).build() },
            };
        }
        return repos;
    }

}
