package kg.cbk.controller.web.reference;

import kg.cbk.table.reference.CommonReferenceTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/common-reference")
public class CommonReferenceController {

    private final CommonReferenceTable table;

    @Autowired
    public CommonReferenceController(
            CommonReferenceTable table
    ) {
        this.table = table;
    }

    @GetMapping
    public ModelAndView list() {
        return new ModelAndView(String.format("%s/list", templates()))
                .addObject("table", table);
    }

    public @NotNull String templates() {
        return "/common-reference";
    }

    public @NotNull String url() {
        return "/common-reference";
    }

}
