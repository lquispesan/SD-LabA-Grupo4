package Cliente;

import Operaciones.DatabaseOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cliente {
    private static DatabaseOperations dbOperations;

    public static void main(String[] args) {
        dbOperations = new DatabaseOperations();

        JFrame frame = new JFrame("Gestión de Proyectos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel para las opciones principales
        JPanel optionsPanel = new JPanel(new GridLayout(5, 1));
        JButton deptButton = new JButton("Departamentos");
        JButton engButton = new JButton("Ingenieros");
        JButton projButton = new JButton("Proyectos");
        JButton engprojButton = new JButton("Asignar proyectos");
        JButton exitButton = new JButton("Salir");

        optionsPanel.add(deptButton);
        optionsPanel.add(engButton);
        optionsPanel.add(projButton);
        optionsPanel.add(engprojButton);
        optionsPanel.add(exitButton);

        mainPanel.add(optionsPanel, BorderLayout.CENTER);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        int posX = (screenWidth - windowWidth) / 2;
        int posY = (screenHeight - windowHeight) / 2;
        

        frame.getContentPane().add(mainPanel);
        frame.setLocation(posX, posY);
        frame.setVisible(true);

        // Acción para el botón Departamentos
        deptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMenuDepartamentos();
            }
        });

        // Acción para el botón Ingenieros
        engButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMenuIngenieros();
            }
        });

        // Acción para el botón Proyectos
        projButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMenuProyectos();
            }
        });
        
        engprojButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMenuAsignarProyectos();
            }
        });

        // Acción para el botón Salir
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saliendo...");
                System.out.println("Sesion cerrada");
                System.exit(0);
            }
        });
    }

    // Método para mostrar el menú de Departamentos
    private static void mostrarMenuDepartamentos() {
        JFrame deptFrame = new JFrame("Gestión de Departamentos");
        deptFrame.setSize(400, 300);
        JPanel deptPanel = new JPanel(new BorderLayout());

        // Panel para las operaciones de Departamentos
        JPanel deptOptionsPanel = new JPanel(new GridLayout(5, 1));
        JButton createDeptButton = new JButton("Crear Departamento");
        JButton readAllDeptsButton = new JButton("Listar Todos los Departamentos");
        JButton updateDeptButton = new JButton("Actualizar Departamento");
        JButton deleteDeptButton = new JButton("Eliminar Departamento");
        JButton backToMainButton = new JButton("Volver al Menú Principal");

        deptOptionsPanel.add(createDeptButton);
        deptOptionsPanel.add(readAllDeptsButton);
        deptOptionsPanel.add(updateDeptButton);
        deptOptionsPanel.add(deleteDeptButton);
        deptOptionsPanel.add(backToMainButton);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = deptFrame.getWidth();
        int windowHeight = deptFrame.getHeight();
        int posX = (screenWidth - windowWidth) / 2;
        int posY = (screenHeight - windowHeight) / 2;
        
        deptPanel.add(deptOptionsPanel, BorderLayout.CENTER);
        deptFrame.setLocation(posX, posY);
        deptFrame.getContentPane().add(deptPanel);
        deptFrame.setVisible(true);

        // Acción para el botón Crear Departamento
        createDeptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreDept = JOptionPane.showInputDialog("Nombre del Departamento:");
                if (nombreDept != null && !nombreDept.trim().isEmpty()) {
                    try {
                        dbOperations.insertarDepartamento(nombreDept);
                        JOptionPane.showMessageDialog(null, "Departamento insertado: " + nombreDept);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al insertar departamento: " + ex.getMessage());
                    }
                }
            }
        });

        // Acción para el botón Listar Todos los Departamentos
        readAllDeptsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<String> departamentos = dbOperations.obtenerTodosDepartamentos();
                    StringBuilder sb = new StringBuilder("Todos los Departamentos:\n");
                    for (String dept : departamentos) {
                        sb.append(dept).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al listar departamentos: " + ex.getMessage());
                }
            }
        });

        // Acción para el botón Actualizar Departamento
        updateDeptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idDeptStr = JOptionPane.showInputDialog("ID del Departamento:");
                if (idDeptStr != null && !idDeptStr.trim().isEmpty()) {
                    int idDept = Integer.parseInt(idDeptStr);
                    String nuevoNombreDept = JOptionPane.showInputDialog("Nuevo Nombre del Departamento:");
                    if (nuevoNombreDept != null && !nuevoNombreDept.trim().isEmpty()) {
                        try {
                            dbOperations.actualizarDepartamento(idDept, nuevoNombreDept);
                            JOptionPane.showMessageDialog(null, "Departamento actualizado: " + nuevoNombreDept);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al actualizar departamento: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        // Acción para el botón Eliminar Departamento
        deleteDeptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idDeptDelStr = JOptionPane.showInputDialog("ID del Departamento:");
                if (idDeptDelStr != null && !idDeptDelStr.trim().isEmpty()) {
                    int idDeptDel = Integer.parseInt(idDeptDelStr);
                    try {
                        dbOperations.eliminarDepartamento(idDeptDel);
                        JOptionPane.showMessageDialog(null, "Departamento eliminado: " + idDeptDel);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al eliminar departamento: " + ex.getMessage());
                    }
                }
            }
        });

        // Acción para el botón Volver al Menú Principal
        backToMainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deptFrame.dispose(); // Cierra la ventana actual de Departamentos
            }
        });
    }

    // Método para mostrar el menú de Ingenieros
    private static void mostrarMenuIngenieros() {    
        JFrame engFrame = new JFrame("Gestión de Ingenieros");
        engFrame.setSize(400, 300);
        JPanel engPanel = new JPanel(new BorderLayout());

        // Panel para las operaciones de Ingenieros
        JPanel engOptionsPanel = new JPanel(new GridLayout(5, 1));
        JButton createEngButton = new JButton("Registrar Ingeniero");
        JButton readAllEngsButton = new JButton("Listar Todos los Ingenieros");
        JButton updateEngButton = new JButton("Actualizar Registro");
        JButton deleteEngButton = new JButton("Eliminar Registro");
        JButton backToMainButton = new JButton("Volver al Menú Principal");

        engOptionsPanel.add(createEngButton);
        engOptionsPanel.add(readAllEngsButton);
        engOptionsPanel.add(updateEngButton);
        engOptionsPanel.add(deleteEngButton);
        engOptionsPanel.add(backToMainButton);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = engFrame.getWidth();
        int windowHeight = engFrame.getHeight();
        int posX = (screenWidth - windowWidth) / 2;
        int posY = (screenHeight - windowHeight) / 2;

        engPanel.add(engOptionsPanel, BorderLayout.CENTER);
        engFrame.setLocation(posX, posY);
        engFrame.getContentPane().add(engPanel);
        engFrame.setVisible(true);

    // Acción para el botón Crear Ingeniero
    createEngButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String nombreEng = JOptionPane.showInputDialog("Nombre del Ingeniero:");
            if (nombreEng != null && !nombreEng.trim().isEmpty()) {
                try {
                    dbOperations.insertarIngeniero(nombreEng);
                    JOptionPane.showMessageDialog(null, "Ingeniero insertado: " + nombreEng);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar ingeniero: " + ex.getMessage());
                }
            }
        }
    });

    // Acción para el botón Listar Todos los Ingenieros
    readAllEngsButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<String> ingenieros = dbOperations.obtenerTodosIngenieros();
                StringBuilder sb = new StringBuilder("Todos los Ingenieros:\n");
                for (String eng : ingenieros) {
                    sb.append(eng).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al listar ingenieros: " + ex.getMessage());
            }
        }
    });

    // Acción para el botón Actualizar Ingeniero
    updateEngButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String idEngStr = JOptionPane.showInputDialog("ID del Ingeniero:");
            if (idEngStr != null && !idEngStr.trim().isEmpty()) {
                int idEng = Integer.parseInt(idEngStr);
                String nuevoNombreEng = JOptionPane.showInputDialog("Nuevo Nombre del Ingeniero:");
                if (nuevoNombreEng != null && !nuevoNombreEng.trim().isEmpty()) {
                    try {
                        dbOperations.actualizarIngeniero(idEng, nuevoNombreEng);
                        JOptionPane.showMessageDialog(null, "Ingeniero actualizado: " + nuevoNombreEng);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al actualizar ingeniero: " + ex.getMessage());
                    }
                }
            }
        }
    });

    // Acción para el botón Eliminar Ingeniero
    deleteEngButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String idEngDelStr = JOptionPane.showInputDialog("ID del Ingeniero a eliminar:");
            if (idEngDelStr != null && !idEngDelStr.trim().isEmpty()) {
                int idEngDel = Integer.parseInt(idEngDelStr);
                try {
                    dbOperations.eliminarIngeniero(idEngDel);
                    JOptionPane.showMessageDialog(null, "Ingeniero eliminado: " + idEngDel);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar ingeniero: " + ex.getMessage());
                }
            }
        }
    });

    // Acción para el botón Volver al Menú Principal
    backToMainButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            engFrame.dispose(); // Cierra la ventana actual de Ingenieros
        }
    });
    }

    // Método para mostrar el menú de Proyectos
    private static void mostrarMenuProyectos() {
        JFrame projFrame = new JFrame("Gestión de Proyectos");
        projFrame.setSize(400, 300);
        JPanel projPanel = new JPanel(new BorderLayout());

        // Panel para las operaciones de Proyectos
        JPanel projOptionsPanel = new JPanel(new GridLayout(5, 1));
        JButton createProjButton = new JButton("Crear Proyecto");
        JButton readAllProjsButton = new JButton("Listar Todos los Proyectos");
        JButton updateProjButton = new JButton("Actualizar Proyecto");
        JButton deleteProjButton = new JButton("Eliminar Proyecto");
        JButton backToMainButton = new JButton("Volver al Menú Principal");

        projOptionsPanel.add(createProjButton);
        projOptionsPanel.add(readAllProjsButton);
        projOptionsPanel.add(updateProjButton);
        projOptionsPanel.add(deleteProjButton);
        projOptionsPanel.add(backToMainButton);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = projFrame.getWidth();
        int windowHeight = projFrame.getHeight();
        int posX = (screenWidth - windowWidth) / 2;
        int posY = (screenHeight - windowHeight) / 2;        

        projPanel.add(projOptionsPanel, BorderLayout.CENTER);
        projFrame.setLocation(posX, posY);
        projFrame.getContentPane().add(projPanel);
        projFrame.setVisible(true);

    // Acción para el botón Crear Proyecto
    createProjButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String nombreProj = JOptionPane.showInputDialog("Nombre del Proyecto:");
            if (nombreProj != null && !nombreProj.trim().isEmpty()) {
                try {
                    dbOperations.insertarProyecto(nombreProj);
                    JOptionPane.showMessageDialog(null, "Proyecto insertado: " + nombreProj);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar proyecto: " + ex.getMessage());
                }
            }
        }
    });

    // Acción para el botón Listar Todos los Proyectos
    readAllProjsButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<String> proyectos = dbOperations.obtenerTodosProyectos();
                StringBuilder sb = new StringBuilder("Todos los Proyectos:\n");
                for (String proj : proyectos) {
                    sb.append(proj).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al listar proyectos: " + ex.getMessage());
            }
        }
    });

    // Acción para el botón Actualizar Proyecto
    updateProjButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String idProjStr = JOptionPane.showInputDialog("ID del Proyecto:");
            if (idProjStr != null && !idProjStr.trim().isEmpty()) {
                int idProj = Integer.parseInt(idProjStr);
                String nuevoNombreProj = JOptionPane.showInputDialog("Nuevo Nombre del Proyecto:");
                if (nuevoNombreProj != null && !nuevoNombreProj.trim().isEmpty()) {
                    try {
                        dbOperations.actualizarProyecto(idProj, nuevoNombreProj);
                        JOptionPane.showMessageDialog(null, "Proyecto actualizado: " + nuevoNombreProj);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al actualizar proyecto: " + ex.getMessage());
                    }
                }
            }
        }
    });

    // Acción para el botón Eliminar Proyecto
    deleteProjButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String idProjDelStr = JOptionPane.showInputDialog("ID del Proyecto a eliminar:");
            if (idProjDelStr != null && !idProjDelStr.trim().isEmpty()) {
                int idProjDel = Integer.parseInt(idProjDelStr);
                try {
                    dbOperations.eliminarProyecto(idProjDel);
                    JOptionPane.showMessageDialog(null, "Proyecto eliminado: " + idProjDel);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar proyecto: " + ex.getMessage());
                }
            }
        }
    });

    // Acción para el botón Volver al Menú Principal
    backToMainButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            projFrame.dispose(); // Cierra la ventana actual de Proyectos
        }
    });
    }
 
    private static void mostrarMenuAsignarProyectos() {

        JFrame engprojFrame = new JFrame("Gestión de Proyectos");
        engprojFrame.setSize(400, 300);
        
        JPanel engprojOptionsPanel = new JPanel(new GridLayout(3, 1));
        JButton asignarProyectoButton = new JButton("Asignar Proyecto a Ingeniero");
        JButton verProyectosIngenieroButton = new JButton("Ver Proyectos de Ingeniero");
        JButton backToMainButton = new JButton("Volver al Menú Principal");

        engprojOptionsPanel.add(asignarProyectoButton);
        engprojOptionsPanel.add(verProyectosIngenieroButton);
        engprojOptionsPanel.add(backToMainButton);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = engprojFrame.getWidth();
        int windowHeight = engprojFrame.getHeight();
        int posX = (screenWidth - windowWidth) / 2;
        int posY = (screenHeight - windowHeight) / 2;        

        engprojFrame.getContentPane().add(engprojOptionsPanel);
        engprojFrame.setLocation(posX, posY);
        engprojFrame.getContentPane().add(engprojOptionsPanel);
        engprojFrame.setVisible(true);

        // Acción para el botón Asignar Proyecto a Ingeniero
        asignarProyectoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idIngenieroStr = JOptionPane.showInputDialog("ID del Ingeniero:");
                String idProyectoStr = JOptionPane.showInputDialog("ID del Proyecto:");
                if (idIngenieroStr != null && !idIngenieroStr.trim().isEmpty() &&
                    idProyectoStr != null && !idProyectoStr.trim().isEmpty()) {
                    try {
                        int idIngeniero = Integer.parseInt(idIngenieroStr);
                        int idProyecto = Integer.parseInt(idProyectoStr);
                        dbOperations.asignarProyectoAIngeniero(idIngeniero, idProyecto);
                        JOptionPane.showMessageDialog(null, "Proyecto asignado al Ingeniero");
                    } catch (NumberFormatException | SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al asignar proyecto: " + ex.getMessage());
                    }
                }
            }
        });

        // Acción para el botón Ver Proyectos de Ingeniero
        verProyectosIngenieroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idIngenieroStr = JOptionPane.showInputDialog("ID del Ingeniero:");
                if (idIngenieroStr != null && !idIngenieroStr.trim().isEmpty()) {
                    try {
                        int idIngeniero = Integer.parseInt(idIngenieroStr);
                        ArrayList<String> proyectos = dbOperations.obtenerProyectosDeIngeniero(idIngeniero);
                        StringBuilder sb = new StringBuilder("Proyectos asignados al Ingeniero:\n");
                        for (String proj : proyectos) {
                            sb.append(proj).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    } catch (NumberFormatException | SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al obtener proyectos: " + ex.getMessage());
                    }
                }
            }
        });
            backToMainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                engprojFrame.dispose(); // Cierra la ventana actual de asignar proyectos
            }
        });


    }
}