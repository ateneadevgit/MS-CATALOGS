package com.fusm.catalogs.repository;

import com.fusm.catalogs.entity.CatalogItem;
import com.fusm.catalogs.model.CatalogItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatalogItemRepository extends JpaRepository<CatalogItem, Integer> {

    @Query(value = "select * from catalog_item where catalog_id = :id and enabled = true order by item_order asc ",
            nativeQuery = true)
    List<CatalogItem> findAllByCatalogIdOrderedByCreatedAt(
            @Param("id") Integer catalogId);

    @Query(value = "select * from catalog_item where catalog_id = :id order by id_catalog_item desc ",
            nativeQuery = true)
    List<CatalogItem> findAllByCatalogId(
            @Param("id") Integer catalogId);

}
