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

    public ProductDto toDto() {
        return ProductDto.builder()
                .userEmail(email)
                .name(name)
                .ref(ref)
                .stock(Double.parseDouble(stock))
                .build();
    }
}
