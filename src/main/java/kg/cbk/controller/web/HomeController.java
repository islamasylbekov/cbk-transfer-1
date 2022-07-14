package kg.cbk.controller.web;

import kg.cbk.entity.Employee;
import kg.cbk.util.RedirectUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ModelAndView get(
            @AuthenticationPrincipal Employee employee
    ) {
        return RedirectUtil.redirect(employee.getRole().getDefaultPage());
    }
}
