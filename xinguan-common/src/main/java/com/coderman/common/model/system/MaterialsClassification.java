package com.coderman.common.model.system;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_materials_classification", schema = "xinguan", catalog = "")
public class MaterialsClassification {
    private long id;
    private String materialsClassificationName;
    private int status;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "materials_classification_name")
    public String getMaterialsClassificationName() {
        return materialsClassificationName;
    }

    public void setMaterialsClassificationName(String materialsClassificationName) {
        this.materialsClassificationName = materialsClassificationName;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialsClassification that = (MaterialsClassification) o;
        return id == that.id &&
                status == that.status &&
                Objects.equals(materialsClassificationName, that.materialsClassificationName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, materialsClassificationName, status);
    }
}
