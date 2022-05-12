package com.tcan.spring5.jdk18.common.model;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SearchResult<T> implements Serializable, Iterable<T> {
    private static final long serialVersionUID = 1L;

    private final List<T> content;
    private final long totalElements;
    private final int pageSize;
    private final int pageNumber;

    public SearchResult(List<T> content,
                        long totalElements,
                        int pageSize,
                        int number) {
        this.content = content;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.pageNumber = number;
    }

    public SearchResult(List<T> content, SearchResult<?> copyOtherProperties) {
        this.content = content;
        this.totalElements = copyOtherProperties.totalElements;
        this.pageNumber = copyOtherProperties.pageNumber;
        this.pageSize = copyOtherProperties.pageSize;
    }

    public SearchResult() {
        this.content = new ArrayList<>();
        this.totalElements = 0;
        this.pageNumber = 0;
        this.pageSize = 0;
    }

    public SearchResult(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }

    public List<T> getContent() {
        return content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    /**
     * Returns total number of pages
     *
     * @return total number of pages
     * @see Page#getTotalPages()
     */
    public int getTotalPages() {
        return getPageSize() == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) getPageSize());
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getOffset() {
        return pageNumber * pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
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

    public Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    public <X> SearchResult<X> map(Function<T, X> mapper) {
        final List<X> mappedRows = content.stream().map(mapper).collect(Collectors.toList());
        return new SearchResult<>(mappedRows, this);
    }
}
