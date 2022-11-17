package Services;


import Entity.Marca_reloj;
import Repository.marcas_relojRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class marcas_relojService {
    @Autowired
    marcas_relojRepository marcas_relojRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<Marca_reloj> getAll() {
        return marcas_relojRepository.findAll();
    }

    public Marca_reloj getMarca_relojById(int id) {
        return marcas_relojRepository.findById(id).orElse(null);
    }

    public Marca_reloj save(Marca_reloj marca_reloj) {
        Marca_reloj nuevaMarca_reloj = marcas_relojRepository.save(marca_reloj);
        return nuevaMarca_reloj;
    }

    public List<Marca_reloj> generarMarcas(ArrayList<String> arrayLecturas){
        //en esta lista se almacenan las marcas de reloj creadas para cada trabajador
        List<Marca_reloj> marcasGeneradas = new ArrayList<Marca_reloj>();
        ArrayList<String> rutsRegistrados = new ArrayList<String>();
        Integer cantidadLecturas = arrayLecturas.size();
        //ciclo para encontrar todos los ruts registrados
        for (int i=0 ; i<cantidadLecturas ; i++){
            String lecturaActual = arrayLecturas.get(i);
            String[] lecturaSeparada = lecturaActual.split(";");
            if(!rutsRegistrados.contains(lecturaSeparada[0])){
                rutsRegistrados.add(lecturaSeparada[0]);
            }
        }
        //ciclo para generar las marcas de cada uno de los ruts
        for (int i=0 ; i<cantidadLecturas ; i++ ){
            String lecturaMarcaActual = arrayLecturas.get(i);
            String[] marcaActual = lecturaMarcaActual.split(";");
            

        }



        return marcasGeneradas;
    }

}



