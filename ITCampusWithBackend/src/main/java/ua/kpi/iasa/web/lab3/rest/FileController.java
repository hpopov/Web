package ua.kpi.iasa.web.lab3.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.kpi.iasa.web.lab3.data.ApiExceptionData;
import ua.kpi.iasa.web.lab3.data.TemporaryFileData;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.TemporaryFileConverter;
import ua.kpi.iasa.web.lab3.rest.apiresponses.NotFoundApiResponse;
import ua.kpi.iasa.web.lab3.rest.apiresponses.UnauthorizedApiResponse;
import ua.kpi.iasa.web.lab3.service.FileService;
import ua.kpi.iasa.web.lab3.service.MimeTypeService;
import ua.kpi.iasa.web.lab3.service.TemporaryFileService;

@Tag(name = "file", description = "file operation (i.e., retrieving dynamic image file)")
@RestController
@RequestMapping(path = "/resources")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private TemporaryFileService temporaryFileService;
    @Autowired
    private MimeTypeService mimeTypeService;

    @Autowired
    private TemporaryFileConverter temporaryFileConverter;

    @Operation(summary = "Download file by fileName")
    @GetMapping(path = "/{fileName}")
    @NotFoundApiResponse
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "*/*"))
    @ApiResponse(responseCode = "500", description = "Internal server error: unable to fetch the file",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiExceptionData.class)))
    @ApiResponse(responseCode = "400", description = "Bad request: invalid fileName format",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ApiExceptionData.class)))
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Resource resource = fileService.loadFileByFileName(fileName);
        final String contentType = mimeTypeService.getMimeType(resource);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
    }

    @Operation(summary = "Upload temporary user file")
    @PostMapping(path = "/temporary", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "201", description = "Successful operation")
    @UnauthorizedApiResponse
    @ResponseStatus(HttpStatus.CREATED)
    public TemporaryFileData uploadTemporaryFile(@RequestPart MultipartFile file, HttpServletRequest request) {
        final String username = request.getUserPrincipal().getName();
        return temporaryFileConverter.modelToData(temporaryFileService.uploadTemporaryFileForUser(file, username));
    }
}
