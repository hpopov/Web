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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.data.RegistrationData;
import ua.kpi.iasa.web.lab3.mapping.converter.Converter;
import ua.kpi.iasa.web.lab3.mapping.converter.impl.PublicUserConverter;
import ua.kpi.iasa.web.lab3.service.MimeTypeService;
import ua.kpi.iasa.web.lab3.service.UserAvatarService;
import ua.kpi.iasa.web.lab3.service.UserService;

@RestController
@RequestMapping("/rest/users")
public class UserController extends LoggableComponent {

    @Autowired
    private UserService userService;

    @Autowired
    private MimeTypeService mimeTypeService;

    @Autowired
    private UserAvatarService userAvatarService;

    @Autowired
    private PublicUserConverter publicUserConverter;

    @GetMapping("/{username}")
    public PublicUserData getUserByLogin(@PathVariable("username") String username) {
        return publicUserConverter.modelToData(userService.getUserByUsername(username));
    }

    @PostMapping
    public PublicUserData registerUser(@RequestBody RegistrationData registrationData) {
        return publicUserConverter.modelToData(userService.createRegularUser(registrationData));
    }

    @GetMapping
    public Collection<PublicUserData> getAllUsers() {
        return Converter.mapToList(publicUserConverter::modelToData, userService.getAllUsers());
    }

    @PutMapping("/{username}")
    public PublicUserData updatePublicUserByLogin(@PathVariable String username,
            @RequestBody PublicUserData publicUserData) {
        publicUserData.setLogin(username);
        return publicUserConverter.modelToData(userService.updateUser(publicUserData));
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @PutMapping("/{username}/avatar")
    public void changeUserAvatar(@PathVariable String username, @RequestParam MultipartFile file) {
        userAvatarService.changeUserAvatar(username, file);
    }

    @GetMapping("/{username}/avatar")
    public ResponseEntity<Resource> getUserAvatar(@PathVariable String username) {
        final Resource avatarResource = userAvatarService.getUserAvatarImage(username);
        final String contentType = mimeTypeService.getMimeType(avatarResource);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(avatarResource);
    }
}
