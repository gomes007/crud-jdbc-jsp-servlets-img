package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;


@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private DaoProduto daoProduto = new DaoProduto();
	
	
	
   
    public ProdutoServlet() {
        super();
    }
    
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			
		String acao = request.getParameter("acao");		
		String produto = request.getParameter("produto");
		
		if (acao.equalsIgnoreCase("delete")) {
			daoProduto.delete(produto);
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			view.forward(request, response);
			
		} else if (acao.equalsIgnoreCase("editar")){
			BeanProduto beanProduto = daoProduto.consultar(produto);
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produto", beanProduto);
			request.setAttribute("categorias", daoProduto.listaCategoria());
			view.forward(request, response);
			
		} else if(acao.equalsIgnoreCase("listartodos")) {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			request.setAttribute("categorias", daoProduto.listaCategoria());
			view.forward(request, response);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {					
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");
			String categoria = request.getParameter("categoria_id");
			
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			beanProduto.setNome(nome);
			beanProduto.setQuantidade(Double.parseDouble(quantidade));
			beanProduto.setValor(Double.parseDouble(valor));
			beanProduto.setCategoria_id(Long.parseLong(categoria));
			
			try {
				
				String msg = null;
				boolean podeInserir = true;
				
				if (nome == null || nome.isEmpty()) {
					msg = "o nome precisa ser inserido!";
					podeInserir = false;
					
				} else if (quantidade == null || quantidade.isEmpty()) {
					msg = "a quantidade precisa ser inserida!";
					podeInserir = false;
					
				} else if (valor == null || valor.isEmpty()) {
					msg = "o valor precisa ser inserido!";
					podeInserir = false;
					
				} else if (id == null || id.isEmpty() && !daoProduto.validarNome(nome)) {
					msg = "ja existe produto com esse nome!";
					podeInserir = false;
				}				
				
				if (msg != null) {
					request.setAttribute("msg", msg);
					
				}
				

				if (id == null || id.isEmpty() && daoProduto.validarNome(nome) && podeInserir) {
					daoProduto.salvar(beanProduto);
					
				} else if(id != null && !id.isEmpty() && podeInserir) {
					daoProduto.atualizar(beanProduto);
				}
				
				
				
				if (!podeInserir) {
					request.setAttribute("produto", beanProduto);
				}
				
				
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				request.setAttribute("categorias", daoProduto.listaCategoria());
				view.forward(request, response);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

}
