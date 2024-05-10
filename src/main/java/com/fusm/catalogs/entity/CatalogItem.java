package com.fusm.catalogs.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "catalog_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogItem {

    @Id
    @Column(name = "id_catalog_item", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer catalogItemId;

    @NonNull
    @Column(name = "name", length = 100, nullable = false)
    private String catalogItemName;

    @Column(name = "description", length = 255, nullable = true)
    private String catalogItemDescription;

    @Column(name = "item_order", nullable = true)
    private Integer itemOrder;

    @NonNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "catalog_id", nullable = false)
    private Catalog catalogId;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @NonNull
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

}
