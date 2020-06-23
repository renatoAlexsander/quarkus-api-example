package org.quarkus.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.quarkus.book.dto.BookDTO;
import org.quarkus.book.model.Book;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "registerAt", target = "registerAt")
    @Mapping(source = "editedAt", target = "editedAt")
    Book of(BookDTO bookDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "registerAt", target = "registerAt")
    @Mapping(source = "editedAt", target = "editedAt")
    BookDTO of(Book book);
}
