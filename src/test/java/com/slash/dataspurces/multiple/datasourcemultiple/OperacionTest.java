package com.slash.dataspurces.multiple.datasourcemultiple;

import java.math.BigInteger;
import java.util.Optional;

import com.slash.datasources.multiple.DemoApplication;
import com.slash.datasources.multiple.dao.heimdal.OperacionDao;
import com.slash.datasources.multiple.domain.heimdal.Operacion;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { DemoApplication.class })
public class OperacionTest {
    
    @Autowired
    private OperacionDao operacionDao;

    @Test
    @Transactional(value = "heimdalTransactionManager", readOnly = true)
    public void findByIdTest() {
        final BigInteger idOperacion = BigInteger.valueOf(1063L);
        final String merchant = "0000000000002101";

        Optional<Operacion> operacion = operacionDao.findById(idOperacion);
        assertTrue("No se obtuvo operacion", operacion.isPresent());

        assertEquals(merchant, operacion.get().getMerchant());
    }

    @Test
    @Transactional(value = "heimdalTransactionManager", readOnly = true)
    public void findAllTest() {
        PageRequest request = PageRequest.of(0, 10, Sort.by(new String[]{ "id" }));

        Page<Operacion> pagina = operacionDao.findAll(request);
        pagina.getContent().stream().forEach(item -> System.out.println(item));
    }
    
}