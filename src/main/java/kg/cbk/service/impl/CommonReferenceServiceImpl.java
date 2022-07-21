package kg.cbk.service.impl;

import com.querydsl.core.types.Predicate;
import kg.cbk.entity.reference.CommonReference;
import kg.cbk.repository.reference.CommonReferenceRepository;
import kg.cbk.service.CommonReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class CommonReferenceServiceImpl implements CommonReferenceService {

    private final CommonReferenceRepository repository;

    public CommonReferenceServiceImpl(
            JpaRepository<CommonReference, Long> repository
    ) {
        this.repository = (CommonReferenceRepository) repository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommonReference> findAll(Predicate predicate, @NotNull Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommonReference> findByTypeCode(String code) {
        return repository.findByTypeCode(code);
    }

    @Transactional(readOnly = true)
    @Override
    public CommonReference getFirstFindByCode(String code){
        return repository.findByCode(code).get(0);
    }
}
