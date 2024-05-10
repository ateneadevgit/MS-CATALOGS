package com.fusm.catalogs.service;

import com.fusm.catalogs.dto.CatalogDto;
import com.fusm.catalogs.model.CatalogModel;
import com.fusm.catalogs.model.CatalogRequestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICatalogService {
    void createCatalog(CatalogRequestModel catalogRequestModel);
    List<CatalogDto> getCatalogs();
    CatalogDto getCatalogById(Integer catalogId);
    void updateCatalog(CatalogRequestModel catalogRequestModel, Integer catalogId);
    void disableCatalog(Integer catalogId);
}
