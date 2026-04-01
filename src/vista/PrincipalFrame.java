package vista;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import util.Mensajes;
import modelo.Usuario;
import servicio.UsuarioServicio;
import util.BotonRedondeado;
import util.PanelDegradado;
import util.PanelRedondeado;

public class PrincipalFrame extends JFrame {

    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private BotonRedondeado btnNuevo;
    private BotonRedondeado btnActualizar;
    private BotonRedondeado btnEliminar;
    private BotonRedondeado btnCerrarSesion;

    private UsuarioServicio usuarioServicio;

    public PrincipalFrame() {
        usuarioServicio = new UsuarioServicio();

        setTitle("Pantalla Principal");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        PanelDegradado fondo = new PanelDegradado();
        fondo.setLayout(null);
        setContentPane(fondo);

        JLabel lblTitulo = new JLabel("U S U A R I O S   R E G I S T R A D O S");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(200, 30, 500, 40);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        fondo.add(lblTitulo);

        PanelRedondeado panelCentral = new PanelRedondeado(new Color(255, 255, 255, 90), 35);
        panelCentral.setLayout(null);
        panelCentral.setBounds(70, 95, 840, 450);
        fondo.add(panelCentral);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Correo electronico");
        modeloTabla.addColumn("Usuario");

        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setRowHeight(28);
        tablaUsuarios.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaUsuarios.setSelectionBackground(new Color(52, 152, 219));
        tablaUsuarios.setSelectionForeground(Color.WHITE);
        tablaUsuarios.setGridColor(new Color(220, 220, 220));
        tablaUsuarios.setBorder(BorderFactory.createEmptyBorder());

        JTableHeader encabezado = tablaUsuarios.getTableHeader();
        encabezado.setFont(new Font("Arial", Font.BOLD, 13));
        encabezado.setBackground(new Color(52, 73, 94));
        encabezado.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBounds(35, 35, 770, 280);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        panelCentral.add(scrollPane);

        btnNuevo = new BotonRedondeado("NUEVO");
        btnNuevo.setFont(new Font("Arial", Font.BOLD, 13));
        btnNuevo.setBounds(45, 360, 150, 38);
        panelCentral.add(btnNuevo);

        btnActualizar = new BotonRedondeado("ACTUALIZAR");
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 13));
        btnActualizar.setBounds(235, 360, 150, 38);
        panelCentral.add(btnActualizar);

        btnEliminar = new BotonRedondeado("ELIMINAR");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEliminar.setBounds(425, 360, 150, 38);
        panelCentral.add(btnEliminar);

        btnCerrarSesion = new BotonRedondeado("CERRAR SESION");
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 13));
        btnCerrarSesion.setBounds(615, 360, 170, 38);
        panelCentral.add(btnCerrarSesion);

        btnNuevo.addActionListener(e -> new RegistroFrame(this));
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnActualizar.addActionListener(e -> actualizarUsuario());

        cargarUsuarios();

        setVisible(true);
    }

    public void cargarUsuarios() {
        modeloTabla.setRowCount(0);

        List<Usuario> listaUsuarios = usuarioServicio.obtenerUsuarios();

        for (Usuario usuario : listaUsuarios) {
            Object[] fila = {
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getTelefono(),
                usuario.getCorreo(),
                usuario.getNombreUsuario()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void actualizarUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();

        if (filaSeleccionada == -1) {
        	Mensajes.mostrarInfo(this, "Debe seleccionar un usuario para actualizar.");
            return;
        }

        int id = Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
        Usuario usuario = usuarioServicio.buscarPorId(id);

        if (usuario != null) {
            new EditarUsuarioFrame(usuario, this);
        } else {
        	Mensajes.mostrarInfo(this, "No se pudo encontrar el usuario.");
        }
    }

    private void eliminarUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();

        if (filaSeleccionada == -1) {
        	Mensajes.mostrarInfo(this, "Debe seleccionar un usuario para eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar este usuario?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 0).toString());

            boolean eliminado = usuarioServicio.eliminarUsuario(id);

            if (eliminado) {
            	Mensajes.mostrarInfo(this, "Usuario eliminado correctamente.");
                cargarUsuarios();
            } else {
            	Mensajes.mostrarInfo(this, "No se pudo eliminar el usuario.");
            }
        }
    }

    private void cerrarSesion() {
        dispose();
        new LoginFrame();
    }
}