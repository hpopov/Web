package ua.kpi.iasa.web.lab3.service.impl;

import javax.servlet.ServletConfig;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import ua.kpi.iasa.web.lab3.service.MimeTypeService;

@Service
public class WebConfigMimeTypeService implements MimeTypeService {

    @Autowired
    private ServletConfig servletConfig;

    @Override
    public String getMimeType(Resource resource) {
        final String mimeType = servletConfig.getServletContext().getMimeType(resource.getFilename());
        if (StringUtils.isNotBlank(mimeType)) {
            return mimeType;
        }
        return MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }
}
