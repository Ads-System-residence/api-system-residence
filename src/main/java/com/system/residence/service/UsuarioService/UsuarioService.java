package com.system.residence.service.UsuarioService;

import com.system.residence.datasource.domain.Usuario;
import com.system.residence.repository.UsuarioRepository;
import com.system.residence.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

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

    public UsuarioService comInicioQuery(String inicioQuery) {
        this.inicioQuery = inicioQuery;
        return this;
    }

    public UsuarioService comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Usuario obterUsuarioPorCpfString(String cpf) {
        Query query = comInicioQuery(queryObterUsuario).comCpf(cpf).buildQuery();
        List<Object[]> queryResult = query.getResultList();
        List<Usuario> userResponse = gerarResponse(queryResult);

        return userResponse.get(0);
    }

    private List<Usuario> gerarResponse(List<Object[]> queryResult) {
        List<Usuario> resultado = new ArrayList<>();

        for (Object[] item :
                queryResult) {
            Usuario response = new Usuario();
            response.setId(Integer.parseInt(item[0].toString()));
            response.setName(item[1].toString());
            //response.se(item[2].toString());

            resultado.add(response);
        }

        return resultado;
    }

    public Response obterUsuarioPorCpf(String cpf) throws Exception {
        Usuario user = obterUsuarioPorCpfString(cpf);
        return new Response(user, true);
    }

}
