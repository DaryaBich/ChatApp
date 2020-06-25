package chatApp.web.configurations;

import chatApp.backEndApp.configurations.RootApplicationConfiguration;
import chatApp.web.servlets.CustomServlet;
import org.h2.server.web.WebServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //Total Application config class
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootApplicationConfiguration.class};
    }
    //Web Application Config Class
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebApplicationContextConfig.class};
    }
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("h2-console", new WebServlet());
        servlet.setLoadOnStartup(2);
        servlet.addMapping("/h2-console/*");
        //custom servlet Example
        ServletRegistration.Dynamic myServlet = servletContext.addServlet("myServlet", new CustomServlet());
        myServlet.setLoadOnStartup(3);
        myServlet.addMapping("/myServlet/*");
    }
}


