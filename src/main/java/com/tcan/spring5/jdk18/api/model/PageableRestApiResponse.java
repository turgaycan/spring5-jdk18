package com.tcan.spring5.jdk18.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcan.spring5.jdk18.common.model.SearchResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class PageableRestApiResponse<T> extends BaseRestResponse implements Serializable, Iterable<T> {
    private static final long serialVersionUID = 1L;

    private final List<T> content;
    private final long totalElements;
    private final int pageSize;
    private final int pageNumber;

    public PageableRestApiResponse() {
        super(RestResponseStatus.ok());
        this.content = new ArrayList<>();
        this.totalElements = 0;
        this.pageNumber = 0;
        this.pageSize = 0;
    }

    public PageableRestApiResponse(List<T> content, SearchResult<?> copyOtherProperties) {
        super(RestResponseStatus.ok());
        this.content = content;
        this.totalElements = copyOtherProperties.getTotalElements();
        this.pageNumber = copyOtherProperties.getPageNumber();
        this.pageSize = copyOtherProperties.getPageSize();
    }


    public PageableRestApiResponse(Page<T> page) {
        super(RestResponseStatus.ok());
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PageableRestApiResponse(@JsonProperty("content") List<T> content,
                                   @JsonProperty("totalElements") long totalElements,
                                   @JsonProperty("pageSize") int pageSize,
                                   @JsonProperty("pageNumber") int pageNumber) {
        super(RestResponseStatus.ok());
        this.content = content;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public <C extends Collection<M>, M> C convertRows(Function<? super T, ? extends M> mapper, Collector<M, ?, C> collector) {
        return content.stream().map(mapper).collect(collector);
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        content.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return content.spliterator();
    }

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    public <X> PageableRestApiResponse<X> map(Function<T, X> mapper) {
        final List<X> mappedRows = content.stream().map(mapper).collect(Collectors.toList());
        return new PageableRestApiResponse<>(mappedRows, this.totalElements, this.pageSize, this.pageNumber);
    }
}
