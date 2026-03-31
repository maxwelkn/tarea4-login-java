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

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnEntrar;
    private JButton btnRegistrarse;

    private UsuarioServicio usuarioServicio;

    public LoginFrame() {
        usuarioServicio = new UsuarioServicio();

        setTitle("Login");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("LOGIN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(150, 20, 100, 30);
        add(lblTitulo);

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        lblUsuario.setBounds(40, 80, 140, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(180, 80, 150, 25);
        add(txtUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(40, 120, 140, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(180, 120, 150, 25);
        add(txtContrasena);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(70, 190, 100, 30);
        add(btnEntrar);

        btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(200, 190, 120, 30);
        add(btnRegistrarse);

        btnEntrar.addActionListener(e -> iniciarSesion());
        btnRegistrarse.addActionListener(e -> abrirRegistro());

        setVisible(true);
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse.");
            return;
        }

        Usuario usuarioEncontrado = usuarioServicio.iniciarSesion(usuario, contrasena);

        if (usuarioEncontrado != null) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }

    private void abrirRegistro() {
        new RegistroFrame();
    }
}