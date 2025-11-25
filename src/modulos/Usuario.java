package modulos;
import java.util.Objects;

public abstract class Usuario {
    protected String nombre;
    protected String email;
    protected String rol;
    protected String password;

    public Usuario(String nombre, String email, String rol) {
        if (nombre == null || email == null || rol == null || nombre.isEmpty() || email.isEmpty() || rol.isEmpty()) {
            throw new IllegalArgumentException("Nombre, email y rol no pueden ser nulos.");
        }
        if (nombre.length() < 5) {
            throw new IllegalArgumentException("Nombre debe tener al menos 5 caracteres.");
        }
        if (!email.contains("@") || !email.endsWith(".com")) {
            throw new IllegalArgumentException("Email no es válido.");
        }
        if (!rol.equals("Profesor") && !rol.equals("Ayudante")) {
            throw new IllegalArgumentException("Rol debe ser 'Profesor' o 'Ayudante'.");
        }
        if (password != null && password.length() < 8) {
            throw new IllegalArgumentException("Contraseña debe tener al menos 8 caracteres.");
        }
        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Contraseña debe contener al menos un número.");
        }
        if (!password.matches(".*[!@#$%^&*()].*")) {
            throw new IllegalArgumentException("Contraseña debe contener al menos un carácter especial.");
        }
        if (password.matches(".*\\s.*")) {
            throw new IllegalArgumentException("Contraseña no debe contener espacios.");
        }

        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.password = "default123"; // Contraseña por defecto
    }

    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getRol() {
        return rol;
    public String getPassword() {
        return password;
    }
    public boolean checkCredentials(String email, String password) {
        return this.email.equalsIgnoreCase(email) && this.password.equals(password);
    }

    public abstract void mostrarMenu();

        @Override
    public String toString() {
        return String.format("Usuario: %s | Rol: %s", email, role);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nombre, other.nombre || !Objects.equals(this.email, other.email))) {
            return false;
        }
        return Objects.equals(this.rol, other.rol);
    }
    @Override
     public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.correo);
        hash = 53 * hash + Objects.hashCode(this.rol);
        return hash;
    }
}