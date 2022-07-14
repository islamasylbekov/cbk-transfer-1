package kg.cbk.service.security;

import com.querydsl.core.BooleanBuilder;
import kg.cbk.entity.Employee;
import kg.cbk.entity.security.SecurityRole;
import kg.cbk.repository.security.SecurityRoleRepository;
import kg.cbk.service.crud.AbstractCrudService;
import kg.cbk.entity.security.QSecurityRole;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SecurityRoleService extends AbstractCrudService<SecurityRole, SecurityRole> {

    private final SecurityRoleRepository repository;

    public SecurityRoleService(
            SecurityRoleRepository repository) {
        super(repository, repository);
        this.repository = repository;
    }

    @NotNull
    @Override
    public SecurityRole create(
            @NotNull SecurityRole entity
    ) {
        return save(entity);
    }

    @NotNull
    @Override
    public SecurityRole update(
            @NotNull SecurityRole source,
            @NotNull SecurityRole target
    ) {
        target.setTitle(source.getTitle());
        target.setCode(source.getCode());
        return save(target);
    }

    public SecurityRole findRoleByCode(String code) {
        return Optional.ofNullable(repository.findByCode(code)).orElseThrow(EntityNotFoundException::new);
    }

    public Set<SecurityRole> getAllByEmployee(Employee employee) {
        final QSecurityRole root = QSecurityRole.securityRole;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(root.in(employee.getRoles()));

        return StreamSupport
                .stream(repository
                        .findAll(Objects.requireNonNull(builder.getValue()))
                        .spliterator(), false)
                .collect(Collectors.toSet());
    }
}
