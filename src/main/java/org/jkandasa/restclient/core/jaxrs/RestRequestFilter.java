package org.jkandasa.restclient.core.jaxrs;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;

import org.jkandasa.restclient.core.jaxrs.fasterxml.jackson.ClientObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class RestRequestFilter implements ClientRequestFilter {

    private static ObjectMapper OBJECT_MAPPER = new ClientObjectMapper();

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        logRequests(requestContext);
    }

    private void logRequests(ClientRequestContext requestContext) throws JsonProcessingException {
        if (_logger.isDebugEnabled()) {
            _logger.debug(">> HTTP: {}", requestContext.getMethod());
            _logger.debug(">> URI: {}", requestContext.getUri());
            _logger.debug(">> Headers: {}", requestContext.getHeaders());
            _logger.debug(">> Data: {}", OBJECT_MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(requestContext.getEntity()));
        }
    }
}