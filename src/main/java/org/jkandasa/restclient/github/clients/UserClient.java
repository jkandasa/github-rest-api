package org.jkandasa.restclient.github.clients;

import org.jkandasa.restclient.core.ClientResponse;
import org.jkandasa.restclient.github.model.User;

public interface UserClient {
    ClientResponse<User> selfInfo();

}
