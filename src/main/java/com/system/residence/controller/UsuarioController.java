package com.system.residence.controller;

import com.system.residence.datasource.domain.Usuario;
import com.system.residence.repository.UsuarioRepository;
import com.system.residence.service.UsuarioService.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
public class UsuarioController {
    @Autowired
    private UsuarioRepository _usuarioRepository;
    
    @Autowired
    private UsuarioService _usuarioService;

    @ApiOperation(value = "Método que Retorna uma lista de usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de usuarios"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/usuario/getAll", method = RequestMethod.GET, produces="application/json")
    public List<Usuario> Get() {
        return _usuarioRepository.findAll();
    }

    @RequestMapping(value = "/usuario/getById/{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Usuario> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Usuario> usuario = _usuarioRepository.getUsuarioById(id);
        if(usuario.isPresent())
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/usuario/getByCpf/{cpf}", method = RequestMethod.GET, produces = "application/json")
    public Object getByCpf(@PathVariable(value = "cpf") String cpf) throws Exception {
        return _usuarioService.obterUsuarioPorCpf(cpf).getData();
    }

    @ApiOperation(value = "Método que inclui um novo usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario Cadastrado com sucesso!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })

    @RequestMapping(value = "/usuario/incluirUsuario", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public Usuario Post(@Valid @RequestBody Usuario usuario) {
        return _usuarioRepository.save(usuario);
    }

    @RequestMapping(value = "/usuario/alterarUsuario/{id}", method =  RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<Usuario> Put(@PathVariable(value = "id") Integer id, @Valid @RequestBody Usuario newUsuario)
    {
        Optional<Usuario> oldUsuario = _usuarioRepository.findById(id);
        if(oldUsuario.isPresent()){
            Usuario usuario = oldUsuario.get();
            usuario.setName(newUsuario.getName());
            usuario.setCpf(newUsuario.getCpf());
            _usuarioRepository.save(usuario);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/usuario/excluirUsuario/{id}", method = RequestMethod.DELETE, produces="application/json")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Usuario> usuario = _usuarioRepository.deleteUsuarioById(id);
        if(usuario.isPresent()){
            _usuarioRepository.delete(usuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
