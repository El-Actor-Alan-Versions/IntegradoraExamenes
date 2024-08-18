package mx.edu.utez.integradiratjuans.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtils {

    // Método para hashear una contraseña

    public static String hashPassword(String password) {
        try {
            // Crear instancia del algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Realizar el hash de la contraseña
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Convertir bytes a formato hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            // Retornar la contraseña hasheada
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
}
