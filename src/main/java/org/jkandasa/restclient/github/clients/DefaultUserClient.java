package org.jkandasa.restclient.github.clients;

import javax.ws.rs.core.Response;

import org.jkandasa.restclient.core.BaseClient;
import org.jkandasa.restclient.core.ClientInfo;
import org.jkandasa.restclient.core.ClientResponse;
import org.jkandasa.restclient.core.DefaultClientResponse;
import org.jkandasa.restclient.core.jaxrs.ResponseCodes;
import org.jkandasa.restclient.core.jaxrs.RestFactory;
import org.jkandasa.restclient.github.jaxrs.handlers.UserHandler;
import org.jkandasa.restclient.github.model.User;

import com.fasterxml.jackson.databind.JavaType;

public class DefaultUserClient extends BaseClient<UserHandler> implements UserClient {
    public DefaultUserClient(ClientInfo clientInfo) {
        super(clientInfo, new RestFactory<>(UserHandler.class));
    }

    @Override
    public ClientResponse<User> selfInfo() {
        Response serverResponse = null;
        try {
            serverResponse = restApi().selfInfo();
            JavaType javaType = simpleResolver().get(User.class);

            return new DefaultClientResponse<>(javaType, serverResponse, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (serverResponse != null) {
                serverResponse.close();
            }
        }
    }

}
