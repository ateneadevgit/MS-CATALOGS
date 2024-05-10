package com.fusm.catalogs.service;

import com.fusm.catalogs.model.CatalogItemModel;
import com.fusm.catalogs.model.CatalogItemRequestModel;
import com.fusm.catalogs.model.SearchCatalogItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICatalogItemService {

    void createCatalogItem(CatalogItemRequestModel catalogItemRequestModel);
    List<CatalogItemModel> getCatalogItemsByCatalog(Integer catalogId);
    List<CatalogItemModel> getAllCatalogItemsByCatalog(Integer catalogId);
    List<CatalogItemModel> getCatalogItemsByCatalogWithFilter(SearchCatalogItem searchCatalogItem, Integer catalogId);
    CatalogItemModel getCatalogItemsById(Integer catalogItemId);
    void updateCatalogItem(CatalogItemRequestModel catalogItemRequestModel, Integer catalogItemId);
    void disableCatalogItem(Integer catalogItemId);
    String getCatalogItemValue(Integer catalogItemId);
    void enableDisableCatalogItem(Integer catalogItemId, Boolean enabled);

}
