package com.slash.datasources.multiple.dao.heimdal;

import java.math.BigInteger;

import com.slash.datasources.multiple.domain.heimdal.Operacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperacionDao extends JpaRepository<Operacion, BigInteger>, JpaSpecificationExecutor<Operacion> {
    
}