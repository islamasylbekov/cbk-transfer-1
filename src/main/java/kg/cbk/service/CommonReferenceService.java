package kg.cbk.service;

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
public interface CommonReferenceService {

    Page<CommonReference> findAll(Predicate predicate, @NotNull Pageable pageable);

    List<CommonReference> findByTypeCode(String code);


    CommonReference getFirstFindByCode(String code);
}
