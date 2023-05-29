import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends JFrame {
	
	private JTable produtosTable;
	
	public vendasVIEW() {
		initComponents();
		listarVendas();
	}
	
	private void initComponents() {
		setTitle("Tela de Vendas");
		
		JPanel fundo = new JPanel(new BorderLayout());
		fundo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		produtosTable = new JTable(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {null, null, null, null},
	                {null, null, null, null},
	                {null, null, null, null},
	                {null, null, null, null}
	            },
	            new String [] {
	                "ID", "Nome", "Valor", "Status"
	            }
	        ));
		JScrollPane scroll = new JScrollPane(produtosTable);
		fundo.add(scroll);
		getContentPane().add(fundo);
		
		setSize(500, 470);
	}
	
	private void listarVendas(){
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            
            DefaultTableModel model = (DefaultTableModel) produtosTable.getModel();
            model.setNumRows(0);
            
            ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutosVendidos();
            
            for(int i = 0; i < listagem.size(); i++){
                model.addRow(new Object[]{
                    listagem.get(i).getId(),
                    listagem.get(i).getNome(),
                    listagem.get(i).getValor(),
                    listagem.get(i).getStatus()
                });
            }
        } catch (Exception e) {
        	
        }
    }
	
}
