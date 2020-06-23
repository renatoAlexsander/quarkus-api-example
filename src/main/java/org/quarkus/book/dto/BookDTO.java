package org.quarkus.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime registerAt;
    private LocalDateTime editedAt;

}
