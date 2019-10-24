package ua.kpi.iasa.web.lab3.rest.apiresponses;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ua.kpi.iasa.web.lab3.config.OasConfiguration;

@Retention(RUNTIME)
@Target({ TYPE, METHOD, ANNOTATION_TYPE })
@SecurityRequirement(name = OasConfiguration.SECURITY_SCHEME)
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
public @interface UnauthorizedApiResponse {

}
