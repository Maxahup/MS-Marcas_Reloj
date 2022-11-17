package Services;


import Entity.Marca_reloj;
import Repository.marcas_relojRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import java.util.HashMap;
import java.util.Map;
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

}



