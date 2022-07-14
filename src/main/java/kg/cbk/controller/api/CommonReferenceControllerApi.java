package kg.cbk.controller.api;

import com.querydsl.core.types.Predicate;
import kg.cbk.entity.reference.CommonReference;
import kg.cbk.service.reference.CommonReferenceService;
import kg.cbk.assembler.datatable.CommonReferenceResourceAssembler;
import kg.cbk.resource.datatable.ReferenceResource;
import kg.cbk.resource.datatable.base.TableResource;
import kg.cbk.table.core.TableRequestData;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common-reference")
public class CommonReferenceControllerApi {

    private final CommonReferenceService commonReferenceService;
    private final CommonReferenceResourceAssembler commonReferenceResourceAssembler;

    public CommonReferenceControllerApi(
            CommonReferenceService commonReferenceService,
            CommonReferenceResourceAssembler commonReferenceResourceAssembler
    ) {
        this.commonReferenceService = commonReferenceService;
        this.commonReferenceResourceAssembler = commonReferenceResourceAssembler;
    }

    @PostMapping("/table")
    public TableResource<ReferenceResource> commonReference(
            @QuerydslPredicate(root = CommonReference.class) Predicate predicate,
            @RequestBody TableRequestData requestData
    ) {
        return commonReferenceResourceAssembler.toTableResource(
                commonReferenceService.findAll(predicate, requestData.getPageable()),
                requestData
        );
    }

}
