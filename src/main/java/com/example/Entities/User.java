package com.example.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DNI", nullable = false)
    private String DNI;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fecha_nacimiento;

    @Column(name = "sexo", nullable = false)
    private Character sexo;

    @Column(name = "email")
    private String email;

    @Column(name = "num_tarjeta_sanitaria", nullable = false)
    private Integer num_tarjeta_sanitaria;

    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "alergias", nullable = false)
    private String alergias;

    @Column(name = "contacto1_nombre_completo", nullable = false)
    private String contacto1_nombre_completo;

    @Column(name = "contacto1_telefono", nullable = false)
    private String contacto1_telefono;

    @Column(name = "contacto1_email", nullable = false)
    private String contacto1_email;

    @Column(name = "contacto1_relacion", nullable = false)
    private String contacto1_relacion;

    @Column(name = "contacto1_comentarios")
    private String contacto1_comentarios;

    @Column(name = "contacto2_nombre_completo", nullable = false)
    private String contacto2_nombre_completo;

    @Column(name = "contacto2_telefono", nullable = false)
    private String contacto2_telefono;

    @Column(name = "contacto2_email", nullable = false)
    private String contacto2_email;

    @Column(name = "contacto2_relacion", nullable = false)
    private String contacto2_relacion;

    @Column(name = "contacto2_comentarios")
    private String contacto2_comentarios;

    @Column(name = "fecha_alta", nullable = false)
    private Date fecha_alta;

    @Column(name = "fecha_baja")
    private Date fecha_baja;


    public User(Integer id, String DNI, String contraseña, Boolean activo, String nombre, String apellidos, Date fecha_nacimiento, Character sexo, String email, Integer num_tarjeta_sanitaria, String comentarios, String alergias, String contacto1_nombre_completo, String contacto1_telefono, String contacto1_email, String contacto1_relacion, String contacto1_comentarios, String contacto2_nombre_completo, String contacto2_telefono, String contacto2_email, String contacto2_relacion, String contacto2_comentarios, Date fecha_alta, Date fecha_baja) {
        this.id = id;
        this.DNI = DNI;
        this.contraseña = contraseña;
        this.activo = activo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.email = email;
        this.num_tarjeta_sanitaria = num_tarjeta_sanitaria;
        this.comentarios = comentarios;
        this.alergias = alergias;
        this.contacto1_nombre_completo = contacto1_nombre_completo;
        this.contacto1_telefono = contacto1_telefono;
        this.contacto1_email = contacto1_email;
        this.contacto1_relacion = contacto1_relacion;
        this.contacto1_comentarios = contacto1_comentarios;
        this.contacto2_nombre_completo = contacto2_nombre_completo;
        this.contacto2_telefono = contacto2_telefono;
        this.contacto2_email = contacto2_email;
        this.contacto2_relacion = contacto2_relacion;
        this.contacto2_comentarios = contacto2_comentarios;
        this.fecha_alta = fecha_alta;
        this.fecha_baja = fecha_baja;
    }

    public User() {
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNum_tarjeta_sanitaria() {
        return num_tarjeta_sanitaria;
    }

    public void setNum_tarjeta_sanitaria(Integer num_tarjeta_sanitaria) {
        this.num_tarjeta_sanitaria = num_tarjeta_sanitaria;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getContacto1_nombre_completo() {
        return contacto1_nombre_completo;
    }

    public void setContacto1_nombre_completo(String contacto1_nombre_completo) {
        this.contacto1_nombre_completo = contacto1_nombre_completo;
    }

    public String getContacto1_telefono() {
        return contacto1_telefono;
    }

    public void setContacto1_telefono(String contacto1_telefono) {
        this.contacto1_telefono = contacto1_telefono;
    }

    public String getContacto1_email() {
        return contacto1_email;
    }

    public void setContacto1_email(String contacto1_email) {
        this.contacto1_email = contacto1_email;
    }

    public String getContacto1_relacion() {
        return contacto1_relacion;
    }

    public void setContacto1_relacion(String contacto1_relacion) {
        this.contacto1_relacion = contacto1_relacion;
    }

    public String getContacto1_comentarios() {
        return contacto1_comentarios;
    }

    public void setContacto1_comentarios(String contacto1_comentarios) {
        this.contacto1_comentarios = contacto1_comentarios;
    }

    public String getContacto2_nombre_completo() {
        return contacto2_nombre_completo;
    }

    public void setContacto2_nombre_completo(String contacto2_nombre_completo) {
        this.contacto2_nombre_completo = contacto2_nombre_completo;
    }

    public String getContacto2_telefono() {
        return contacto2_telefono;
    }

    public void setContacto2_telefono(String contacto2_telefono) {
        this.contacto2_telefono = contacto2_telefono;
    }

    public String getContacto2_email() {
        return contacto2_email;
    }

    public void setContacto2_email(String contacto2_email) {
        this.contacto2_email = contacto2_email;
    }

    public String getContacto2_relacion() {
        return contacto2_relacion;
    }

    public void setContacto2_relacion(String contacto2_relacion) {
        this.contacto2_relacion = contacto2_relacion;
    }

    public String getContacto2_comentarios() {
        return contacto2_comentarios;
    }

    public void setContacto2_comentarios(String contacto2_comentarios) {
        this.contacto2_comentarios = contacto2_comentarios;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Date getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(Date fecha_baja) {
        this.fecha_baja = fecha_baja;
    }
}