package com.fusm.catalogs.controller;

import com.fusm.catalogs.model.CatalogItemModel;
import com.fusm.catalogs.model.CatalogItemRequestModel;
import com.fusm.catalogs.model.SearchCatalogItem;
import com.fusm.catalogs.service.ICatalogItemService;
import com.fusm.catalogs.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase expone todos los servicios relacionados con los catalog item
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.CATALOG_ITEM_ROUTE)
public class CatalogItemController {

    @Autowired
    private ICatalogItemService catalogItemService;


    /**
     * Crea un catalog item
     * @param catalogItemRequestModel Modelo para crear un catalog item
     * @return OK
     */
    @PostMapping
    public ResponseEntity<String> createCatalogItem(
            @RequestBody CatalogItemRequestModel catalogItemRequestModel
            ) {
        catalogItemService.createCatalogItem(catalogItemRequestModel);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Obtiene los catalogs item segun el id del catálogo al que pertenecen de forma organizada
     * @param catalogId ID del catálogo
     * @return Lista de catalog item
     */
    @GetMapping("/catalog-id/{catalog-id}")
    public ResponseEntity<List<CatalogItemModel>> getCatalogsItemsByCatalog(
            @PathVariable("catalog-id") Integer catalogId
    ) {
        return ResponseEntity.ok(catalogItemService.getCatalogItemsByCatalog(catalogId));
    }

    /**
     * los catalogs item segun el id del catálogo al que pertenecen
     * @param catalogId ID del catálogo
     * @return Lista de catalog item
     */
    @GetMapping("/all/catalog-id/{catalog-id}")
    public ResponseEntity<List<CatalogItemModel>> getAllCatalogsItemsByCatalog(
            @PathVariable("catalog-id") Integer catalogId
    ) {
        return ResponseEntity.ok(catalogItemService.getAllCatalogItemsByCatalog(catalogId));
    }

    /**
     * Obtiene el catalog item según su ID
     * @param catalogItemId ID del catalog item
     * @return catalog item
     */
    @GetMapping("/{id}")
    public ResponseEntity<CatalogItemModel> getCatalogItemById(
            @PathVariable("id") Integer catalogItemId
    ) {
        return ResponseEntity.ok(catalogItemService.getCatalogItemsById(catalogItemId));
    }

    /**
     * Actualizar catalog item
     * @param catalogItemId ID del catalog item
     * @param catalogItemRequestModel Modelo de catalog item que contiene los campos a actualizar el catalog item
     * @return OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCatalogItem(
            @PathVariable("id") Integer catalogItemId,
            @RequestBody CatalogItemRequestModel catalogItemRequestModel
    ) {
        catalogItemService.updateCatalogItem(catalogItemRequestModel, catalogItemId);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Deshabilita un catalog item
     * @param catalogItemId ID del catalog item
     * @return OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> disableCatalogItem(
            @PathVariable("id") Integer catalogItemId
    ) {
        catalogItemService.disableCatalogItem(catalogItemId);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Obtiene el valor de un catalog item
     * @param catalogItemId ID del catalog item
     * @return valor del catalog item
     */
    @GetMapping("/value/{id}")
    public ResponseEntity<String> getCatalogItemValue(
            @PathVariable("id") Integer catalogItemId
    ) {
        return ResponseEntity.ok(catalogItemService.getCatalogItemValue(catalogItemId));
    }

    /**
     * Obtiene una lista de catalog item segpun su respectivo catálogo con la posibilidad de filtrar
     * @param catalogId ID del catálogo
     * @param searchCatalogItem Parámetros de búsqueda para realizar el filtro
     * @return lista de catalog item
     */
    @PostMapping("/catalog-id/{catalog-id}/filter")
    public ResponseEntity<List<CatalogItemModel>> getCatalogItemsByCatalogWithFilter(
            @PathVariable("catalog-id") Integer catalogId,
            @RequestBody SearchCatalogItem searchCatalogItem
            ) {
        return ResponseEntity.ok(catalogItemService.getCatalogItemsByCatalogWithFilter(searchCatalogItem, catalogId));
    }

    /**
     * Habilita y deshabilita el catalog item
     * @param catalogItemId ID del catalog item
     * @param enabled True o False en caso que se quiera habilitar o deshabilitar el catalog item
     * @return OK
     */
    @DeleteMapping("/{id}/enabled/{enabled}")
    public ResponseEntity<String> enableDisableCatalogItem(
            @PathVariable("id") Integer catalogItemId,
            @PathVariable("enabled") Boolean enabled
    ) {
        catalogItemService.enableDisableCatalogItem(catalogItemId, enabled);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

}
