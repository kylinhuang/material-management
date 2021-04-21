package com.coderman.common.model.system;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_materials_type", schema = "xinguan", catalog = "")
public class MaterialsType {
    private long id;
    private String materialName;
    private String specificationModel;
    private String unit;
    private long materialsClassificationId;
    private String materialsClassificationName;
    private String remarks;
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
    @Column(name = "material_name")
    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Basic
    @Column(name = "specification_model")
    public String getSpecificationModel() {
        return specificationModel;
    }

    public void setSpecificationModel(String specificationModel) {
        this.specificationModel = specificationModel;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "materials_classification_id")
    public long getMaterialsClassificationId() {
        return materialsClassificationId;
    }

    public void setMaterialsClassificationId(long materialsClassificationId) {
        this.materialsClassificationId = materialsClassificationId;
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
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        MaterialsType that = (MaterialsType) o;
        return id == that.id &&
                materialsClassificationId == that.materialsClassificationId &&
                status == that.status &&
                Objects.equals(materialName, that.materialName) &&
                Objects.equals(specificationModel, that.specificationModel) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(materialsClassificationName, that.materialsClassificationName) &&
                Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, materialName, specificationModel, unit, materialsClassificationId, materialsClassificationName, remarks, status);
    }
}
