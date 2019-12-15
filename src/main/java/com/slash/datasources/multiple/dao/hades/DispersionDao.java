package com.slash.datasources.multiple.dao.hades;

import java.math.BigInteger;

import com.slash.datasources.multiple.domain.hades.Dispersion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DispersionDao extends JpaRepository<Dispersion, BigInteger>, JpaSpecificationExecutor<Dispersion> {
    
}