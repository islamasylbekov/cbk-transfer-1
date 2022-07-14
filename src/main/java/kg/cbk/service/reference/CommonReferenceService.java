package kg.cbk.service.reference;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import kg.cbk.entity.reference.CommonReference;
import kg.cbk.repository.reference.CommonReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

import static kg.cbk.entity.reference.QCommonReference.commonReference;

@Service
public class CommonReferenceService {

    private final CommonReferenceRepository repository;

    @Autowired
    public CommonReferenceService(
            JpaRepository<CommonReference, Long> repository
    ) {
        this.repository = (CommonReferenceRepository) repository;
    }

    public Page<CommonReference> findAll(Predicate predicate, @NotNull Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    public List<CommonReference> findByTypeCode(String code) {
        return repository.findByTypeCode(code);
    }

    public Page<CommonReference> findParents(Predicate predicate, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder(predicate);
        builder.and(commonReference.parent.isNull());

        return findAll(Objects.requireNonNull(builder.getValue()), pageable);
    }

    public CommonReference getFirstFindByCode(String code){
        return repository.findByCode(code).get(0);
    }
}
