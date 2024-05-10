package com.fusm.catalogs.dto;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface CatalogDto {

    @Value("#{target.id_catalog}")
    Integer getIdCatalog();

    @Value("#{target.description}")
    String getDescription();

    @Value("#{target.created_at}")
    Date getCreatedAt();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.created_by}")
    String getCreatedBy();

    @Value("#{target.enabled}")
    Boolean getEnabled();

    @Value("#{target.items}")
    Integer getItems();

}
