package vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modelo.Usuario;
import servicio.UsuarioServicio;

public class EditarUsuarioFrame extends JFrame {

    private JTextField txtNombreUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnGuardar;

    private Usuario usuario;
    private UsuarioServicio usuarioServicio;
    private PrincipalFrame principalFrame;

    public EditarUsuarioFrame(Usuario usuario, PrincipalFrame principalFrame) {
        this.usuario = usuario;
        this.principalFrame = principalFrame;
        this.usuarioServicio = new UsuarioServicio();

        setTitle("Actualizar Usuario");
        setSize(450, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("ACTUALIZAR USUARIO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(110, 20, 250, 30);
        add(lblTitulo);

        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
        lblNombreUsuario.setBounds(30, 70, 140, 25);
        add(lblNombreUsuario);

        txtNombreUsuario = new JTextField(usuario.getNombreUsuario());
        txtNombreUsuario.setBounds(180, 70, 200, 25);
        add(txtNombreUsuario);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 105, 140, 25);
        add(lblNombre);

        txtNombre = new JTextField(usuario.getNombre());
        txtNombre.setBounds(180, 105, 200, 25);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(30, 140, 140, 25);
        add(lblApellido);

        txtApellido = new JTextField(usuario.getApellido());
        txtApellido.setBounds(180, 140, 200, 25);
        add(txtApellido);

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setBounds(30, 175, 140, 25);
        add(lblTelefono);

        txtTelefono = new JTextField(usuario.getTelefono());
        txtTelefono.setBounds(180, 175, 200, 25);
        add(txtTelefono);

        JLabel lblCorreo = new JLabel("Correo electronico:");
        lblCorreo.setBounds(30, 210, 140, 25);
        add(lblCorreo);

        txtCorreo = new JTextField(usuario.getCorreo());
        txtCorreo.setBounds(180, 210, 200, 25);
        add(txtCorreo);

        JLabel lblContrasena = new JLabel("Contrasena:");
        lblContrasena.setBounds(30, 245, 140, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField(usuario.getContrasena());
        txtContrasena.setBounds(180, 245, 200, 25);
        add(txtContrasena);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBounds(140, 305, 160, 30);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> actualizarUsuario());

        setVisible(true);
    }

    private void actualizarUsuario() {
        String nombreUsuario = txtNombreUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        if (nombreUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty()
                || telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        usuario.setNombreUsuario(nombreUsuario);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);

        boolean actualizado = usuarioServicio.actualizarUsuario(usuario);

        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
            principalFrame.cargarUsuarios();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el usuario.");
        }
    }
}