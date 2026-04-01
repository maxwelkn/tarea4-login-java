package dao;

import java.util.List;
import modelo.Usuario;

public interface UsuarioDAO {

    // Registra un nuevo usuario en la base de datos
    boolean registrarUsuario(Usuario usuario);

    // Valida el inicio de sesion con usuario y contrasena
    Usuario iniciarSesion(String nombreUsuario, String contrasena);

    // Obtiene todos los usuarios registrados
    List<Usuario> obtenerUsuarios();

    // Actualiza los datos de un usuario
    boolean actualizarUsuario(Usuario usuario);

    // Elimina un usuario por su id
    boolean eliminarUsuario(int id);

    // Busca un usuario por su id
    Usuario buscarPorId(int id);
}