package com.groupeisi.com.company.requests;

import com.groupeisi.com.company.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
public class UserRequest {

    private String action;
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public static UserRequest from(HttpServletRequest request) {
        UserRequest r = new UserRequest();
        r.setAction(request.getParameter("action"));
        r.setId(request.getParameter("id"));
        r.setFirstName(request.getParameter("firstName"));
        r.setLastName(request.getParameter("lastName"));
        r.setEmail(request.getParameter("email"));
        r.setPassword(request.getParameter("password"));
        return r;
    }

    // ── Helpers d'action ─────────────────────────────────────────────

    public boolean isCreate() {
        return action == null || action.isBlank() || "create".equals(action);
    }

    public boolean isEdit() {
        return "edit".equals(action);
    }

    public boolean isDelete() {
        return "delete".equals(action);
    }

    public UserDto toDto() {
        return UserDto.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .build();
    }
}
