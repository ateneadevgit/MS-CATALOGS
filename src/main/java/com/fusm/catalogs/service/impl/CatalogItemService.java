package com.fusm.catalogs.service.impl;

import com.fusm.catalogs.entity.Catalog;
import com.fusm.catalogs.entity.CatalogItem;
import com.fusm.catalogs.model.CatalogItemModel;
import com.fusm.catalogs.model.CatalogItemRequestModel;
import com.fusm.catalogs.model.SearchCatalogItem;
import com.fusm.catalogs.repository.ICatalogItemRepository;
import com.fusm.catalogs.repository.ICatalogRepository;
import com.fusm.catalogs.service.ICatalogItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatalogItemService implements ICatalogItemService {

    @Autowired
    private ICatalogItemRepository catalogItemRepository;

    @Autowired
    private ICatalogRepository catalogRepository;


    @Override
    public void createCatalogItem(CatalogItemRequestModel catalogItemRequestModel) {
        Catalog catalog = null;
        Optional<Catalog> catalogOptional = catalogRepository.findById(catalogItemRequestModel.getCatalogId());
        if (catalogOptional.isPresent()) catalog = catalogOptional.get();
        catalogItemRepository.save(CatalogItem.builder()
                .catalogItemName(catalogItemRequestModel.getName())
                .catalogItemDescription(catalogItemRequestModel.getDescription())
                .createdAt(new Date())
                .createdBy(catalogItemRequestModel.getCreatedBy())
                .enabled(true)
                .catalogId(catalog)
                .itemOrder(catalogItemRequestModel.getOrder())
                .build()
        );
    }

    @Override
    public List<CatalogItemModel> getCatalogItemsByCatalog(Integer catalogId) {
        return catalogItemRepository.findAllByCatalogIdOrderedByCreatedAt(catalogId).stream().map(
                        catalogItem ->
                                CatalogItemModel.builder()
                                        .catalogItemId(catalogItem.getCatalogItemId())
                                        .catalogItemDescription(catalogItem.getCatalogItemDescription())
                                        .catalogItemName(catalogItem.getCatalogItemName())
                                        .createdAt(catalogItem.getCreatedAt())
                                        .enabled(catalogItem.getEnabled())
                                        .catalogId(catalogItem.getCatalogId().getCatalogId())
                                        .order(catalogItem.getItemOrder())
                                        .build()
                )
                .toList();
    }

    @Override
    public List<CatalogItemModel> getAllCatalogItemsByCatalog(Integer catalogId) {
        return catalogItemRepository.findAllByCatalogId(catalogId).stream().map(
                        catalogItem ->
                                CatalogItemModel.builder()
                                        .catalogItemId(catalogItem.getCatalogItemId())
                                        .catalogItemDescription(catalogItem.getCatalogItemDescription())
                                        .catalogItemName(catalogItem.getCatalogItemName())
                                        .createdAt(catalogItem.getCreatedAt())
                                        .enabled(catalogItem.getEnabled())
                                        .catalogId(catalogItem.getCatalogId().getCatalogId())
                                        .order(catalogItem.getItemOrder())
                                        .build()
                )
                .toList();
    }

    @Override
    public List<CatalogItemModel> getCatalogItemsByCatalogWithFilter(SearchCatalogItem searchCatalogItem, Integer catalogId) {
        List<CatalogItem> catalogItemList = catalogItemRepository.findAllByCatalogIdOrderedByCreatedAt(catalogId);
        if (searchCatalogItem.getKeyWord() != null)
            catalogItemList = catalogItemList.stream()
                    .filter(catalogItem -> catalogItem.getCatalogItemName().toLowerCase().contains(searchCatalogItem.getKeyWord().toLowerCase()))
                    .collect(Collectors.toList());
        return catalogItemList.stream()
                .map(
                        catalogItem ->
                                CatalogItemModel.builder()
                                        .catalogItemId(catalogItem.getCatalogItemId())
                                        .catalogItemDescription(catalogItem.getCatalogItemDescription())
                                        .catalogItemName(catalogItem.getCatalogItemName())
                                        .createdAt(catalogItem.getCreatedAt())
                                        .enabled(catalogItem.getEnabled())
                                        .catalogId(catalogItem.getCatalogId().getCatalogId())
                                        .build()
                )
                .toList();
    }

    @Override
    public CatalogItemModel getCatalogItemsById(Integer catalogItemId) {
        Optional<CatalogItem> catalogItemOptional = catalogItemRepository.findById(catalogItemId);
        CatalogItem catalogItem;
        CatalogItemModel model = null;
        if (catalogItemOptional.isPresent()) {
            catalogItem = catalogItemOptional.get();
            model = CatalogItemModel.builder()
                    .catalogItemId(catalogItem.getCatalogItemId())
                    .catalogItemDescription(catalogItem.getCatalogItemDescription())
                    .catalogItemName(catalogItem.getCatalogItemName())
                    .createdAt(catalogItem.getCreatedAt())
                    .enabled(catalogItem.getEnabled())
                    .catalogId(catalogItem.getCatalogId().getCatalogId())
                    .order(catalogItem.getItemOrder())
                    .build();
        }
        return model;
    }

    @Override
    public void updateCatalogItem(CatalogItemRequestModel catalogItemRequestModel, Integer catalogItemId) {
        Optional<CatalogItem> catalogItemOptional = catalogItemRepository.findById(catalogItemId);
        CatalogItem catalogItem;
        if (catalogItemOptional.isPresent()) {
            catalogItem = catalogItemOptional.get();
            catalogItem.setCatalogItemName(catalogItemRequestModel.getName());
            catalogItem.setCatalogItemDescription(catalogItemRequestModel.getDescription());
            catalogItem.setItemOrder(catalogItemRequestModel.getOrder());
            Catalog catalog = null;
            Optional<Catalog> catalogOptional = catalogRepository.findById(catalogItemRequestModel.getCatalogId());
            if (catalogOptional.isPresent()) catalog = catalogOptional.get();
            catalogItem.setCatalogId(catalog);
            catalogItemRepository.save(catalogItem);
        }
    }

    @Override
    public void disableCatalogItem(Integer catalogItemId) {
        Optional<CatalogItem> catalogItemOptional = catalogItemRepository.findById(catalogItemId);
        CatalogItem catalogItem;
        if (catalogItemOptional.isPresent()) {
            catalogItem = catalogItemOptional.get();
            catalogItem.setEnabled(false);
            catalogItemRepository.save(catalogItem);
        }
    }

    @Override
    public String getCatalogItemValue(Integer catalogItemId) {
        String response = "";
        Optional<CatalogItem> catalogItemOptional = catalogItemRepository.findById(catalogItemId);
        if (catalogItemOptional.isPresent()) response = catalogItemOptional.get().getCatalogItemName();
        return response;
    }

    @Override
    public void enableDisableCatalogItem(Integer catalogItemId, Boolean enabled) {
        Optional<CatalogItem> catalogItemOptional = catalogItemRepository.findById(catalogItemId);
        CatalogItem catalogItem;
        if (catalogItemOptional.isPresent()) {
            catalogItem = catalogItemOptional.get();
            catalogItem.setEnabled(enabled);
            catalogItemRepository.save(catalogItem);
        }
    }

}
