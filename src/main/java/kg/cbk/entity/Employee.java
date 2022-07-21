package kg.cbk.entity;

import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;
import kg.cbk.component.Selectable;
import kg.cbk.entity.base.TimedEntity;
import kg.cbk.entity.security.SecurityRole;
import kg.cbk.util.TimeHelper;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee extends TimedEntity implements UserDetails, Selectable {

    private static final long serialVersionUID = 2450947553087439883L;
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String name;

    private String patronymic;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = TimeHelper.DATE_FORMAT)
    private LocalDate dateOfBirth;

    @Column(columnDefinition = "boolean default false")
    private boolean enabled;

    @Column(name = "password_expiration_time")
    private LocalDateTime passwordExpirationTime;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(name = "rank")
    private String rank;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private SecurityRole role;

    @ManyToMany
    @JoinTable(name = "m2m_employee_security_role",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "security_role_id")})
    private Set<SecurityRole> roles;

    @Transient
    private Set<SecurityRole> sRoles = new HashSet<>();

    @Transient
    private Collection<? extends GrantedAuthority> sAuthorities = new HashSet<>();

    /**
     * QueryDsl
     * Поиск сотрудников по роли
     */
    @Transient
    @QueryType(PropertyType.STRING)
    private String qRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return sAuthorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        boolean isNonExpired = false;
        if (this.passwordExpirationTime != null) {
            isNonExpired = LocalDateTime.now().isBefore(this.passwordExpirationTime);
        }
        return isNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getSelectorId() {
        return String.valueOf(getId());
    }

    @Override
    public String getSelectorTitle() {
        return String.format("%s %s - %s", surname, name, role.getTitle());
    }

    public String getFullName() {
        return String.format("%s %s %s", surname, name, patronymic != null ? patronymic : "");
    }

    public boolean hasRole(String role) {
        return sAuthorities.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }
}
