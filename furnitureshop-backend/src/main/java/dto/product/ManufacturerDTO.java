package dto.product;


import dto.shop.RequisiteDTO;

public class ManufacturerDTO {

    private String title;
    private RequisiteDTO requisite;

    public ManufacturerDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RequisiteDTO getRequisite() {
        return requisite;
    }

    public void setRequisite(RequisiteDTO requisite) {
        this.requisite = requisite;
    }
}
