package br.com.teste.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.teste.usuario.Usuario;
import br.com.teste.usuario.UsuarioRN;

@ManagedBean(name="usuarioBean")
@RequestScoped

public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destino;
	private Usuario usuarioLogado;
	
	public UsuarioBean(){
		usuarioLogado = new Usuario();
	}
	
	public String login(){
		String irPara = "login";
		UsuarioRN usuarioRN = new UsuarioRN();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		Usuario usuarioNoBD	= usuarioRN.getByNome(usuarioLogado.getNome());
		if(usuarioNoBD==null){
			facesContext.addMessage("erro", new FacesMessage("Login não Autorizado! Usuario não Cadastrado."));
			usuarioLogado = new Usuario();
		}else if(!usuarioLogado.getSenha().equals(usuarioNoBD.getSenha())){
			facesContext.addMessage("erro", new FacesMessage("Login não Autorizado! Senha Incorreta! "));
			usuarioLogado = new Usuario();
		}else{
				HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
				sessaoHttp.setAttribute("usuarioLogado", usuarioLogado);
				irPara ="usuario";
		}
		
		return irPara;
	}
	public String logout(){
		//usuarioLogado = null;
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
	    sessao.invalidate();  
	    return "login";
	}
	
	
	public String novo(){
		destino = "usuarioCadastrado";
		usuario = new Usuario();
		usuario.setStatus(true);
		return "usuario" ;
	}
	public String edit(){
		confirmarSenha= usuario.getSenha();
		return "usuario";
	}
	
	public String active(){
		if(usuario.isStatus()){
			usuario.setStatus(false);
			
		}else{
			usuario.setStatus(true);
		}
		UsuarioRN user= new UsuarioRN();
		user.salvar(usuario);
		
		return null;
		
	}
	
	public String delete(){
		UsuarioRN user = new UsuarioRN();
		user.excluir(usuario);
		
		
		lista = null;
		return null;
		
	}
	
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		String senha = usuario.getSenha();
		if(!senha.equals(confirmarSenha)){
			FacesMessage facesMessage = new FacesMessage("Confirmação de senha incorreta!");
			context.addMessage(null, facesMessage);
			return null;
		}
		
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(usuario);
		return destino;
	}
	
	public List<Usuario> getLista(){
		if(lista==null){
			UsuarioRN usuarioRN = new UsuarioRN();
			lista = usuarioRN.listar();
		}
		return lista;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getConfirmarSenha() {
		return confirmarSenha;
	}
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
	
	
	
	
		

}
