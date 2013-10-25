package br.com.teste.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.teste.usuario.Usuario;
import br.com.teste.usuario.UsuarioRN;

@ManagedBean(name="usuarioBean")
@RequestScoped

public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destino;
	
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
	
	
	
	
	
		

}
