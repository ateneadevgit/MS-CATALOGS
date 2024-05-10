package com.fusm.catalogs.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogItemRequestModel {

    private String description;
    private String name;
    private Integer catalogId;
    private String createdBy;
    private Integer order;

}
