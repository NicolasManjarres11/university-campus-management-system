package com.devsenior.nmanja.university_campus_management_system.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    //Método para obtener la llave para firmar
    //Se requiere la llave única de tipo Key para firmar y verificar los tokens

    private SecretKey getSignIKey(){

        var keyBytes = Decoders.BASE64.decode(secret); //convertimos a un Array de Bytes
        return Keys.hmacShaKeyFor(keyBytes); //Convertimos los bytes en una clave criptográfica (hash)

    }


    //Método para obtener los claims de un token

        //Método genérico, el dato que devuelve T puede ser cualquiera

        /**
     * Extrae un claim específico del token JWT usando una función extractora.
     * 
     * Este método genérico permite extraer cualquier claim del token JWT
     * proporcionando una función que especifica qué claim extraer.
     * 
     * @param <T>           Tipo de dato del claim a extraer
     * @param token         Token JWT del cual extraer el claim
     * @param claimResolver Función que especifica qué claim extraer del token
     * @return El valor del claim extraído
     * @throws IllegalArgumentException si el token es inválido o no puede ser
     *                                  parseado
     */

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolser){
        final var claims = extractAllClaims(token);
        return claimsResolser.apply(claims);
    }

    /**
     * Extrae TODOS los claims del token JWT.
     * 
     * Este método parsea el token JWT, verifica su firma digital y
     * retorna todos los claims contenidos en el payload del token.
     * 
     * @param token Token JWT del cual extraer todos los claims
     * @return Objeto Claims con todos los claims del token
     * @throws IllegalArgumentException si el token es inválido, está malformado o
     *                                  la firma es incorrecta
     */


    public Claims extractAllClaims(String token){ //Parsea desde el token a todos los claims del token

        return Jwts.parser() //Inicializa el constructor del parser de JWT
            .verifyWith(getSignIKey())  //Establecemos la clave de firma para la verificación y validación del token
            .build()   //Construye el parser del token
            .parseSignedClaims(token)   //Parsea el token y verifica la firma (JSON Web Signature)
            .getPayload(); //Ontiene el cuerpo (Payload) del token como unobjeto claim

    }

            /**
     * Extrae el nombre de usuario del token JWT.
     * 
     * @param token Token JWT del cual extraer el nombre de usuario
     * @return Nombre de usuario extraído del token
     * @throws IllegalArgumentException si el token es inválido o no puede ser
     *                                  parseado
     */

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

            /**
     * Extrae la fecha de expiración del token JWT.
     * 
     * @param token Token JWT del cual extraer la fecha de expiración
     * @return Fecha de expiración del token
     * @throws IllegalArgumentException si el token es inválido o no puede ser
     *                                  parseado
     */

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

        /**
     * Verifica si el token JWT ha expirado.
     * 
     * @param token Token JWT a verificar
     * @return true si el token ha expirado, false en caso contrario
     * @throws IllegalArgumentException si el token es inválido o no puede ser
     *                                  parseado
     */

    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

            /**
     * Valida un token JWT contra los detalles de un usuario.
     * 
     * La validación incluye:
     * - Verificar que el nombre de usuario en el token coincida con el
     * proporcionado
     * - Verificar que el token no haya expirado
     * - Verificar la firma digital del token
     * 
     * @param token       Token JWT a validar
     * @param userDetails Detalles del usuario contra el cual validar el token
     * @return true si el token es válido, false en caso contrario
     * @throws IllegalArgumentException si el token es inválido o no puede ser
     *                                  parseado
     */

    public Boolean validateToken(String token, UserDetails userDetails){

        final var username =extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

        /**
     * Genera un token JWT para un usuario específico.
     * 
     * Este método crea un token JWT que incluye:
     * - El nombre de usuario como subject
     * - Los roles/autoridades del usuario como claims personalizados
     * - Fecha de emisión y expiración
     * - Firma digital usando la clave secreta configurada
     * 
     * @param userDetails Detalles del usuario para el cual generar el token
     * @return Token JWT como String
     * @throws IllegalArgumentException si userDetails es null
     */

    public String generateToken(UserDetails userDetails){

        var claims = new HashMap<String, Object>();

        //Agregamos la información al token

        claims.put("roles", userDetails.getAuthorities().stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .toList());

        return createToken(claims, userDetails.getUsername());

    }

    /**
     * Crea un token JWT con claims personalizados.
     * 
     * Este método privado construye el token JWT final incluyendo:
     * - Claims personalizados proporcionados
     * - Nombre de usuario como subject
     * - Fecha de emisión (iat)
     * - Fecha de expiración (exp)
     * - Firma digital usando la clave secreta
     * 
     * @param claims   Claims personalizados a incluir en el token
     * @param username Nombre de usuario que será el subject del token
     * @return Token JWT como String
     */

    public String createToken(Map<String, Object> claims, String username){

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignIKey())
                .compact();

    }






}
