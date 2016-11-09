package org.jkandasa.restclient.core.jaxrs;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.jkandasa.restclient.core.ClientInfo;
import org.jkandasa.restclient.core.jaxrs.fasterxml.jackson.GHJacksonJson2Provider;
import org.jkandasa.restclient.core.jaxrs.fasterxml.jackson.JacksonObjectMapperProvider;
import org.jboss.resteasy.client.jaxrs.ProxyBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestFactory<T> {

    private final ClassLoader classLoader;
    private Class<T> apiClassType;

    public RestFactory(Class<T> clz) {
        classLoader = null;
        apiClassType = clz;
    }

    public RestFactory(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public T createAPI(ClientInfo clientInfo) {
        final HttpClient httpclient;
        if (clientInfo.getEndpointUri().toString().startsWith("https") && clientInfo.getTrustAnyHost().isPresent()
                && clientInfo.getTrustAnyHost().get()) {
            httpclient = getHttpClient();
        } else {
            httpclient = HttpClientBuilder.create().build();
        }
        final ResteasyClient client;

        ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpclient);
        client = new ResteasyClientBuilder().httpEngine(engine).build();

        client.register(JacksonJaxbJsonProvider.class);
        client.register(JacksonObjectMapperProvider.class);
        client.register(RestRequestFilter.class);
        client.register(new RequestHeadersFilter(clientInfo.getHeaders()));
        client.register(RestResponseFilter.class);
        client.register(GHJacksonJson2Provider.class);

        ProxyBuilder<T> proxyBuilder = client.target(clientInfo.getEndpointUri()).proxyBuilder(apiClassType);
        if (classLoader != null) {
            proxyBuilder = proxyBuilder.classloader(classLoader);
        }
        return proxyBuilder.build();
    }

    //trust any host
    private HttpClient getHttpClient() {
        CloseableHttpClient httpclient = null;

        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(keyStore, (TrustStrategy) (trustedCert, nameConstraints) -> true);

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            _logger.error("Failed to create HTTPClient: {}", e);
        }

        return httpclient;
    }
}