package com.fusm.catalogs.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "Catalogs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {

    @Id
    @Column(name = "id_catalog", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer catalogId;

    @NonNull
    @Column(name = "name", length = 100, nullable = false)
    private String catalogName;

    @Column(name = "description", length = 255, nullable = true)
    private String catalogDescription;

    @NonNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @NonNull
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @NonNull
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

}
