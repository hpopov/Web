package ua.kpi.iasa.web.lab3.service;

import org.springframework.core.io.Resource;

public interface MimeTypeService {

    String getMimeType(Resource resource);
}
