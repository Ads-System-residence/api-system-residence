package com.system.residence.controller;

import com.system.residence.response.Login;
import com.system.residence.service.Login.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "Método de autenticação de usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário autenticado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/login/autenticar", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public Object Post(@Valid @RequestBody Login login) throws Exception {
        return loginService.AutenticarUsuarioPorCpf(login).getData();
    }

    @ApiOperation(value = "Método que Recupera o ID da pergunta de segurança do usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ID da pergunta recuperada"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/login/recuperarPerguntaSeguranca/{cpf}", method = RequestMethod.GET, produces="application/json")
    public Object Get(@PathVariable(value = "cpf") String cpf) throws Exception {
        return loginService.obterIdPerguntaSegurancaPorCpf(cpf).getData();
    }
//
//    @RequestMapping(value = "/logar/validarResposta", method = RequestMethod.POST, produces="application/json", consumes="application/json")
//    public Usuario Post(@Valid @RequestBody Usuario usuario) {
//        return  null;;
//    }
//
//    @RequestMapping(value = "/logar/redefinirSenha", method = RequestMethod.POST, produces="application/json", consumes="application/json")
//    public Usuario Put(@Valid @RequestBody Usuario usuario) {
//        return  null;;
//    }

}
