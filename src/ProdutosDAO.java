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
import java.util.List;

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
    	conn = new conectaDAO().connectDB();
    	try {
    		prep = conn.prepareStatement("SELECT * FROM produtos");
			resultset = prep.executeQuery();
			while(resultset.next()) {
				int id = resultset.getInt(1);
				String nome = resultset.getString(2);
				int valor = resultset.getInt(3);
				String status = resultset.getString(4);
				ProdutosDTO produto = new ProdutosDTO();
				produto.setId(id); produto.setNome(nome); produto.setStatus(status); produto.setValor(valor);
				listagem.add(produto);
			}
    	}catch(SQLException e) {
    		System.out.println("Não foi possível listar produtos no banco de dados");
			e.printStackTrace();
    	}
        return listagem;
    }
    
    public void venderProduto(int id) {
    	conn = new conectaDAO().connectDB();
    	try {
			prep = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
			prep.setString(1, "Vendido");
			prep.setInt(2, id);
			prep.executeUpdate();
			JOptionPane.showMessageDialog(null, "O status foi atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar um produto no banco de dados");
			e.printStackTrace();
		}
    }
}

