package com.slash.datasources.multiple.domain.heimdal;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_operacion", catalog = "fnza_heimdal")
public class Operacion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idOperacion")
    private BigInteger id;

    @Column(name = "merchant")
    private String merchant;

    @Column(name = "importe")
    private Double importe;
}