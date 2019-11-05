package ua.kpi.iasa.web.lab3.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.data.RegistrationData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.PublicUserConverter;
import ua.kpi.iasa.web.lab3.rest.apiresponses.NotFoundApiResponse;
import ua.kpi.iasa.web.lab3.rest.apiresponses.UnauthorizedApiResponse;
import ua.kpi.iasa.web.lab3.service.MimeTypeService;
import ua.kpi.iasa.web.lab3.service.UserAvatarService;
import ua.kpi.iasa.web.lab3.service.UserService;

@Tag(name = "user", description = "the user API")
@RestController
@RequestMapping(path = "/rest/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MimeTypeService mimeTypeService;

    @Autowired
    private UserAvatarService userAvatarService;

    @Autowired
    private PublicUserConverter publicUserConverter;

    @Operation(summary = "Get user by username")
    @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @NotFoundApiResponse
    public PublicUserData getUserByLogin(@PathVariable String username) {
        return publicUserConverter.modelToData(userService.getUserByUsername(username));
    }

    @Operation(summary = "Create a new user")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "201", description = "Successful operation")
    @ResponseStatus(HttpStatus.CREATED)
    public PublicUserData registerUser(@RequestBody RegistrationData registrationData) {
        return publicUserConverter.modelToData(userService.createRegularUser(registrationData));
    }

    @Operation(summary = "Get list of all registered users")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public Collection<PublicUserData> getAllUsers() {
        return Converter.mapToList(publicUserConverter::modelToData, userService.getAllUsers());
    }

    @Operation(summary = "Update user by username")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @UnauthorizedApiResponse
    @NotFoundApiResponse
    @PutMapping(path = "/{username}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PublicUserData updatePublicUserByLogin(@PathVariable String username,
            @RequestBody PublicUserData publicUserData) {
        publicUserData.setLogin(username);
        return publicUserConverter.modelToData(userService.updateUser(publicUserData));
    }

    @Operation(summary = "Delete user by username")
    @ApiResponse(responseCode = "204", description = "Successful operation")
    @UnauthorizedApiResponse
    @NotFoundApiResponse
    @DeleteMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @Operation(summary = "Update user avatar by username")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @UnauthorizedApiResponse
    @NotFoundApiResponse
    @PutMapping(path = "/{username}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void changeUserAvatar(@PathVariable String username, @RequestPart MultipartFile file) {
        userAvatarService.changeUserAvatar(username, file);
    }

    @Operation(summary = "Get user avatar by username")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @NotFoundApiResponse
    @GetMapping(path = "/{username}/avatar")
    public ResponseEntity<Resource> getUserAvatar(@PathVariable String username) {
        final Resource avatarResource = userAvatarService.getUserAvatarImage(username);
        final String contentType = mimeTypeService.getMimeType(avatarResource);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(avatarResource);
    }
}
