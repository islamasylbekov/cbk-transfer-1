package kg.cbk.controller.api;

import com.querydsl.core.types.Predicate;
import kg.cbk.assembler.datatable.TransferResourceAssembler;
import kg.cbk.entity.Employee;
import kg.cbk.entity.Transfer;
import kg.cbk.resource.datatable.TransferResource;
import kg.cbk.resource.datatable.base.TableResource;
import kg.cbk.service.TransferService;
import kg.cbk.table.core.TableRequestData;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
public class TransferControllerApi {

    private final TransferResourceAssembler resourceAssembler;
    private final TransferService service;

    public TransferControllerApi(TransferResourceAssembler resourceAssembler, TransferService service) {
        this.resourceAssembler = resourceAssembler;
        this.service = service;
    }


    @PostMapping("/table")
    public TableResource<TransferResource> employees(
            @AuthenticationPrincipal Employee employee,
            @QuerydslPredicate(root = Transfer.class) Predicate predicate,
            @RequestBody TableRequestData requestData,
            @RequestParam(name = "statusCode", required = false) String code
    ) {
        return resourceAssembler.toTableResource(
                service.findAll(
                        predicate,
                        requestData.getPageable(),
                        employee,
                        code),
                requestData
        );
    }

}
