package godinho.savio.TvNews.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "people")
public class Person extends RepresentationModel<Person> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date deletedAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "reporter", fetch = FetchType.LAZY)
    private Set<NewsPaper> newsPapersAsReporter = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY)
    private Set<NewsPaper> newsPapersAsProducer = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_has_roles",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Set<NewsPaper> getNewsPapersAsReporter() {
        return newsPapersAsReporter;
    }

    public void setNewsPapersAsReporter(Set<NewsPaper> newsPapersAsReporter) {
        this.newsPapersAsReporter = newsPapersAsReporter;
    }

    public Set<NewsPaper> getNewsPapersAsProducer() {
        return newsPapersAsProducer;
    }

    public void setNewsPapersAsProducer(Set<NewsPaper> newsPapersAsProducer) {
        this.newsPapersAsProducer = newsPapersAsProducer;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
