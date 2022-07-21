package kg.cbk.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ApplicationAdvice {

    @Value("${spring.profiles.active:default}")
    private String profile;

    @ModelAttribute("profile")
    public String activeProfile() {
        return profile;
    }
}
