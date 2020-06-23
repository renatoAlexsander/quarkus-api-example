package org.quarkus.book.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOOKS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode(of = {"id", "name", "price", "registerAt", "editedAt"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOOK")
    @SequenceGenerator(name = "SEQ_BOOK", sequenceName = "SEQ_BOOK", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true, name = "NAME")
    @Setter
    private String name;

    @NotNull
    @Column(nullable = false, name = "PRICE")
    @Setter
    private BigDecimal price;

    @Column(nullable = false, updatable = false, name = "REGISTER_AT")
    private LocalDateTime registerAt;

    @Column(name = "EDITED_AT")
    private LocalDateTime editedAt;

    @PrePersist
    public void prePersist() {
        this.registerAt = LocalDateTime.now();
    }

    @PostPersist
    public void posPersist() {
        this.editedAt = LocalDateTime.now();
    }

}
