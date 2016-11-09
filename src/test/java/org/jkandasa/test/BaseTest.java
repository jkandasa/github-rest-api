package org.jkandasa.test;

import java.net.URISyntaxException;
import java.util.Random;

import org.jkandasa.restclient.core.RestClient;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    public static RestClient client = null;
    public static final Random RANDOM = new Random();

    @BeforeSuite
    public void setupClient() throws URISyntaxException {
        String uri = getEnv("GITHUB_API_URI", "https://api.github.com");
        String username = getEnv("GITHUB_USERNAME", "vimplecom");
        String password = getEnv("GITHUB_PASSWORD", "vimplecom@123");
        client = RestClient.builder().uri(uri).basicAuthentication(username, password).build();
    }

    public String getEnv(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null ? defaultValue : value;
    }
}
