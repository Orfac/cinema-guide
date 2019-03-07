package ru.kinoguide.controller.rest.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kinoguide.entity.CinemaNetwork;
import ru.kinoguide.service.CinemaNetworkService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CinemaNetworkAuthorizationFilter implements Filter {

    private CinemaNetworkService cinemaNetworkService;

    @Autowired
    public CinemaNetworkAuthorizationFilter(CinemaNetworkService cinemaNetworkService) {
        this.cinemaNetworkService = cinemaNetworkService;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String authToken = httpServletRequest.getHeader("Auth-Token");
        if (authToken == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        CinemaNetwork cinemaNetwork = cinemaNetworkService.findCinemaNetworkByToken(authToken);
        if (cinemaNetwork == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        request.setAttribute("cinemaNetwork", cinemaNetwork);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
