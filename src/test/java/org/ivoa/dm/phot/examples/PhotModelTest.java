/*
 * Created on 6 Oct 2022 
 * Copyright 2022 Paul Harrison (paul.harrison@manchester.ac.uk)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License in file LICENSE
 */ 

package org.ivoa.dm.phot.examples;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.ivoa.dm.ivoa.Unit;
import org.ivoa.dm.phot.Access;
import org.ivoa.dm.phot.Bandwidth;
import org.ivoa.dm.phot.Flux;
import org.ivoa.dm.phot.MagnitudeSystem;
import org.ivoa.dm.phot.PhotCal;
import org.ivoa.dm.phot.PhotModel;
import org.ivoa.dm.phot.PhotometricSystem;
import org.ivoa.dm.phot.PhotometryFilter;
import org.ivoa.dm.phot.SpectralLocation;
import org.ivoa.dm.phot.TransmissionCurve;
import org.ivoa.dm.phot.TypeOfMagSystem;
import org.ivoa.dm.phot.UCD;
import org.ivoa.dm.phot.ZeroPoint;
import org.ivoa.vodml.nav.ModelInstanceTraverser;
import org.javastro.ivoa.tests.AbstractJAXBJPATest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing PhotDM .
 * recreating https://github.com/ivoa/modelinstanceinvot-code/blob/package/photdm_builder/photdm.WISE.WISE.W1.xml
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 
 * @since 6 Oct 2022
 */
class PhotModelTest extends AbstractJAXBJPATest {

    

    private static PhotCal photcal;
    private static PhotometricSystem system;

    /**
     * 
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        
        PhotometryFilter filter = PhotometryFilter.createPhotometryFilter(f ->{
            f.bandname = "";
            f.name = "WISE W1 filter";
            f.description =  "WISE W1 filter";
            f.identifier = "WISE/WISE.W1";
            f.fpsidentifier = "ivo://svo/fps";
            f.bandwidth = Bandwidth.createBandwidth(b->{ 
                b.ucd = new UCD("instr.bandwidth;stat.fwhm");
                b.unitexpression  = new Unit("Angstrom");
                b.extent = 6626.4186524258;
                b.start = 27540.970745309;
                b.stop = 38723.877229677;
                
            });
            f.spectralLocation = new SpectralLocation(new UCD("em.wl.effective"), new Unit("Angstrom") , 33526.0);
            f.transmissionCurve = TransmissionCurve.createTransmissionCurve(c->{
                c.access = Access.createAccess(a->{
                    a.reference = "http://svo2.cab.inta-csic.es/theory/fps/fps.php?PhotCalID=WISE/WISE.W1/Vega";
                    a.size = -1;
                    a.format = "VOTable";
                });
            });
        });
        MagnitudeSystem bb = new MagnitudeSystem(TypeOfMagSystem.VEGAMAG, "http://svo2.cab.inta-csic.es/theory/fps/morefiles/vega.dat");
        photcal = new PhotCal("WISE/WISE.W1/Vega", ZeroPoint.createZeroPoint(z->{
            z.flux = Flux.createFlux(l->{
                l.value = 309.54;
                l.ucd = new UCD("phot.flux;meta.modelled");
                l.unitexpression = new Unit("Jy");
            });
        }), bb, filter);

        
        
        List<PhotometryFilter> filters = new ArrayList<>();
        filters.add(filter);
        system = new PhotometricSystem("WISE", 0, filters );
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() throws PropertyException, TransformerConfigurationException, ParserConfigurationException, JAXBException, TransformerFactoryConfigurationError, TransformerException {
        PhotModel model = new PhotModel();
        model.addContent(photcal);
        model.addContent(system);
        PhotModel modelin = roundtripXML(PhotModel.contextFactory(), model, PhotModel.class);
        assertNotNull(modelin);
    }

}


