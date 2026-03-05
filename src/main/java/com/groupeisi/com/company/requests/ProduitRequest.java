package com.groupeisi.com.company.requests;

import com.groupeisi.com.company.dto.ProductDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
public class ProduitRequest {

    private String action;
    private String ref;
    private String name;
    private String stock;
    private String email;

    public static ProduitRequest from(HttpServletRequest request) {
        ProduitRequest r = new ProduitRequest();
        r.setAction(request.getParameter("action"));
        r.setRef(request.getParameter("ref"));
        r.setName(request.getParameter("name"));
        r.setStock(request.getParameter("stock"));
        r.setEmail(request.getParameter("email"));
        return r;
    }

    // ── Helpers d'action ─────────────────────────────────────────────

    public boolean isCreate() {
        return action == null || action.isBlank() || "create".equals(action);
    }

    public boolean isEdit() {
        return "edit".equalsIgnoreCase(action);
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

    // ── Construction du DTO ───────────────────────────────────────────

    public ProductDto toDto() throws Exception {
        return ProductDto.builder()
                .userEmail(email)
                .name(name)
                .ref(ref)
                .stock(Double.parseDouble(stock))
                .build();
    }
}
