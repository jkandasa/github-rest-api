package org.jkandasa.restclient.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;

public class RestClientBuilder {

    private URI uri;
    private Optional<Boolean> trustAnyHost = Optional.empty();
    private Optional<String> username = Optional.empty();
    private Map<String, Object> headers = new HashMap<>();

    public RestClientBuilder uri(String uri) throws URISyntaxException {
        return uri(new URI(uri));
    }

    public RestClientBuilder uri(URI uri) {
        this.uri = uri;
        return this;
    }

    public RestClientBuilder basicAuthentication(String username, String password) {
        this.username = Optional.ofNullable(username);
        headers.put(RestClient.KEY_HEADER_AUTHORIZATION,
                "Basic " + Base64.encodeBase64String((username + ":" + password).getBytes()));
        return this;
    }

    public RestClientBuilder tokenAuthentication(String token) {
        headers.put(RestClient.KEY_HEADER_AUTHORIZATION, "Bearer " + token);
        return this;
    }

    public RestClientBuilder addHeader(String key, Object value) {
        headers.put(key, value);
        return this;
    }

    public RestClientBuilder trustAnyHost(Optional<Boolean> trustAnyHost) {
        this.trustAnyHost = trustAnyHost;
        return this;
    }

    public RestClient build() {
        ClientInfo clientInfo = new ClientInfo(uri, username, trustAnyHost, headers);
        return new RestClient(clientInfo);
    }
}