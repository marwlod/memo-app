package io.github.marwlod.memoapp.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

import static io.github.marwlod.memoapp.config.Constants.TEXT_LENGTH;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotEmpty(message = "Email required")
    @Size(max = TEXT_LENGTH, message = "maximum " + TEXT_LENGTH + " characters")
    @Email(message = "Invalid email")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password required")
    @Size(max = TEXT_LENGTH, message = "maximum " + TEXT_LENGTH + " characters")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "First name required")
    @Size(max = TEXT_LENGTH, message = "maximum " + TEXT_LENGTH + " characters")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name required")
    @Size(max = TEXT_LENGTH, message = "maximum " + TEXT_LENGTH + " characters")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "enabled")
    private int enabled;

    @ManyToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL)
    private List<Memo> memos;

    public User(String email, String password, String firstName, String lastName, int enabled, Set<Role> roles, List<Memo> memos) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.roles = roles;
        this.memos = memos;
    }
}