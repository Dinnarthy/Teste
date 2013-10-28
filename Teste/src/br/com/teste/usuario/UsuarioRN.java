package br.com.teste.usuario;

import java.util.List;
import br.com.teste.usuario.DAOFactory;

public class UsuarioRN {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioRN(){
		usuarioDAO = DAOFactory.criarUsuarioDAO();
	}
	
	public Usuario getById(Integer idUsuario){
		return usuarioDAO.getById(idUsuario);
	}
	
	public Usuario getByEmail(String email){
		return usuarioDAO.getByEmail(email);
	}
	public Usuario getByNome(String nome){
		return usuarioDAO.getByNome(nome);
	}

	public Usuario getBySenha(String senha){
		return usuarioDAO.getBySenha(senha);
	}
	public void salvar(Usuario usuario){
		Integer idUsuario = usuario.getIdUsuario();
		if(idUsuario == 0 || idUsuario == null){
			usuarioDAO.salvar(usuario);	
		}else{
			usuarioDAO.alter(usuario);
		}
		
	}
	
	public void excluir(Usuario usuario){
		usuarioDAO.delete(usuario);
	}
	
	public List<Usuario> listar(){
		return usuarioDAO.listar();
		
	}
	
	

}
