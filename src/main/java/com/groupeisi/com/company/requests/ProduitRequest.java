package com.groupeisi.com.company.requests;

import com.groupeisi.com.company.dto.ProductDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
public class ProduitRequest {

    private String action;
    private String id;
    private String name;
    private String ref;
    private String stock;
    private String email;

    public static ProduitRequest from(HttpServletRequest request) {
        ProduitRequest r = new ProduitRequest();
        r.setAction(request.getParameter("action"));
        r.setId(request.getParameter("id"));
        r.setName(request.getParameter("name"));
        r.setRef(request.getParameter("ref"));
        r.setStock(request.getParameter("stock"));
        r.setEmail(request.getParameter("email"));
        return r;
    }

    // ── Helpers d'action ─────────────────────────────────────────────

    public boolean isCreate() {
        return action == null || action.isBlank() || "create".equals(action);
    }

    public boolean isUpdate() {
        return "update".equals(action);
    }

    public boolean isDelete() {
        return "delete".equals(action);
    }

    // ── Validations individuelles ─────────────────────────────────────

    private String validateEmail() throws Exception {
        if (email == null || email.isBlank())
            throw new Exception("Email obligatoire");
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            throw new Exception("Email invalide");
        return email;
    }

    private String validateName() throws Exception {
        if (name == null || name.isBlank())
            throw new Exception("Nom du produit obligatoire");
        return name;
    }

    private String validateRef() throws Exception {
        if (ref == null || ref.isBlank())
            throw new Exception("Référence produit obligatoire");
        return ref;
    }

    private double validateStock() throws Exception {
        if (stock == null || stock.isBlank())
            throw new Exception("Stock obligatoire");
        try {
            double s = Double.parseDouble(stock);
            if (s < 0) throw new Exception("Le stock ne peut pas être négatif");
            return s;
        } catch (NumberFormatException e) {
            throw new Exception("Stock invalide");
        }
    }

    public String validateId() throws Exception {
        if (id == null || id.isBlank())
            throw new Exception("ID obligatoire");
        return id;
    }

    public String parseIdOrNull() {
        if (id == null || id.isBlank()) return null;
        return id;
    }

    // ── Construction du DTO ───────────────────────────────────────────

    public ProductDto toDto() throws Exception {
        ProductDto dto = ProductDto.builder()
                .userEmail(validateEmail())
                .name(validateName())
                .ref(validateRef())
                .stock(validateStock())
                .build();

        String parsedId = parseIdOrNull();
        if (parsedId != null) dto.setRef(parsedId);

        return dto;
    }
}
