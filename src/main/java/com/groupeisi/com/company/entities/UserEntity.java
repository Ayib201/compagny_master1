package com.groupeisi.com.company.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "firstName", length = 250, nullable = false)
	private String firstName;
	@Column(name = "lastName", length = 200, nullable = false)
	private String lastName;
	@Column(name = "email", length = 250, nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
}
