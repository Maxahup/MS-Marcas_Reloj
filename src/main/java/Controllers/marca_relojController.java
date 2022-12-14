package Controllers;


import Entity.Marca_reloj;
import Services.marcas_relojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class marca_relojController {

    @Autowired
    marcas_relojService marcas_relojService;

    @GetMapping
    public ResponseEntity<List<Marca_reloj>> getAll() {
        List<Marca_reloj> marcas_reloj = marcas_relojService.getAll();
        if(marcas_reloj.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas_reloj);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Marca_reloj> getById(@PathVariable("id") int id){
        Marca_reloj marca_reloj = marcas_relojService.getMarca_relojById(id);
        if(marca_reloj == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca_reloj);
    }
    @PostMapping()
    public ResponseEntity<Marca_reloj> save(@RequestBody Marca_reloj marca_reloj){
        Marca_reloj Nueva_marca_reloj = marcas_relojService.save(marca_reloj);
        return ResponseEntity.ok(Nueva_marca_reloj);
    }

    @PostMapping("/subir")
    public ResponseEntity<List<Marca_reloj>> saveAll(@RequestBody List<Marca_reloj> marcas_reloj){
        int cantidad_marcas = marcas_reloj.size();
        for (int i=0 ; i<cantidad_marcas ; i++){
            Marca_reloj nuevaMarca = marcas_relojService.save(marcas_reloj.get(i));
        }
        return ResponseEntity.ok((marcas_reloj));
    }
    @PostMapping("/subir2")
    public ResponseEntity<List<Marca_reloj>> saveAll(@RequestBody MultipartFile file){
        List<Marca_reloj> marcas_reloj= marcas_relojService.saveData(file);
        int cantidad_marcas = marcas_reloj.size();
        for (int i=0 ; i<cantidad_marcas ; i++){
            Marca_reloj nuevaMarca = marcas_relojService.save(marcas_reloj.get(i));
        }
        return ResponseEntity.ok((marcas_reloj));
    }



}


