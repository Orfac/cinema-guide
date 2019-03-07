package ru.kinoguide.controller.rest.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kinoguide.MappingProperties;

@Configuration
public class CinemaNetworkAuthorizationFilterConfiguration {

    private CinemaNetworkAuthorizationFilter cinemaNetworkAuthorizationFilter;

    private MappingProperties mappingProperties;

    @Autowired
    public CinemaNetworkAuthorizationFilterConfiguration(CinemaNetworkAuthorizationFilter cinemaNetworkAuthorizationFilter, MappingProperties mappingProperties) {
        this.cinemaNetworkAuthorizationFilter = cinemaNetworkAuthorizationFilter;
        this.mappingProperties = mappingProperties;
    }

    @Bean
    public FilterRegistrationBean initFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(cinemaNetworkAuthorizationFilter);
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns(mappingProperties.getCinemaNetworkRestApiPath());
        return filterRegistrationBean;
    }

}
