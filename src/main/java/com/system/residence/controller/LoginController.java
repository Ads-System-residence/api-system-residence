package com.system.residence.controller;

import com.system.residence.request.RedefinirSenha;
import com.system.residence.request.ValidPerguntaRespostaUsuario;
import com.system.residence.response.Login;
import com.system.residence.service.Login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login/autenticar", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public Object Post(@Valid @RequestBody Login login) throws Exception {
        return loginService.AutenticarUsuarioPorCpf(login).getData();
    }

    @RequestMapping(value = "/login/recuperarPerguntaSeguranca/{cpf}", method = RequestMethod.GET, produces="application/json")
    public Object Get(@PathVariable(value = "cpf") String cpf) throws Exception {
        return loginService.obterIdPerguntaSegurancaPorCpf(cpf).getData();
    }

    @RequestMapping(value = "/login/validarRespostaSeguranca", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public Object Post(@Valid @RequestBody ValidPerguntaRespostaUsuario validPerguntaRespostaUsuario) throws Exception {
        return loginService.validarPerguntaSeguranca(validPerguntaRespostaUsuario).getData();
    }

    @RequestMapping(value = "/logar/redefinirSenha", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    public Object Put(@Valid @RequestBody RedefinirSenha redefinirSenha) throws Exception {
        return loginService.redefinirSenha(redefinirSenha).getData();
    }

}
