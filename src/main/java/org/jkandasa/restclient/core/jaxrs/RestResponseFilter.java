package org.jkandasa.restclient.core.jaxrs;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class RestResponseFilter implements ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext)
            throws IOException {
        logResponses(responseContext);
    }

    private void logResponses(ClientResponseContext responseContext) {
        if (_logger.isDebugEnabled()) {
            _logger.debug("<< Response headers:{}", responseContext.getHeaders());
            _logger.debug("<< Status -> code:{}, message:{}",
                    responseContext.getStatusInfo().getStatusCode(),
                    responseContext.getStatusInfo().getReasonPhrase());
        }
    }

}