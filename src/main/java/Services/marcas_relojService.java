package Services;


import Entity.Marca_reloj;
import Repository.marcas_relojRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    public String[] saveData(MultipartFile file){
        if(!file.isEmpty()){
            try {
                byte [] bytes=file.getBytes();
                String str = new String(bytes, StandardCharsets.UTF_8);
                String[] data = str.split("\n");
                generarMarcas(data);

            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public List<Marca_reloj> generarMarcas(String[] arrayLecturas){
        //en esta lista se almacenan las marcas de reloj creadas para cada trabajador
        List<Marca_reloj> marcasGeneradas = new ArrayList<Marca_reloj>();
        ArrayList<String> rutsRegistrados = new ArrayList<String>();
        //Integer cantidadLecturas = arrayLecturas.size();
        Integer cantidadLecturas = arrayLecturas.length;
        //ciclo para encontrar todos los ruts registrados
        for (int i=0 ; i<cantidadLecturas ; i++){
            String lecturaActual = arrayLecturas[i];
            //String lecturaActual = arrayLecturas.get(i);
            String[] lecturaSeparada = lecturaActual.split(";");
            if(!rutsRegistrados.contains(lecturaSeparada[2])){
                rutsRegistrados.add(lecturaSeparada[2]);
            }
        }
        //ciclo para generar las marcas de cada uno de los ruts
        for (int i=0 ; i< rutsRegistrados.size() ; i++ ){
            int min_10=0;
            int min_25=0;
            int min_45=0;
            int ausencia=0;
            int horasExtras=0;
            for (int j =0; j< cantidadLecturas; j++){
                String marcaActual = arrayLecturas[j];
                //String marcaActual = arrayLecturas.get(j);
                String[] marcaSeparada = marcaActual.split(";");
                if (marcaSeparada[2].equals(rutsRegistrados.get(i))){
                    //obtiene la hor en formato hh:mm
                    String[] horaMarcada=marcaSeparada[1].split(":");
                    int horaLeida = Integer.parseInt(horaMarcada[0]);
                    int minutoLeido = Integer.parseInt(horaMarcada[1]);
                    //verificar ingreso con retraso, se acota hasta las 12 para evitar problemas con
                    //hora de salida
                    if (horaLeida>=7 && horaLeida<12){
                        //retraso de llegada de entre 10 y 25 minutos
                        if (minutoLeido >10 && minutoLeido <= 25){
                            min_10=min_10+1;
                        }
                        if (minutoLeido >25 && minutoLeido <= 45){
                            min_25=min_25+1;
                        }
                        if (minutoLeido >45 && minutoLeido <=59 || (minutoLeido >=0 && minutoLeido<=15 && horaLeida>=9 && horaLeida<=10)){
                            min_45=min_45+1;
                        }
                        if (minutoLeido>15 && horaLeida>=9 && horaLeida<12){
                            ausencia=ausencia+1;
                        }
                    }
                    /*Este segmento de codigo se encuentra deshabilitad debido a no ser un requerimiento
                    el implementar la autorizacion de horas extras (sin autorizacion no se pagan las horas extras)

                    if (horaLeida>18 && horaLeida<24){
                        horasExtras=horasExtras+(horaLeida-18);
                    }
                     */
                }
            }
            Marca_reloj nuevaMarca = new Marca_reloj(rutsRegistrados.get(i), min_10,min_25,min_45,ausencia );
            marcasGeneradas.add(nuevaMarca);

        }
        return marcasGeneradas;
    }

}



