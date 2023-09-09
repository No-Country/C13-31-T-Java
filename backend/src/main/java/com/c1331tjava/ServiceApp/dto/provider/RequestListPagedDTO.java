package com.c1331tjava.ServiceApp.dto.provider;

import com.c1331tjava.ServiceApp.model.Request;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * A Data Transfer Object (DTO) to return paged Request list
 */
@Data
public class RequestListPagedDTO {

    private int totalPages;
    private int requestPerPage;
    private long totalRequest;
    private int currentPage;
    private List<RequestProviderDTO> requests;

    /**
     * Constructor to fill DTO attributes with Page info.
     *
     * @param page type: Page<>, comes from Pageable
     */
    public RequestListPagedDTO(Page<Request> page) {
        ModelMapper modelMapper = new ModelMapper();
        this.totalPages = page.getTotalPages();
        this.requestPerPage = page.getNumberOfElements();
        this.totalRequest = page.getTotalElements();
        this.currentPage = page.getNumber();
        this.requests = page.getContent().stream().
                map(a->modelMapper.map(a, RequestProviderDTO.class)).toList();
    }
}
