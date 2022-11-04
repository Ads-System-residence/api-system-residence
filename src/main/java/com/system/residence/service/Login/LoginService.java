package com.system.residence.service.Login;

import com.system.residence.datasource.domain.Usuario;
import com.system.residence.repository.UsuarioRepository;
import com.system.residence.request.RedefinirSenha;
import com.system.residence.request.ValidPerguntaRespostaUsuario;
import com.system.residence.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UsuarioRepository _usuarioRepository;

    @Autowired
    private EntityManager entityManager;
    private String inicioQuery;
    private String cpf;

    public static final String queryObterUsuario =
            "SELECT * FROM tbsgr001_user " +
                    "WHERE 1=1";

    public Query buildQuery() {
        StringBuilder stringBuilder = new StringBuilder(queryObterUsuario);

        if (cpf != "") {
            stringBuilder.append(" AND CPF = :CPF");
        }

        Query query = entityManager.createNativeQuery(stringBuilder.toString());

        if (cpf != "") {
            query.setParameter("CPF", cpf);
        }

        return query;

    }

    public LoginService comInicioQuery(String inicioQuery) {
        this.inicioQuery = inicioQuery;
        return this;
    }

    public LoginService comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Usuario obterUsuarioPorCpfString(String cpf) {
        Query query = comInicioQuery(queryObterUsuario).comCpf(cpf).buildQuery();
        List<Object[]> queryResult = query.getResultList();
        List<Usuario> userResponse = gerarResponse(queryResult);

        if (userResponse.size() > 0) {
            return userResponse.get(0);
        } else {
            return null;
        }
    }

    private List<Usuario> gerarResponse(List<Object[]> queryResult) {
        List<Usuario> resultado = new ArrayList<>();

        for (Object[] item : queryResult) {
            Usuario response = new Usuario();
            response.setId(Integer.parseInt(item[0].toString()));
            response.setName(item[1].toString());
            response.setEmail(item[2].toString());
            response.setPhone(item[3].toString());
            response.setWhatsapp(item[4].toString());
            response.setCpf(item[5].toString());
            response.setRg(item[6].toString());
            response.setDt_nasc(item[7].toString());
            response.setGenero(Integer.valueOf(item[8].toString()));
            response.setUser_password(item[9].toString());
            response.setRole_acess(Integer.valueOf(item[10].toString()));
            response.setId_pergunta(Integer.valueOf(item[11].toString()));
            response.setResp_seg(item[12].toString());
            response.setToken_acess_system(item[13].toString());
            resultado.add(response);
        }

        return resultado;
    }

    public Response obterIdPerguntaSegurancaPorCpf(String cpf) throws Exception {
        Usuario user = obterUsuarioPorCpfString(cpf);
        if (user != null) {
            RecuperarPergunta pergunta = new RecuperarPergunta();
            pergunta.setIdPerguntaSeguranca(user.getId_pergunta());
            pergunta.setCpf(user.getCpf());
            return new Response(pergunta, true);
        } else {
            Erros erros = new Erros();
            erros.setErro("Usuário não localizado!");
            return new Response<>(erros, true);
        }

    }

    public Response AutenticarUsuarioPorCpf(Login login) throws Exception {
        Usuario usuarioLocalizado = obterUsuarioPorCpfString(login.getCpf());
        if(usuarioLocalizado != null) {
            if (Objects.equals(usuarioLocalizado.getUser_password(), login.getSenha())) {
                return new Response(usuarioLocalizado, true);
            } else {
                Erros erros = new Erros();
                erros.setErro("Senha inválida!");
                return new Response<>(erros, true);
            }
        } else {
            Erros erros = new Erros();
            erros.setErro("Usuário não localizado!");
            return new Response<>(erros, true);
        }
    }

    public Response validarPerguntaSeguranca(ValidPerguntaRespostaUsuario validPerguntaRespostaUsuario) throws Exception {
        Usuario usuario = obterUsuarioPorCpfString(validPerguntaRespostaUsuario.getCpf());
        if (Objects.equals(usuario.getResp_seg(), validPerguntaRespostaUsuario.getRespostaSeguranca())) {
            Success success = new Success();
            success.setSuccess("Resposta válida!");
            return new Response<>(success,true);
        } else {
            Erros erros = new Erros();
            erros.setErro("Resposta inválida!");
            return new Response<>(erros, true);
        }
    }

    public Response redefinirSenha(RedefinirSenha redefinirSenha) throws Exception {
        Optional<Usuario> oldUsuario = _usuarioRepository.getUsuarioByCpf(redefinirSenha.getCpf());
        if (oldUsuario.isPresent()) {
            Usuario usuario = oldUsuario.get();
            usuario.setUser_password(redefinirSenha.getNovaSenha());
            _usuarioRepository.save(usuario);
            return new Response<>(usuario, true);
        } else {
            Erros erros = new Erros();
            erros.setErro("Usuário inválido");
            return new Response<>(erros, true);
        }
    }

}