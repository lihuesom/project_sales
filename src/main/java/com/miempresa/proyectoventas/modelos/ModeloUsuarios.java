package com.miempresa.proyectoventas.modelos;

public class ModeloUsuarios extends ModeloBase{

    public ModeloUsuarios(){
        super();
    }
    
    public boolean validarPassword(String nombreUsuario, String password){
        try {
            String query = "SELECT hash_password FROM t_usuarios WHERE nombre_usuario = '"+nombreUsuario+ "'";
            String passwordHash = consultarPrimerResultado(query).get("hash_password");
            return MD5(password).equals(passwordHash);
        }catch (Exception ex){
            return false;    
        }
    }
    
    private String MD5(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuilder sb = new StringBuilder();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
         }
         return null;
    }
    
    
}