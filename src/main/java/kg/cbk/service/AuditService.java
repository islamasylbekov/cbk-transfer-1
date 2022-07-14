package kg.cbk.service;

import kg.cbk.entity.audit.RevEntity;
import kg.cbk.entity.base.BaseEntity;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.AnnotationRevisionMetadata;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuditService {

    private final EntityManager entityManager;

    @Autowired
    public AuditService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <E extends BaseEntity> Page<Revision<Long, E>> find(Long id, Class<E> aClass, Pageable pageable) {
        AuditReader reader = AuditReaderFactory.get(entityManager);

        List<Number> numbers = reader.getRevisions(aClass, id);
        List<Number> slice = limit(numbers, pageable);
        List<Revision<Long, E>> list = toRevisions(slice, aClass, id, reader);

        return new PageImpl<Revision<Long, E>>(list, pageable, numbers.size());
    }

    private <E> Revision<Long, E> toRevision(E entity, RevEntity rev) {
        AnnotationRevisionMetadata<Long> metadata = new AnnotationRevisionMetadata<>(rev, RevisionNumber.class, RevisionTimestamp.class);

        return Revision.of(metadata, entity);
    }

    private <E> List<Revision<Long, E>> toRevisions(
            List<Number> numbers,
            Class<E> entityClass,
            Long entityId,
            AuditReader auditReader) {

        if (numbers.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Number, RevEntity> revisions = auditReader.findRevisions(RevEntity.class, new HashSet<>(numbers));

        return revisions.entrySet()
                .stream()
                .map(entry -> {
                    E entity = auditReader.find(entityClass, entityId, entry.getKey());
                    return toRevision(entity, entry.getValue());
                })
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private List<Number> limit(List<Number> revNumbers, Pageable pageable) {
        revNumbers.sort(Collections.reverseOrder());
        if (pageable.getOffset() > revNumbers.size()) {
            return Collections.emptyList();
        }

        long upperBound = pageable.getOffset() + pageable.getPageSize();
        upperBound = upperBound > revNumbers.size() ? revNumbers.size() : upperBound;

        return revNumbers.subList((int) pageable.getOffset(), (int) upperBound);
    }
}