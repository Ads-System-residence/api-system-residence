package com.system.residence.response;

public class Params {

    private String cpf;

    private Integer idPerguntaSeguranca;

    private String respostaSeguranca;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getIdPerguntaSeguranca() {
        return idPerguntaSeguranca;
    }

    public void setIdPerguntaSeguranca(Integer idPerguntaSeguranca) {
        this.idPerguntaSeguranca = idPerguntaSeguranca;
    }

    public String getRespostaSeguranca() {
        return respostaSeguranca;
    }

    public void setRespostaSeguranca(String respostaSeguranca) {
        this.respostaSeguranca = respostaSeguranca;
    }
}
