package co.develhope.interceptorsmiddleware2.interceptors;

import co.develhope.interceptorsmiddleware2.entities.Month;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    private List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1,"January","Gennaio", "Januar"),
            new Month(2,"February","Febbraio", "Februar"),
            new Month(3,"March","Marzo", "Marz"),
            new Month(4,"April","Aprile", "April"),
            new Month(5,"May","Maggio", "Mai"),
            new Month(6,"June","Giugno", "Juni")    ));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("monthNumber");
        if(header == null || header.isEmpty()){
            response.setStatus(410);
            return false;
        }
        else {  int monthNumber = Integer.parseInt(header);
            Month month = months.stream().filter(m -> m.getMonthNumber() == monthNumber).findFirst().orElseGet(() -> new Month(0, "nope", "nope", "nope"));
            request.setAttribute("month", month);
            return true;

        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}