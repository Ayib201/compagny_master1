package com.groupeisi.com.company.requests;

import com.groupeisi.com.company.dto.SaleDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SaleRequest {

    private String action;
    private String id;
    private String dateP;
    private String quantity;
    private String productRef;
    private String email;

    public static SaleRequest from(HttpServletRequest request) {
        SaleRequest r = new SaleRequest();
        r.setAction(request.getParameter("action"));
        r.setId(request.getParameter("id"));
        r.setDateP(request.getParameter("dateP"));
        r.setQuantity(request.getParameter("quantity"));
        r.setProductRef(request.getParameter("product_ref"));
        r.setEmail(request.getParameter("email"));
        return r;
    }

    public boolean isCreate() {
        return action == null || action.isBlank() || "create".equals(action);
    }

    public boolean isUpdate() {
        return "update".equals(action);
    }

    public boolean isDelete() {
        return "delete".equals(action);
    }

    private String validateEmail() throws Exception {
        if (email == null || email.isBlank())
            throw new Exception("Email obligatoire");
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            throw new Exception("Email invalide");
        return email;
    }

    private String validateProductRef() throws Exception {
        if (productRef == null || productRef.isBlank())
            throw new Exception("Référence produit obligatoire");
        return productRef;
    }

    private double validateQuantity() throws Exception {
        if (quantity == null || quantity.isBlank())
            throw new Exception("Quantité obligatoire");
        try {
            double q = Double.parseDouble(quantity);
            if (q <= 0) throw new Exception("La quantité doit être supérieure à zéro");
            return q;
        } catch (NumberFormatException e) {
            throw new Exception("Quantité invalide");
        }
    }

    private Date validateDate() throws Exception {
        if (dateP == null || dateP.isBlank())
            throw new Exception("Date obligatoire");
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateP);
        } catch (ParseException e) {
            throw new Exception("Date invalide");
        }
    }

    public Long validateId() throws Exception {
        if (id == null || id.isBlank())
            throw new Exception("ID obligatoire");
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new Exception("ID invalide");
        }
    }

    public Long parseIdOrNull() throws Exception {
        if (id == null || id.isBlank()) return null;
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new Exception("ID invalide");
        }
    }

    public SaleDto toDto() throws Exception {
        SaleDto dto = SaleDto.builder()
                .userEmail(validateEmail())
                .productRef(validateProductRef())
                .quantity(validateQuantity())
                .dateP(validateDate())
                .build();

        Long parsedId = parseIdOrNull();
        if (parsedId != null) dto.setId(parsedId);

        return dto;
    }
}