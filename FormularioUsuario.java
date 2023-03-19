//Author: Jesus David Nieves Hernandez - T00058742
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormularioUsuario extends JFrame {
    private JLabel etiquetaNombre;
    private JTextField campoNombre;
    private JLabel etiquetaEmail;
    private JTextField campoEmail;
    private JLabel etiquetaPassword;
    private JPasswordField campoPassword;
    private JButton botonEnviar;

    public FormularioUsuario() {
        // Configuración de la ventana
        setTitle("Formulario de Usuario");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Creación de los componentes de la GUI
        etiquetaNombre = new JLabel("Nombre completo:");
        campoNombre = new JTextField(20);
        etiquetaEmail = new JLabel("Correo electrónico:");
        campoEmail = new JTextField(20);
        etiquetaPassword = new JLabel("Contraseña:");
        campoPassword = new JPasswordField(20);
        botonEnviar = new JButton("Enviar");

        // Configuración del layout y adición de componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(etiquetaNombre);
        panel.add(campoNombre);
        panel.add(etiquetaEmail);
        panel.add(campoEmail);
        panel.add(etiquetaPassword);
        panel.add(campoPassword);
        panel.add(botonEnviar);

        // Asociación del evento de clic del botón "Enviar" con su correspondiente acción
        botonEnviar.addActionListener(e -> enviarDatos());

        // Adición del panel al contenedor principal de la ventana
        getContentPane().add(panel);
    }

    // Acción que se ejecuta cuando se presiona el botón "Enviar"
    private void enviarDatos() {
        String nombre = campoNombre.getText();
        String email = campoEmail.getText();
        String password = new String(campoPassword.getPassword());

        // Validación del correo electrónico
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Correo electrónico inválido. Por favor ingrese un correo electrónico válido.");
            return;
        }

        // Envío del mensaje de correo electrónico
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("tu_correo@gmail.com", "tu_contraseña");
                        }
                    });

           
