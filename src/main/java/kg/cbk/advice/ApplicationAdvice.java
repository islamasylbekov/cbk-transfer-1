package kg.cbk.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ApplicationAdvice {

    @Value("${spring.profiles.active:default}")
    private String profile;

    private final MultipartProperties multipartProperties;

    public ApplicationAdvice(MultipartProperties multipartProperties) {
        this.multipartProperties = multipartProperties;
    }

    @ModelAttribute("profile")
    public String activeProfile() {
        return profile;
    }

    @ModelAttribute("maxFileSizeInBytes")
    public long maxFileSizeInBytes() {
        return multipartProperties.getMaxFileSize().toBytes();
    }

}
