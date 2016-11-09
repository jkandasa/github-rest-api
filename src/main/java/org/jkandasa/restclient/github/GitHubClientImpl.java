package org.jkandasa.restclient.github;

import org.jkandasa.restclient.core.ClientInfo;
import org.jkandasa.restclient.github.clients.DefaultRepositoryClient;
import org.jkandasa.restclient.github.clients.DefaultUserClient;
import org.jkandasa.restclient.github.clients.RepositoryClient;
import org.jkandasa.restclient.github.clients.UserClient;

import static com.google.common.base.Preconditions.checkNotNull;

public class GitHubClientImpl implements GitHubClient {
    private final RepositoryClient repository;
    private final UserClient user;

    public GitHubClientImpl(ClientInfo clientInfo) {
        checkNotNull(clientInfo);
        repository = new DefaultRepositoryClient(clientInfo);
        user = new DefaultUserClient(clientInfo);
    }

    @Override
    public RepositoryClient repository() {
        return repository;
    }

    @Override
    public UserClient user() {
        return user;
    }

}
