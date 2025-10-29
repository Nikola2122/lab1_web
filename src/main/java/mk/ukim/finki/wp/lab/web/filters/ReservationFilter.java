package mk.ukim.finki.wp.lab.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@WebFilter
public class ReservationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String method = req.getMethod();
        String path = req.getServletPath();
        BookReservation reservation = (BookReservation) req.getAttribute("book");

        if(!path.isEmpty() && method.equals("GET") && reservation == null){
            resp.sendRedirect("/");
        }
        else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
