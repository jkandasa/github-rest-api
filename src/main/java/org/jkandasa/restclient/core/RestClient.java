package org.jkandasa.restclient.core;

import static com.google.common.base.Preconditions.checkNotNull;

import org.jkandasa.restclient.github.GitHubClient;
import org.jkandasa.restclient.github.GitHubClientImpl;

public class RestClient {

    static final String KEY_HEADER_AUTHORIZATION = "Authorization";

    private GitHubClient gitHubClient;
    private ClientInfo clientInfo;

    public RestClient(ClientInfo clientInfo) {
        checkNotNull(clientInfo);
        this.clientInfo = clientInfo;
        this.gitHubClient = new GitHubClientImpl(clientInfo);
    }

    public GitHubClient gitHub() {
        return gitHubClient;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        RestClient that = (RestClient) o;

        return clientInfo != null ? clientInfo.equals(that.clientInfo) : that.clientInfo == null;

    }

    
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }
    
    @Override
    public int hashCode() {
        return clientInfo != null ? clientInfo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CoreClient{" +
                "gitHubClient=" + gitHubClient +
                ", clientInfo=" + clientInfo +
                '}';
    }
}