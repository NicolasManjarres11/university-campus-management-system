package com.devsenior.nmanja.university_campus_management_system.helper;


import org.springframework.stereotype.Component;

@Component
public class UpdateHelper {

    public static <T> void updateIfNotNull(T target, Object source) {
        try {

            //Obtenemos el tipo de clase y campos del recurso
            var sourceFields = source.getClass().getDeclaredFields();
            
            //Para cada campo del recurso

            for (var sourceField : sourceFields) {
                sourceField.setAccessible(true); //Permite que accedamos a los campos privados
                var value = sourceField.get(source); //Obtiene el valos del campo en el objeto
                
                if (isValidField(value)) {
                    try {
                        var targetField = target.getClass().getDeclaredField(sourceField.getName()); //Busca un campo con el mismo nombre en el Objeto destino (T)
                        targetField.setAccessible(true); //Permite acceder a los campos privados
                        targetField.set(target, value); //Asigna el nuevo valor al campo del objeto destino
                    } catch (NoSuchFieldException e) {
                        // Campo no existe en target, lo ignoramos
                        throw new NoSuchFieldException("Campo " + sourceField.getName() + " no existe en la entidad");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error actualizando entidad: " + e.getMessage(), e);
        }
    }
    
    private static boolean isValidField(Object field) {
        if (field == null) return false;
        if (field instanceof String) {
            return !((String) field).isBlank();
        }
        return true;
    }
    
}
