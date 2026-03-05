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

    public boolean isEdit() {
        return "edit".equals(action);
    }

    public boolean isDelete() {
        return "delete".equals(action);
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
                .userEmail(email)
                .productRef(productRef)
                .quantity(Double.parseDouble(quantity))
                .dateP(dateP)
                .build();

        Long parsedId = parseIdOrNull();
        if (parsedId != null) dto.setId(parsedId);

        return dto;
    }
}