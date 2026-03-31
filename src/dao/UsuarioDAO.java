package dao;

import java.util.List;
import modelo.Usuario;

public interface UsuarioDAO {
    boolean registrarUsuario(Usuario usuario);
    Usuario iniciarSesion(String nombreUsuario, String contrasena);
    List<Usuario> obtenerUsuarios();
    boolean actualizarUsuario(Usuario usuario);
    boolean eliminarUsuario(int id);
    Usuario buscarPorId(int id);
}