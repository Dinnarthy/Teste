package br.com.teste.usuario;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;



public class UsuarioDAOHibernate implements UsuarioDAO {
	
	private Session session;
	
	public void setSession(Session s){
		session = s;
	}
		

	public void salvar(Usuario user) {
		session.save(user);

	}

	public void delete(Usuario user) {
		session.delete(user);

	}

	public void alter(Usuario user) {
		session.update(user);

	}

	public Usuario getById(Integer idUsuario) {
		return (Usuario) session.get(Usuario.class, idUsuario);
	}

	public Usuario getByEmail(String email) {
		String hql = "SELECT u  FROM Usuario u WHERE u.email= :param";
		Query consulta = session.createQuery(hql);
		consulta.setString("param", email);
		return (Usuario) consulta.uniqueResult();
	}

	public List<Usuario> listar() {
		return session.createCriteria(Usuario.class).list();
	}

}
