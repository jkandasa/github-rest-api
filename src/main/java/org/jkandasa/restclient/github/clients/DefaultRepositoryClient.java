package org.jkandasa.restclient.github.clients;

import java.util.List;

import javax.ws.rs.core.Response;

import org.jkandasa.restclient.core.BaseClient;
import org.jkandasa.restclient.core.ClientInfo;
import org.jkandasa.restclient.core.ClientResponse;
import org.jkandasa.restclient.core.DefaultClientResponse;
import org.jkandasa.restclient.core.jaxrs.Empty;
import org.jkandasa.restclient.core.jaxrs.ResponseCodes;
import org.jkandasa.restclient.core.jaxrs.RestFactory;
import org.jkandasa.restclient.github.jaxrs.handlers.RepositoryHandler;
import org.jkandasa.restclient.github.model.Repository;

import com.fasterxml.jackson.databind.JavaType;

public class DefaultRepositoryClient extends BaseClient<RepositoryHandler> implements RepositoryClient {
    public DefaultRepositoryClient(ClientInfo clientInfo) {
        super(clientInfo, new RestFactory<>(RepositoryHandler.class));
    }

    @Override
    public ClientResponse<Repository> createUserRepository(Repository repository) {
        Response serverResponse = null;
        try {
            serverResponse = restApi().createUserRepository(repository);
            JavaType javaType = simpleResolver().get(Repository.class);

            return new DefaultClientResponse<>(javaType, serverResponse, ResponseCodes.CREATE_SUCCESS_201);
        } finally {
            if (serverResponse != null) {
                serverResponse.close();
            }
        }
    }

    @Override
    public ClientResponse<Repository> createOrgRepository(String organization, Repository repository) {
        Response serverResponse = null;
        try {
            serverResponse = restApi().createOrgRepository(organization, repository);
            JavaType javaType = simpleResolver().get(Repository.class);
            return new DefaultClientResponse<>(javaType, serverResponse, ResponseCodes.CREATE_SUCCESS_201);
        } finally {
            if (serverResponse != null) {
                serverResponse.close();
            }
        }
    }

    @Override
    public ClientResponse<Empty> delete(String owner, String repository) {
        Response serverResponse = null;
        try {
            serverResponse = restApi().delete(owner, repository);
            JavaType javaType = simpleResolver().get(Empty.class);
            return new DefaultClientResponse<>(javaType, serverResponse, ResponseCodes.NO_CONTENT_204);
        } finally {
            if (serverResponse != null) {
                serverResponse.close();
            }
        }
    }

    @Override
    public ClientResponse<Repository> get(String owner, String repository) {
        Response serverResponse = null;
        try {
            serverResponse = restApi().get(owner, repository);
            JavaType javaType = simpleResolver().get(Repository.class);
            return new DefaultClientResponse<>(javaType, serverResponse, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (serverResponse != null) {
                serverResponse.close();
            }
        }
    }

    @Override
    public ClientResponse<List<Repository>> listSelfRepo() {
        Response serverResponse = null;
        try {
            serverResponse = restApi().listSelfRepo();
            JavaType javaType = collectionResolver().get(List.class, Repository.class);
            return new DefaultClientResponse<>(javaType, serverResponse, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (serverResponse != null) {
                serverResponse.close();
            }
        }
    }

    @Override
    public ClientResponse<List<Repository>> listOrginazationRepo(String organization) {
        Response serverResponse = null;
        try {
            serverResponse = restApi().listOrganizationRepo(organization);
            JavaType javaType = collectionResolver().get(List.class, Repository.class);
            return new DefaultClientResponse<>(javaType, serverResponse, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (serverResponse != null) {
                serverResponse.close();
            }
        }
    }

    @Override
    public ClientResponse<List<Repository>> listUserRepo(String username) {
        Response serverResponse = null;
        try {
            serverResponse = restApi().listUserRepo(username);
            JavaType javaType = collectionResolver().get(List.class, Repository.class);
            return new DefaultClientResponse<>(javaType, serverResponse, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (serverResponse != null) {
                serverResponse.close();
            }
        }
    }

}
