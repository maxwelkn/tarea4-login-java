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

public class RegistroFrame extends JFrame {

    private JTextField txtNombreUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmarContrasena;
    private JButton btnRegistrar;
    private JButton btnVolver;

    private UsuarioServicio usuarioServicio;
    private PrincipalFrame principalFrame;

    public RegistroFrame() {
        this(null);
    }

    public RegistroFrame(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        usuarioServicio = new UsuarioServicio();

        setTitle("Registro de Usuario");
        setSize(450, 430);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("REGISTRO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(160, 20, 150, 30);
        add(lblTitulo);

        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
        lblNombreUsuario.setBounds(30, 70, 140, 25);
        add(lblNombreUsuario);

        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(180, 70, 200, 25);
        add(txtNombreUsuario);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 105, 140, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(180, 105, 200, 25);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(30, 140, 140, 25);
        add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(180, 140, 200, 25);
        add(txtApellido);

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setBounds(30, 175, 140, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(180, 175, 200, 25);
        add(txtTelefono);

        JLabel lblCorreo = new JLabel("Correo electronico:");
        lblCorreo.setBounds(30, 210, 140, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(180, 210, 200, 25);
        add(txtCorreo);

        JLabel lblContrasena = new JLabel("Contrasena:");
        lblContrasena.setBounds(30, 245, 140, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(180, 245, 200, 25);
        add(txtContrasena);

        JLabel lblConfirmarContrasena = new JLabel("Confirmar Contrasena:");
        lblConfirmarContrasena.setBounds(30, 280, 150, 25);
        add(lblConfirmarContrasena);

        txtConfirmarContrasena = new JPasswordField();
        txtConfirmarContrasena.setBounds(180, 280, 200, 25);
        add(txtConfirmarContrasena);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(90, 330, 120, 30);
        add(btnRegistrar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(240, 330, 120, 30);
        add(btnVolver);

        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnVolver.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void registrarUsuario() {
        String nombreUsuario = txtNombreUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        String confirmarContrasena = new String(txtConfirmarContrasena.getPassword()).trim();

        if (nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: nombre de usuario");
            return;
        }

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: nombre");
            return;
        }

        if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: apellido");
            return;
        }

        if (telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: telefono");
            return;
        }

        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: correo electronico");
            return;
        }

        if (contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: contrasena");
            return;
        }

        if (confirmarContrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: confirmar contrasena");
            return;
        }

        if (!contrasena.equals(confirmarContrasena)) {
            JOptionPane.showMessageDialog(this, "La contrasena y su confirmacion no coinciden.");
            return;
        }

        Usuario usuario = new Usuario(nombreUsuario, nombre, apellido, telefono, correo, contrasena);

        boolean registrado = usuarioServicio.registrarUsuario(usuario);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");

            if (principalFrame != null) {
                principalFrame.cargarUsuarios();
            }

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar el usuario. Verifique si el usuario o correo ya existen.");
        }
    }
}