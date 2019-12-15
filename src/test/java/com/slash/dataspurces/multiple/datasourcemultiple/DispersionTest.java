package com.slash.dataspurces.multiple.datasourcemultiple;

import com.slash.datasources.multiple.DemoApplication;
import com.slash.datasources.multiple.config.HadesDataConfig;
import com.slash.datasources.multiple.dao.hades.DispersionDao;
import com.slash.datasources.multiple.domain.hades.Dispersion;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
//@ContextConfiguration(classes = { HadesDataConfig.class })
public class DispersionTest {
    
    @Autowired
    private DispersionDao dispersionDao;

    @Test
    @Transactional(value = "hadesTransactionManager", readOnly = true)
    public void findByIdTest() {
        final BigInteger idDispersion = BigInteger.valueOf(9382L);
        final String merchant = "0000000000000003";

        Optional<Dispersion> dispersion = dispersionDao.findById(idDispersion);
        assertTrue("No se obtuvo dispersion", dispersion.isPresent());

        assertEquals(merchant, dispersion.get().getMerchant());
    }
}