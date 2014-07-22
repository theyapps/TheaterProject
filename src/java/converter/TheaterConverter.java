/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import entity.Theater;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ryan
 */
@RequestScoped
@FacesConverter("convert.theater")
public class TheaterConverter implements Converter, Serializable {
    @PersistenceContext(unitName = "TheaterProjectPU")
    private EntityManager entityManager;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Long id = Long.valueOf(value);
        return entityManager.find(Theater.class, id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Theater)value).getId().toString();
    }
    
    
}
