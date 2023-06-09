package pl.coderslab.charity.user.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pl.coderslab.charity.role.entity.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@SQLDelete(sql="Update `charity-donation`.user set deleted=true where id=?")
@Where(clause = "deleted=false")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    private String username;
    private String firstname;
    private String surname;
    private String password;
    private Integer enabled;
    private boolean deleted = Boolean.FALSE;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}  