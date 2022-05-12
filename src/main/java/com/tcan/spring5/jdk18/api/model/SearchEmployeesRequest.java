package com.tcan.spring5.jdk18.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcan.spring5.jdk18.dao.model.Status;
import com.tcan.spring5.jdk18.service.model.SearchEmployeeQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

import static com.tcan.spring5.jdk18.constant.ApplicationConstants.*;

@Getter
@Setter
@ToString
public class SearchEmployeesRequest {
    private String email;
    private String fullname;
    private String title;
    @JsonFormat(pattern = YYYY_MM_DD, timezone = TIME_ZONE_ISTANBUL)
    private Date createDate;
    private String status;

    public SearchEmployeeQuery toQuery() {
        final SearchEmployeeQuery query = new SearchEmployeeQuery();
        query.setEmail(getEmail());
        query.setFullname(getFullname());
        query.setTitle(getTitle());
        query.setCreateDate(getCreateDate());
        query.setStatus(Status.getOrDefault(getStatus(), null));
        return query;
    }
}
