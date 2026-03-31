package servicio;

import java.util.List;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import modelo.Usuario;

public class UsuarioServicio {

    private UsuarioDAO usuarioDAO;

    public UsuarioServicio() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    public boolean registrarUsuario(Usuario usuario) {
        return usuarioDAO.registrarUsuario(usuario);
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        return usuarioDAO.iniciarSesion(nombreUsuario, contrasena);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioDAO.obtenerUsuarios();
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizarUsuario(usuario);
    }

    public boolean eliminarUsuario(int id) {
        return usuarioDAO.eliminarUsuario(id);
    }

    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }
}