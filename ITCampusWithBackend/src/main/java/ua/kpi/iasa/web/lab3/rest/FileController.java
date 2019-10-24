package ua.kpi.iasa.web.lab3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.kpi.iasa.web.lab3.data.ApiExceptionData;
import ua.kpi.iasa.web.lab3.exception.FileStorageException;
import ua.kpi.iasa.web.lab3.exception.InternalServerErrorException;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.FilePathConverter;
import ua.kpi.iasa.web.lab3.service.MimeTypeService;
import ua.kpi.iasa.web.lab3.service.StorageService;

@Tag(name = "file", description = "file operation (i.e., retrieving dynamic image file)")
@RestController
@RequestMapping(path = "/resources")
public class FileController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private MimeTypeService mimeTypeService;
    @Autowired
    private FilePathConverter filePathConverter;

    @Operation(summary = "Fetch file by fileName")
    @GetMapping(path = "/{fileName}")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "*/*"))
    @ApiResponse(responseCode = "500", description = "Internal server error: unable to fetch the file",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiExceptionData.class)))
    @ApiResponse(responseCode = "400", description = "Bad request: invalid fileName format",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiExceptionData.class)))
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        Resource resource;
        try {
            resource = storageService.loadAsResource(filePathConverter.dataToModel(fileName));
        } catch (FileStorageException e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
        final String contentType = mimeTypeService.getMimeType(resource);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
//        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
