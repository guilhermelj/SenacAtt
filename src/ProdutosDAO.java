/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        try {
			prep = conn.prepareStatement("INSERT INTO produtos(nome, valor, status) VALUES(?,?,?)");
			prep.setString(1, produto.getNome());
			prep.setInt(2, produto.getValor());
			prep.setString(3, produto.getStatus());
			prep.executeUpdate();
			System.out.println("Produto cadastrado.");
			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Não foi possível cadastrar um produto no banco de dados.");
			e.printStackTrace();
		}
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
    	
        return listagem;
    }
    
    
    
        
}

