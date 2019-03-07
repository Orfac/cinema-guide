package ru.kinoguide;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public class MappingProperties {

    private String cinemaNetworkRestApiPath;

    public MappingProperties() {
    }

    public String getCinemaNetworkRestApiPath() {
        return cinemaNetworkRestApiPath;
    }

    public void setCinemaNetworkRestApiPath(String cinemaNetworkRestApiPath) {
        this.cinemaNetworkRestApiPath = cinemaNetworkRestApiPath;
    }
}
