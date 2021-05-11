package com.company.helper;

import com.company.domain.Projeto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersistenciaPostgres implements Persistencia{
    String driver;
    String user;
    String senha;
    String url;

    public PersistenciaPostgres(String driver, String user, String senha, String url) {
        this.driver = driver;
        this.user = user;
        this.senha = senha;
        this.url = url;
    }

    public void conectar() {
        try {
            Class.forName(driver);
            Connection con = null;
            con = (Connection) DriverManager.getConnection(url, user, senha);
            System.out.println("Conexão realizada com sucesso.");
        } catch (ClassNotFoundException ex) {
            System.err.print(ex.getMessage());
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
    }

    @Override
    public Projeto selecionar(String projeto) {
        return null;
    }

    @Override
    public void inserir(Projeto p) {

    }

    @Override
    public void remover(String projeto) {

    }

    @Override
    public void atualizar(String projeto) {

    }

    @Override
    public Projeto[] listar(String projeto) {
        return new Projeto[0];
    }
}
