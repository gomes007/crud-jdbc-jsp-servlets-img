package servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.DaoUsuario;


@WebServlet("/salvarUsuario")
@MultipartConfig
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private DaoUsuario daoUsuario = new DaoUsuario();

    public UsuarioServlet() {
        super();
    }


	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
		
		String acao = request.getParameter("acao");		
		String user = request.getParameter("user");
		
		if (acao.equalsIgnoreCase("delete")) {
			daoUsuario.delete(user);
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);
		} else if (acao.equalsIgnoreCase("editar")){
			BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("user", beanCursoJsp);
			view.forward(request, response);
		} else if(acao.equalsIgnoreCase("listartodos")) {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);
		} else if(acao.equalsIgnoreCase("download")) {
			BeanCursoJsp usuario = daoUsuario.consultar(user);
			if (usuario !=null ) {
				
				String tipo = request.getParameter("tipo");
				
				String contentType = "";
				
				byte[] fileBytes = null;
				
				
				if (tipo.equalsIgnoreCase("imagem")) {
					contentType = usuario.getContentType();
					fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());
					
				} else if(tipo.equalsIgnoreCase("curriculo")) {
					contentType = usuario.getCurriculocontentType();
					fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());					
				}
				
				
				response.setHeader("Content-Disposition", "attachment;filename=arquivo." + contentType.split("\\/")[1]);

				
				/* coloca os bytes em um objeto de entrada para processar */
				InputStream is = new ByteArrayInputStream(fileBytes);
				
				/* inicio da resposta para o navegador */
				int read = 0;
				byte[] bytes = new byte[1024];
				OutputStream os = response.getOutputStream();
				
				while((read = is.read(bytes)) != -1) {
					os.write(bytes, 0, read);					
				}
				
				os.flush();
				os.close();
			}
			
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String acao = request.getParameter("acao");
		
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {					
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
		
		
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");		
		String cep = request.getParameter("cep");
		String rua = request.getParameter("rua");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String ibge = request.getParameter("ibge");
		String sexo = request.getParameter("sexo");
		String perfil = request.getParameter("perfil");
		

		BeanCursoJsp usuario = new BeanCursoJsp();
		usuario.setId((id != null && !id.isEmpty()) ? Long.parseLong(id) : null);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		usuario.setFone(fone);
		usuario.setCep(cep);
		usuario.setRua(rua);
		usuario.setBairro(bairro);
		usuario.setCidade(cidade);
		usuario.setEstado(estado);
		usuario.setIbge(ibge);
		usuario.setSexo(sexo);
		usuario.setPerfil(perfil);
		
		if (request.getParameter("ativo") != null
				&& request.getParameter("ativo").equalsIgnoreCase("on")) {
			usuario.setAtivo(true);
		} else {
			usuario.setAtivo(false);
		}
		

		
		
		try {
			
			/* inicio file upload e pdf  */
			
			if (ServletFileUpload.isMultipartContent(request)) {
				
				Part imagemFoto = request.getPart("foto");
				
								
				if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
										
					String fotoBase64 = new Base64().encodeBase64String(convertStringParabyte(imagemFoto.getInputStream()));
					
					usuario.setFotoBase64(fotoBase64);
					usuario.setContentType(imagemFoto.getContentType());					
				} else {					
					usuario.setFotoBase64(request.getParameter("fotoTemp"));
					usuario.setContentType(request.getParameter("contTemp"));
				}
				
				
				/* processar pdf */
				
				Part curriculoPdf = request.getPart("curriculo");
				
				if (curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {
					
					
					String curriculoBase64 = new Base64().encodeBase64String(convertStringParabyte(curriculoPdf.getInputStream()));
					
					usuario.setCurriculoBase64(curriculoBase64);
					usuario.setCurriculocontentType(curriculoPdf.getContentType());
					
				} else {
					usuario.setCurriculoBase64(request.getParameter("cvTemp"));
					usuario.setCurriculocontentType(request.getParameter("cvcontTemp"));
				}
				
				
			}
			
			/* fim file upload e pdf  */
			 
			 
			
			String msg = null;
			boolean podeInserir = true;
			
			if (login == null || login.isEmpty()) {
				msg = "o login deve ser informado!";
				podeInserir = false;
								
			} else if (nome == null || nome.isEmpty()) {
				msg = "o nome deve ser informado!";
				podeInserir = false;
								
			} else if (fone == null || fone.isEmpty()) {
				msg = "o fone deve ser informado!";
				podeInserir = false;
								
			} else if (senha == null || senha.isEmpty()) {
				msg = "a senha deve ser informado!";
				podeInserir = false;
								
			} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
				msg = "ja existe usuario com esse login!";
				podeInserir = false;
			} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
				msg = "ja existe usuario com essa senha!";
				podeInserir = false;
			}
			
			if (msg != null) {
				request.setAttribute("msg", msg);
				
			} else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
				daoUsuario.salvar(usuario);
				
			} else if(id != null && !id.isEmpty() && podeInserir) {
				daoUsuario.atualizar(usuario);
			}
			
			if (!podeInserir) {
				request.setAttribute("user", usuario);
			}
			
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			request.setAttribute("msg", "salvo com sucesso!");
			view.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}

	}
	
	/* converte a entrada de fluxo de dados da imagem para array de byte[] */	
	private static byte[] convertStringParabyte(InputStream imagem) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();			
		}
		
		return baos.toByteArray();
	}

}
