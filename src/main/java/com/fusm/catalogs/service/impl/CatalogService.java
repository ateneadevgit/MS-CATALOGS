package com.fusm.catalogs.service.impl;

import com.fusm.catalogs.dto.CatalogDto;
import com.fusm.catalogs.entity.Catalog;
import com.fusm.catalogs.model.CatalogModel;
import com.fusm.catalogs.model.CatalogRequestModel;
import com.fusm.catalogs.repository.ICatalogRepository;
import com.fusm.catalogs.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogService implements ICatalogService {

    @Autowired
    private ICatalogRepository catalogRepository;



    @Override
    public void createCatalog(CatalogRequestModel catalogRequestModel) {
        catalogRepository.save(Catalog.builder()
                .catalogName(catalogRequestModel.getName())
                .catalogDescription(catalogRequestModel.getDescription())
                .createdAt(new Date())
                .createdBy(catalogRequestModel.getCreatedBy())
                .enabled(true)
                .build()
        );
    }

    @Override
    public List<CatalogDto> getCatalogs() {
        return catalogRepository.findAllOrderedByCreatedAt();
    }

    @Override
    public CatalogDto getCatalogById(Integer catalogId) {
        return catalogRepository.findCatalogById(catalogId).get(0);
    }

    @Override
    public void updateCatalog(CatalogRequestModel catalogRequestModel, Integer catalogId) {
        Optional<Catalog> catalogOptional = catalogRepository.findById(catalogId);
        Catalog catalog;
        if (catalogOptional.isPresent()) {
            catalog = catalogOptional.get();
            catalog.setCatalogName(catalogRequestModel.getName());
            catalog.setCatalogDescription(catalogRequestModel.getDescription());
            catalogRepository.save(catalog);
        }
    }

    @Override
    public void disableCatalog(Integer catalogId) {
        Optional<Catalog> catalogOptional = catalogRepository.findById(catalogId);
        Catalog catalog;
        if (catalogOptional.isPresent()) {
            catalog = catalogOptional.get();
            catalog.setEnabled(false);
            catalogRepository.save(catalog);
        }
    }

}
