package modulos;
import java.util.Objects;
import java.util.Scanner;

public abstract class Usuario {
    protected String nombre;
    protected String correo;
    protected String rol;
    protected String password;

    public Usuario(String nombre, String correo, String rol, String password) {
        if (nombre == null || nombre.length() < 5 ) {
            throw new IllegalArgumentException("⚠︎ EL nombre debe de tener al menos 5 letras");
        }

        if (correo == null || !correo.contains("@") || !correo.endsWith(".com")) {
            throw new IllegalArgumentException("⚠︎ Correo no es válido (debe contener '@' y terminar en '.com').");
        }
        if (rol == null || (!rol.equals("Profesor") && !rol.equals("Ayudante") && !rol.equals("Alumno"))) {
            throw new IllegalArgumentException("!!! Rol debe ser 'Profesor' / 'Ayudante' / 'Alumno'");
        }

        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;

        setPassword(password);
    }

    public String getNombre() { 
        return nombre;
    }
    public String getCorreo() { 
        return correo;
    }
    public String getRol() { 
        return rol;
    }
    public String getPassword() { 
        return password;
    }


    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("⚠︎ Contraseña debe tener al menos 8 caracteres.");
        }
        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("⚠︎ Contraseña debe contener al menos un número.");
        }
        if (!password.matches(".*[!@#$%^&*()].*")) {
            throw new IllegalArgumentException("⚠︎ Contraseña debe contener al menos un carácter especial.");
        }
        if (password.contains(" ")) {
            throw new IllegalArgumentException("⚠︎ Contraseña no debe contener espacios.");
        }
        this.password = password;
    }

        public boolean checkCredentials(String correoInput, String passwordInput) {
        return this.correo.equalsIgnoreCase(correoInput) && this.password.equals(passwordInput);
    }
    public abstract void mostrarMenu(SistemaTareas sistema, Scanner sc);

        @Override
    public String toString() {
        return String.format("Usuario: %s (%s) -%s", nombre, correo, rol);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(this.correo, other.correo);
        }

    @Override
     public int hashCode() {
        return Objects.hash(correo);
    }
}