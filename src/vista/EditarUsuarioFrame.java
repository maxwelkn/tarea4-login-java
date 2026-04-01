package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
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

public class EditarUsuarioFrame extends JFrame {

    // Campos del formulario
    private JTextField txtNombreUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private BotonRedondeado btnGuardar;

    // Objetos necesarios para editar y actualizar el usuario
    private Usuario usuario;
    private UsuarioServicio usuarioServicio;
    private PrincipalFrame principalFrame;

    public EditarUsuarioFrame(Usuario usuario, PrincipalFrame principalFrame) {
        this.usuario = usuario;
        this.principalFrame = principalFrame;
        this.usuarioServicio = new UsuarioServicio();

        // Configuracion general de la ventana
        setTitle("Actualizar Usuario");
        setSize(760, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Fondo principal con degradado
        PanelDegradado fondo = new PanelDegradado();
        fondo.setLayout(null);
        setContentPane(fondo);

        // Titulo principal
        JLabel lblTitulo = new JLabel("E D I T A R   U S U A R I O");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(180, 35, 400, 40);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        fondo.add(lblTitulo);

        // Panel central donde van los campos
        PanelRedondeado panelCentral = new PanelRedondeado(new Color(255, 255, 255, 90), 35);
        panelCentral.setLayout(null);
        panelCentral.setBounds(180, 95, 390, 350);
        fondo.add(panelCentral);

        // Campos cargados con los datos actuales del usuario
        txtNombreUsuario = crearCampo(panelCentral, 70, usuario.getNombreUsuario());
        txtNombre = crearCampo(panelCentral, 115, usuario.getNombre());
        txtApellido = crearCampo(panelCentral, 160, usuario.getApellido());
        txtTelefono = crearCampo(panelCentral, 205, usuario.getTelefono());
        txtCorreo = crearCampo(panelCentral, 250, usuario.getCorreo());
        txtContrasena = crearPassword(panelCentral, 295, usuario.getContrasena());

        // Etiquetas de los campos
        JLabel lblNombreUsuario = crearLabel("Nombre de usuario", 55);
        JLabel lblNombre = crearLabel("Nombre", 100);
        JLabel lblApellido = crearLabel("Apellido", 145);
        JLabel lblTelefono = crearLabel("Telefono", 190);
        JLabel lblCorreo = crearLabel("Correo electronico", 235);
        JLabel lblContrasena = crearLabel("Contrasena", 280);

        panelCentral.add(lblNombreUsuario);
        panelCentral.add(lblNombre);
        panelCentral.add(lblApellido);
        panelCentral.add(lblTelefono);
        panelCentral.add(lblCorreo);
        panelCentral.add(lblContrasena);

        // Boton para guardar los cambios
        btnGuardar = new BotonRedondeado("Guardar Cambios");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 13));
        btnGuardar.setBounds(300, 465, 160, 35);
        fondo.add(btnGuardar);

        btnGuardar.addActionListener(e -> actualizarUsuario());

        setVisible(true);
    }

    // Crea un campo de texto con estilo y valor inicial
    private JTextField crearCampo(PanelRedondeado panel, int y, String valor) {
        JTextField campo = new JTextField(valor);
        campo.setBounds(45, y, 300, 32);
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 200, 210), 1),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        panel.add(campo);
        return campo;
    }

    // Crea el campo de contrasena con el mismo estilo visual
    private JPasswordField crearPassword(PanelRedondeado panel, int y, String valor) {
        JPasswordField campo = new JPasswordField(valor);
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

    // Obtiene los datos, valida y actualiza el usuario
    private void actualizarUsuario() {
        String nombreUsuario = txtNombreUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        // Verifica que ningun campo este vacio
        if (nombreUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty()
                || telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            Mensajes.mostrarInfo(this, "Todos los campos son obligatorios.");
            return;
        }

        // Actualiza el objeto usuario con los nuevos datos
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);

        boolean actualizado = usuarioServicio.actualizarUsuario(usuario);

        // Muestra el resultado de la actualizacion
        if (actualizado) {
            Mensajes.mostrarInfo(this, "Usuario actualizado correctamente.");
            principalFrame.cargarUsuarios();
            dispose();
        } else {
            Mensajes.mostrarError(this, "No se pudo actualizar el usuario.");
        }
    }
}