import com.comercio.sucursal.model.Sucursal;
import com.comercio.sucursal.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public void run(String... args) throws Exception{

        if (sucursalRepository.count() == 0){
            Sucursal s1 = new Sucursal();
            s1.setNombre("Sucursal Viña del Mar");
            s1.setDireccion("Álvarez 123");
            s1.setTelefono(322114455);

            Sucursal s2 = new Sucursal();
            s2.setNombre("Sucursal Valparaíso");
            s2.setDireccion("Av. Brasil 456");
            s2.setTelefono(322667788);

            sucursalRepository.save(s1);
            sucursalRepository.save(s2);

            System.out.println("Los datos de prueba han sido cargados exitosamente.");
        } else {
            System.out.println("La tabla ya contiene estos datos, no se cargaron duplicados.");
        }
    }

}