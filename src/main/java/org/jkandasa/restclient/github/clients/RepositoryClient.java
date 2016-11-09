package org.jkandasa.restclient.github.clients;

import java.util.List;

import org.jkandasa.restclient.core.ClientResponse;
import org.jkandasa.restclient.core.jaxrs.Empty;
import org.jkandasa.restclient.github.model.Repository;

public interface RepositoryClient {
    ClientResponse<Repository> createUserRepository(Repository repository);

    ClientResponse<Repository> createOrgRepository(String organization, Repository repository);

    ClientResponse<Empty> delete(String owner, String repository);

    ClientResponse<Repository> get(String owner, String repository);

    ClientResponse<List<Repository>> listSelfRepo();

    ClientResponse<List<Repository>> listOrginazationRepo(String organization);

    ClientResponse<List<Repository>> listUserRepo(String username);

}
