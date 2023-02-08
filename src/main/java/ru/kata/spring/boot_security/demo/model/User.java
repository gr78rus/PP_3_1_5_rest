package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @NotEmpty(message = "Name should not be empty")
   @Size(min=2, max=30, message = "Name should be between 2 and 30 characters")
   @Column(name = "name")
   private String name;

   @NotEmpty(message = "Name should not be empty")
   @Size(min=2, max=30, message = "Name should be between 2 and 30 characters")
   @Column(name = "surname")
   private String surname;

   @NotEmpty(message = "Email should not be empty")
   @Email(message = "Email should be valid")
   @Column(name = "email")
   private String email;

   public User() {
   }

   public User(String name, String surname, String email) {
      this.name = name;
      this.surname = surname;
      this.email = email;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSurname() {
      return surname;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", surname='" + surname + '\'' +
              ", email='" + email + '\'' +
              '}';
   }
}