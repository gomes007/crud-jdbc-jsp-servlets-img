package beans;

public class BeanCursoJsp {

	private Long id;
	private String login;
	private String senha;
	private String nome;
	private String fone;
	
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String ibge;
	private String cep;
	
	private String fotoBase64;
	private String contentType;
	
	
	private String tempfotoUser;
	
	private String curriculoBase64;
	private String curriculocontentType;
	
	private boolean ativo;
	
	private String sexo;
	
	private String perfil; 
	
	
	
	
	public String getTempfotoUser() {
		
		tempfotoUser = "data:" + contentType + ";base64," + fotoBase64;
		
		return tempfotoUser;
	}
	
	
	



	public String getPerfil() {
		return perfil;
	}






	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}






	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}



	public boolean isAtivo() {
		return ativo;
	}



	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}



	public String getCurriculoBase64() {
		return curriculoBase64;
	}


	public void setCurriculoBase64(String curriculoBase64) {
		this.curriculoBase64 = curriculoBase64;
	}


	public String getCurriculocontentType() {
		return curriculocontentType;
	}


	public void setCurriculocontentType(String curriculocontentType) {
		this.curriculocontentType = curriculocontentType;
	}



	public String getFotoBase64() {
		return fotoBase64;
	}

	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
