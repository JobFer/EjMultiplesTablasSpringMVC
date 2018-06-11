package controlador;

import entidades.Alumno;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modelo.AlumnoDao;
import modelo.ServicioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mostrar.htm")
public class LeerFonos {
    
    @Autowired
    private AlumnoDao dao;
    
    @RequestMapping(method = RequestMethod.GET)
    public String mostrarAlumno(){
        return "buscar";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String mostrarAlumno(@RequestParam("txtRut") String rut, Model model, HttpServletRequest request){
        
        try{
            if (rut.trim().equals("")) {
                return "buscar";
            }
            System.out.println("rut:"+ rut);
            Alumno a = dao.readByRutJPQL(rut);
            
            model.addAttribute("listaFonos", a.getFonoCollection());
            request.getSession().setAttribute("nom", a.getNombre() + " " + a.getApellido());
        
        }catch(Exception e){
            
            System.out.println("e.getMessage(): " + e.getMessage());
            System.out.println("e.getCause().getMessage(): " + e.getCause().getMessage());
            
            model.addAttribute("err", "No se ha encontrado usuario" );
            return "error";
        }
        return "fonos";        
    }
}
