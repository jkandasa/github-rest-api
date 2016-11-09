package org.jkandasa.restclient.github;

import org.jkandasa.restclient.github.clients.RepositoryClient;
import org.jkandasa.restclient.github.clients.UserClient;

public interface GitHubClient {

    RepositoryClient repository();

    UserClient user();
}
