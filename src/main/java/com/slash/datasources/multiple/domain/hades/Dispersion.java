package com.slash.datasources.multiple.domain.hades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "vw_dispersion_all", catalog = "hades_merchant")
public class Dispersion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "tipoTx")
    private String tipoTx;

    @Column(name = "merchant")
    private String merchant;

    @Column(name = "importe")
    private Double importe;

    @Column(name = "fechaAplicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicacion;
}