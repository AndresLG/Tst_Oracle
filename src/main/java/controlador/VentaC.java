package controlador;

import dao.VentaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.VentaM;

@Data
@Named(value = "ventaC")
@SessionScoped
public class VentaC implements Serializable {

    private VentaM venta = new VentaM();
    private List<VentaM> lstVenta;
    private VentaM selectedVenta;

    @PostConstruct
    public void iniciar(){
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void limpiar(){
        venta = new VentaM();
    }
    
    public void add() throws Exception {
        VentaImpl dao;
        try {
            dao = new VentaImpl();
            dao.agregar(venta);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("AGREGADO CON EXITO"));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("NO SE PUDO AGREGAR"));
            throw e;
        }
    }
    
    public void listar() throws Exception {
        VentaImpl dao;
        try {
            dao = new VentaImpl();
            lstVenta = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

}
