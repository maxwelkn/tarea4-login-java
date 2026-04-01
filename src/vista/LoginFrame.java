package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import util.Mensajes;
import modelo.Usuario;
import servicio.UsuarioServicio;
import util.BotonRedondeado;
import util.PanelDegradado;
import util.PanelRedondeado;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private BotonRedondeado btnEntrar;
    private JLabel lblRegistrarse;

    private UsuarioServicio usuarioServicio;

    public LoginFrame() {
        usuarioServicio = new UsuarioServicio();

        setTitle("Login");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        PanelDegradado fondo = new PanelDegradado();
        fondo.setLayout(null);
        setContentPane(fondo);

        JLabel lblTitulo = new JLabel("W E L C O M E"  );
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(200, 55, 300, 40);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        fondo.add(lblTitulo);

        PanelRedondeado panelCentral = new PanelRedondeado(new Color(255, 255, 255, 90), 35);
        panelCentral.setLayout(null);
        panelCentral.setBounds(190, 110, 300, 220);
        fondo.add(panelCentral);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(45, 35, 210, 40);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 210), 1),
                BorderFactory.createEmptyBorder(5, 12, 5, 12)
        ));
        panelCentral.add(txtUsuario);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(45, 95, 210, 40);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 210), 1),
                BorderFactory.createEmptyBorder(5, 12, 5, 12)
        ));
        panelCentral.add(txtContrasena);

        btnEntrar = new BotonRedondeado("LOGIN");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEntrar.setBounds(95, 155, 110, 35);
        panelCentral.add(btnEntrar);

        JLabel lblInfo = new JLabel("Don't have account?");
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblInfo.setForeground(Color.WHITE);
        lblInfo.setBounds(255, 345, 120, 20);
        fondo.add(lblInfo);

        lblRegistrarse = new JLabel("Register");
        lblRegistrarse.setFont(new Font("Arial", Font.BOLD, 12));
        lblRegistrarse.setForeground(new Color(255, 230, 0));
        lblRegistrarse.setBounds(380, 345, 60, 20);
        fondo.add(lblRegistrarse);

        btnEntrar.addActionListener(e -> iniciarSesion());

        lblRegistrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                abrirRegistro();
            }
        });

        setVisible(true);
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
        	Mensajes.mostrarInfo(this,
                    "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse.");
            return;
        }

        Usuario usuarioEncontrado = usuarioServicio.iniciarSesion(usuario, contrasena);

        if (usuarioEncontrado != null) {
            dispose();
            new PrincipalFrame();
        } else {
        	Mensajes.mostrarInfo(this, "Usuario o contraseña incorrectos.");
        }
    }

    private void abrirRegistro() {
        new RegistroFrame();
    }
}