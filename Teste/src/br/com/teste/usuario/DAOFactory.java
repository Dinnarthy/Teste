package br.com.teste.usuario;


import br.com.teste.util.HibernateUtil;

public class DAOFactory {
	
	public static UsuarioDAO criarUsuarioDAO(){
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSf().getCurrentSession());
		return usuarioDAO;
		
		
		
	}

}
