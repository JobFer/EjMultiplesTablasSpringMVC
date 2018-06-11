package modelo;

import entidades.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {ServicioException.class})
public class AlumnoDao {

    @PersistenceContext
    private EntityManager em;

//    public void create(Alumno dto) throws ServicioException {
//        em.persist(dto);
//    }

//    public Alumno readByRut(String rut) throws SecurityException {
//        return em.find(Alumno.class, rut);
//    }

    //Lo mismo con JPQL
//    public Alumno readByRutJPQL(String rut) throws SecurityException {
    public Alumno readByRutJPQL(String rut) throws ServicioException {
        
        try {
            String sql = "Select a from Alumno a Where a.rut = :rut";
            Query q = em.createQuery(sql);
            q.setParameter("rut", rut);
        
            return (Alumno) q.getSingleResult();
            
        }catch (Exception e) {
            //Usamos el constructor con 2 parametros
            throw new ServicioException("Excepción en readByRutJPQL", 
                    new Exception("Excepción causante: " + e.getMessage()));
        }
    }


//    public List<Alumno> readAllJPQL() throws SecurityException {
//        String sql = "Select a from Alumno a";
//
//        Query q = em.createQuery(sql);
//        return q.getResultList();
//
//    }

//    public boolean readByRutJPQLCreate(String rut) throws SecurityException {
//        String sql = "Select a from Alumno a Where a.rut = :rut";
//        Query q = em.createQuery(sql);
//        q.setParameter("rut", rut);
//
//        if (q.getResultList().size() > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public String createJPQL(Alumno dto) throws SecurityException {
//        if (readByRutJPQLCreate(dto.getRut())) {
//            return "-1";
//        } else {
//            em.persist(dto);
//            return dto.getRut();
//        }
//    }
}
