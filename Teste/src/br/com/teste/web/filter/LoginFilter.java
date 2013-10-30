package br.com.teste.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.teste.usuario.Usuario;



public class LoginFilter implements Filter {

	private final static String FILTER_APPLIED = "_security_filter_applied";
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;  
        HttpServletResponse hresp = (HttpServletResponse) response;  
        HttpSession session = hreq.getSession();  
  
        hreq.getPathInfo();  
        String paginaAtual = new String(hreq.getRequestURL());  
        System.out.println(">>>>>>>" + paginaAtual);  
  
        if ((request.getAttribute(FILTER_APPLIED) == null)   
                && paginaAtual != null   
                && (!paginaAtual.endsWith("login.jsf"))   
                && (!paginaAtual.endsWith(".jsf"))) {  
            request.setAttribute(FILTER_APPLIED, Boolean.TRUE);  
            
            String user = null;  
            if (((Usuario) session.getAttribute("usuarioLogado")) != null) {  
                user = ((Usuario) session.getAttribute("usuarioLogado")).getNome();  
            }  
  
            if ((user == null) || (user.equals(""))) {  
                hresp.sendRedirect("login.jsf");  
                
                return;  
                
            }  
            System.out.println(">>>>>> " + user); 
        }  
        
        // entrega a requisição para o proximo filtro    
        
        chain.doFilter(request, response);  
    }  
		
	

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
