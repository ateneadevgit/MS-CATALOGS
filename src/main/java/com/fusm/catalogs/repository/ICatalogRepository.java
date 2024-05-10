package com.fusm.catalogs.repository;

import com.fusm.catalogs.dto.CatalogDto;
import com.fusm.catalogs.entity.Catalog;
import com.fusm.catalogs.model.CatalogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatalogRepository extends JpaRepository<Catalog, Integer> {

    @Query(value = "SELECT " +
            "id_catalog, " +
            "description, " +
            "name, " +
            "created_at, " +
            "created_by, " +
            "enabled," +
            "( " +
            "   select count(*) from catalog_item where catalog_id = id_catalog " +
            ") as items " +
            "FROM public.catalogs " +
            "order by id_catalog asc ",
        nativeQuery = true)
    List<CatalogDto> findAllOrderedByCreatedAt();

    @Query(value = "SELECT " +
            "id_catalog, " +
            "description, " +
            "name, " +
            "created_at, " +
            "created_by, " +
            "enabled," +
            "( " +
            "   select count(*) from catalog_item where catalog_id = id_catalog " +
            ") as items " +
            "FROM public.catalogs " +
            "where id_catalog = :id " +
            "order by id_catalog asc ",
            nativeQuery = true)
    List<CatalogDto> findCatalogById(
            @Param("id") Integer catalogId
    );
}
