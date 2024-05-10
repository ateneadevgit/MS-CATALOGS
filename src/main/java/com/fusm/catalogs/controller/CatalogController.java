package com.fusm.catalogs.controller;

import com.fusm.catalogs.dto.CatalogDto;
import com.fusm.catalogs.model.CatalogModel;
import com.fusm.catalogs.model.CatalogRequestModel;
import com.fusm.catalogs.service.ICatalogService;
import com.fusm.catalogs.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone todos los servicios relacionados con los catálogos
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.CATALOG_ROUTE)
public class CatalogController {

    @Autowired
    private ICatalogService catalogService;


    /**
     * Obtiene todos los catalogos
     * @return lista de catálogos
     */
    @GetMapping
    public ResponseEntity<List<CatalogDto>> getCatalogs() {
        return ResponseEntity.ok(catalogService.getCatalogs());
    }

    /**
     * Obtener el catalogo segun su ID
     * @param catalogId ID del catalogo
     * @return Catalogo
     */
    @GetMapping("/{id}")
    public ResponseEntity<CatalogDto> getCatalogById(
            @PathVariable("id") Integer catalogId
    ) {
        return ResponseEntity.ok(catalogService.getCatalogById(catalogId));
    }

    /**
     * Crear un catalogo
     * @param catalogRequestModel Modelo para la creación del catálogo
     * @return OK
     */
    @PostMapping
    public ResponseEntity<String> createCatalog(
            @RequestBody CatalogRequestModel catalogRequestModel
            ) {
        catalogService.createCatalog(catalogRequestModel);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Actualiza el catálogo
     * @param catalogId ID del catálogo
     * @param catalogRequestModel Modelo con la información a actualizar del catálogo
     * @return OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCatalog(
            @PathVariable("id") Integer catalogId,
            @RequestBody CatalogRequestModel catalogRequestModel
    ) {
        catalogService.updateCatalog(catalogRequestModel, catalogId);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Deshabilita un catálogo
     * @param catalogId ID del catálogo
     * @return OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCatalog(
            @PathVariable("id") Integer catalogId
    ) {
        catalogService.disableCatalog(catalogId);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

}
