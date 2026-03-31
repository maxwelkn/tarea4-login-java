package vista;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Usuario;
import servicio.UsuarioServicio;

public class PrincipalFrame extends JFrame {

    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private JButton btnNuevo;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnCerrarSesion;

    private UsuarioServicio usuarioServicio;

    public PrincipalFrame() {
        usuarioServicio = new UsuarioServicio();

        setTitle("Pantalla Principal");
        setSize(800, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Clientes Registrados");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(30, 20, 250, 30);
        add(lblTitulo);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Correo electronico");
        modeloTabla.addColumn("Usuario");

        tablaUsuarios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBounds(30, 70, 720, 280);
        add(scrollPane);

        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(40, 380, 130, 35);
        add(btnNuevo);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(210, 380, 130, 35);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(380, 380, 130, 35);
        add(btnEliminar);

        btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.setBounds(550, 380, 150, 35);
        add(btnCerrarSesion);

        btnNuevo.addActionListener(e -> new RegistroFrame());
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
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario para actualizar.");
            return;
        }

        int id = Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
        Usuario usuario = usuarioServicio.buscarPorId(id);

        if (usuario != null) {
            new EditarUsuarioFrame(usuario, this);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo encontrar el usuario.");
        }
    }
    
    private void eliminarUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario para eliminar.");
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
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                cargarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el usuario.");
            }
        }
    }

    private void cerrarSesion() {
        dispose();
        new LoginFrame();
    }
}