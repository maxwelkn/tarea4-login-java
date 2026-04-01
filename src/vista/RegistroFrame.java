package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.Mensajes;
import modelo.Usuario;
import servicio.UsuarioServicio;
import util.BotonRedondeado;
import util.PanelDegradado;
import util.PanelRedondeado;

public class RegistroFrame extends JFrame {

    // Campos del formulario de registro
    private JTextField txtNombreUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmarContrasena;
    private BotonRedondeado btnRegistrar;
    private JButton btnVolver;

    // Servicio y referencia a la ventana principal
    private UsuarioServicio usuarioServicio;
    private PrincipalFrame principalFrame;

    public RegistroFrame() {
        this(null);
    }

    public RegistroFrame(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        usuarioServicio = new UsuarioServicio();

        // Configuracion general de la ventana
        setTitle("Registro");
        setSize(760, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Fondo principal con degradado
        PanelDegradado fondo = new PanelDegradado();
        fondo.setLayout(null);
        setContentPane(fondo);

        // Titulo principal
        JLabel lblTitulo = new JLabel("C R E A R   C U E N T A");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(180, 35, 400, 40);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        fondo.add(lblTitulo);

        // Panel central donde van los campos
        PanelRedondeado panelCentral = new PanelRedondeado(new Color(255, 255, 255, 90), 35);
        panelCentral.setLayout(null);
        panelCentral.setBounds(180, 95, 390, 420);
        fondo.add(panelCentral);

        // Creacion de los campos del formulario
        txtNombreUsuario = crearCampo(panelCentral, 70);
        txtNombre = crearCampo(panelCentral, 115);
        txtApellido = crearCampo(panelCentral, 160);
        txtTelefono = crearCampo(panelCentral, 205);
        txtCorreo = crearCampo(panelCentral, 250);
        txtContrasena = crearPassword(panelCentral, 295);
        txtConfirmarContrasena = crearPassword(panelCentral, 340);

        // Etiquetas de cada campo
        JLabel lblNombreUsuario = crearLabel("Nombre de usuario", 55);
        JLabel lblNombre = crearLabel("Nombre", 100);
        JLabel lblApellido = crearLabel("Apellido", 145);
        JLabel lblTelefono = crearLabel("Telefono", 190);
        JLabel lblCorreo = crearLabel("Correo electronico", 235);
        JLabel lblContrasena = crearLabel("Contrasena", 280);
        JLabel lblConfirmar = crearLabel("Confirmar contrasena", 325);

        panelCentral.add(lblNombreUsuario);
        panelCentral.add(lblNombre);
        panelCentral.add(lblApellido);
        panelCentral.add(lblTelefono);
        panelCentral.add(lblCorreo);
        panelCentral.add(lblContrasena);
        panelCentral.add(lblConfirmar);

        // Boton para registrar el usuario
        btnRegistrar = new BotonRedondeado("REGISTER");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnRegistrar.setBounds(130, 375, 130, 35);
        panelCentral.add(btnRegistrar);

        // Boton para cerrar la ventana y volver
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(320, 535, 90, 28);
        btnVolver.setBackground(new Color(255, 255, 255, 120));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 150), 1));
        fondo.add(btnVolver);

        // Eventos de los botones
        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnVolver.addActionListener(e -> dispose());

        setVisible(true);
    }

    // Crea un campo de texto con el estilo del formulario
    private JTextField crearCampo(PanelRedondeado panel, int y) {
        JTextField campo = new JTextField();
        campo.setBounds(45, y, 300, 32);
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 210), 1),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        panel.add(campo);
        return campo;
    }

    // Crea un campo de contrasena con el mismo estilo
    private JPasswordField crearPassword(PanelRedondeado panel, int y) {
        JPasswordField campo = new JPasswordField();
        campo.setBounds(45, y, 300, 32);
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 210), 1),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        panel.add(campo);
        return campo;
    }

    // Crea las etiquetas de cada campo
    private JLabel crearLabel(String texto, int y) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setBounds(48, y, 180, 15);
        return label;
    }

    // Valida los datos y registra un nuevo usuario
    private void registrarUsuario() {
        String nombreUsuario = txtNombreUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        String confirmarContrasena = new String(txtConfirmarContrasena.getPassword()).trim();

        // Validaciones de campos vacios
        if (nombreUsuario.isEmpty()) {
            Mensajes.mostrarInfo(this, "Falta el campo: nombre de usuario");
            return;
        }

        if (nombre.isEmpty()) {
            Mensajes.mostrarInfo(this, "Falta el campo: nombre");
            return;
        }

        if (apellido.isEmpty()) {
            Mensajes.mostrarInfo(this, "Falta el campo: apellido");
            return;
        }

        if (telefono.isEmpty()) {
            Mensajes.mostrarInfo(this, "Falta el campo: telefono");
            return;
        }

        if (correo.isEmpty()) {
            Mensajes.mostrarInfo(this, "Falta el campo: correo electronico");
            return;
        }

        if (contrasena.isEmpty()) {
            Mensajes.mostrarInfo(this, "Falta el campo: contrasena");
            return;
        }

        if (confirmarContrasena.isEmpty()) {
            Mensajes.mostrarInfo(this, "Falta el campo: confirmar contrasena");
            return;
        }

        // Verifica que ambas contrasenas sean iguales
        if (!contrasena.equals(confirmarContrasena)) {
            Mensajes.mostrarInfo(this, "La contrasena y su confirmacion no coinciden.");
            return;
        }

        Usuario usuario = new Usuario(nombreUsuario, nombre, apellido, telefono, correo, contrasena);

        boolean registrado = usuarioServicio.registrarUsuario(usuario);

        // Si se registra correctamente, actualiza la tabla y cierra la ventana
        if (registrado) {
            Mensajes.mostrarInfo(this, "Usuario registrado correctamente.");

            if (principalFrame != null) {
                principalFrame.cargarUsuarios();
            }

            dispose();
        } else {
            Mensajes.mostrarInfo(this, "No se pudo registrar el usuario. Verifique si el usuario o correo ya existen.");
        }
    }
}