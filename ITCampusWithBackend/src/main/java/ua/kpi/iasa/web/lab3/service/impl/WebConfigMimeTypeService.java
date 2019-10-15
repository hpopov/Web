package ua.kpi.iasa.web.lab3.service.impl;

import javax.servlet.ServletConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.service.MimeTypeService;

@Service
public class WebConfigMimeTypeService implements MimeTypeService {

    @Autowired
    private ServletConfig servletConfig;

    @Override
    public String getMimeType(Resource resource) {
        return servletConfig.getServletContext().getMimeType(resource.getFilename());
    }
}
