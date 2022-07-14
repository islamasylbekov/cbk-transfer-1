package kg.cbk.entity.audit;

import kg.cbk.entity.Employee;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        if (SecurityContextHolder.getContext() == null ||
                SecurityContextHolder.getContext().getAuthentication() == null ||
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null ||
                !(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Employee)) {
            return;
        }

        RevEntity revision = (RevEntity) revisionEntity;

        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        revision.setAuditor(employee);
    }

}