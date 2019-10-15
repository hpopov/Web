package ua.kpi.iasa.web.lab3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.kpi.iasa.web.lab3.exception.FileStorageException;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.FilePathConverter;
import ua.kpi.iasa.web.lab3.service.MimeTypeService;
import ua.kpi.iasa.web.lab3.service.StorageService;

@RestController
@RequestMapping("/resources")
public class FileController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private MimeTypeService mimeTypeService;
    @Autowired
    private FilePathConverter filePathConverter;

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) throws FileStorageException {
        Resource resource = storageService.loadAsResource(filePathConverter.dataToModel(fileName));
        final String contentType = mimeTypeService.getMimeType(resource);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
//        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
