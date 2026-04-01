package servicio;

import java.util.List;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import modelo.Usuario;

public class UsuarioServicio {

    // Objeto que conecta el servicio con la capa DAO
    private UsuarioDAO usuarioDAO;

    public UsuarioServicio() {
        // Inicializa la implementacion del DAO
        usuarioDAO = new UsuarioDAOImpl();
    }

    // Registra un nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        return usuarioDAO.registrarUsuario(usuario);
    }

    // Valida el inicio de sesion
    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        return usuarioDAO.iniciarSesion(nombreUsuario, contrasena);
    }

    // Obtiene la lista de usuarios registrados
    public List<Usuario> obtenerUsuarios() {
        return usuarioDAO.obtenerUsuarios();
    }

    // Actualiza los datos de un usuario
    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizarUsuario(usuario);
    }

    // Elimina un usuario por su id
    public boolean eliminarUsuario(int id) {
        return usuarioDAO.eliminarUsuario(id);
    }

    // Busca un usuario por su id
    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }
}