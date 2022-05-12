package com.tcan.spring5.jdk18.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Pager implements Serializable {
	private static final long serialVersionUID = 1L;

	private int page;
	private int max;

	public Pager() {
		this(0, 20);
	}

	public Pager(int page, int max) {
		this.page = page;
		this.max = max;
	}

	public Pageable toPageable() {
		return PageRequest.of(page, max);
	}

	public Pageable toPageableWithSort(Sort s) {
		return PageRequest.of(page, max, s);
	}

	public static Pager max() {
		return new Pager(0, Integer.MAX_VALUE);
	}
}
