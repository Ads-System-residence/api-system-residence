package com.system.residence.datasource.domain;

import javax.persistence.*;

@Entity(name = "tbsgr001_user")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String whatsapp;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private String dt_nasc;

    @Column(nullable = false)
    private Integer genero;

    @Column(nullable = false)
    private String user_password;

    @Column(nullable = false)
    private Integer role_acess;

    @Column(nullable = false)
    private Integer id_pergunta;

    @Column(nullable = false)
    private String resp_seg;

    @Column(nullable = false)
    private String token_acess_system;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(String dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public Integer getGenero() {
        return genero;
    }

    public void setGenero(Integer genero) {
        this.genero = genero;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Integer getRole_acess() {
        return role_acess;
    }

    public void setRole_acess(Integer role_acess) {
        this.role_acess = role_acess;
    }

    public Integer getId_pergunta() {
        return id_pergunta;
    }

    public void setId_pergunta(Integer id_pergunta) {
        this.id_pergunta = id_pergunta;
    }

    public String getResp_seg() {
        return resp_seg;
    }

    public void setResp_seg(String resp_seg) {
        this.resp_seg = resp_seg;
    }

    public String getToken_acess_system() {
        return token_acess_system;
    }

    public void setToken_acess_system(String token_acess_system) {
        this.token_acess_system = token_acess_system;
    }
}
