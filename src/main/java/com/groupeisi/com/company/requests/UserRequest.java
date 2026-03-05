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

    private String validateFirstName() throws Exception {
        if (firstName == null || firstName.isBlank())
            throw new Exception("Prénom obligatoire");
        return firstName;
    }

    private String validateLastName() throws Exception {
        if (lastName == null || lastName.isBlank())
            throw new Exception("Nom obligatoire");
        return lastName;
    }

    private String validatePassword() throws Exception {
        if (password == null || password.isBlank())
            throw new Exception("Mot de passe obligatoire");
        if (password.length() < 6)
            throw new Exception("Le mot de passe doit contenir au moins 6 caractères");
        return password;
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

    public UserDto toDto() throws Exception {
        return new UserDto(
                validateEmail(),
                validateFirstName(),
                validateLastName(),
                validatePassword()
        );
    }
}
