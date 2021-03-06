package br.com.teste.usuario;

import java.util.List;

public interface UsuarioDAO {
	
	public void salvar(Usuario user);
	public void delete(Usuario user);
	public void alter(Usuario user);
	
	public Usuario getById(Integer idUsuario);
	
	public Usuario getByEmail(String email);
	
	public Usuario getByNome(String nome);
	
	public Usuario getBySenha(String senha);
	
	public List<Usuario>listar();
	
	

}
