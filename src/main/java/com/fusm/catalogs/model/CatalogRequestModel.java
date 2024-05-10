package com.fusm.catalogs.model;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CatalogRequestModel {

    private String description;
    private String name;
    private String createdBy;

}
