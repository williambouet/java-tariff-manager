package dev.wcs.nad.tariffmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.wcs.nad.tariffmanager.persistence.entity.Department;
import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.persistence.repository.DepartmentRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.TariffRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TariffDepartmentTest {
    
    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void tariffDepartmentTest() {
        Tariff tariff = new Tariff();
        tariff.setId(1L);
        tariff.setName("tariff");
        tariffRepository.save(tariff);

        Department department = new Department();
        department.setId(1L);
        department.setName("dpt");
        departmentRepository.save(department);
        
        tariff.setDepartment(department);
        assertThat(tariff.getDepartment()).isEqualTo(department);
    }


}
