package com.lps.common.xml.util;

import com.lps.common.xml.result.SolverResult;
import com.lps.common.xml.task.SolverTask;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JAXBUtil {

    public static final SolverTask toObject(File infile) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(SolverTask.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (SolverTask) unmarshaller.unmarshal(infile);
    }

    public static final void toFile(SolverResult task, String path, String format) throws JAXBException, FileNotFoundException {
        String formatedDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String fileName = String.format(format, formatedDate);
        File outfile = new File(path + "\\" + fileName);
        OutputStream out = new FileOutputStream(outfile);
        JAXBContext jc = JAXBContext.newInstance(SolverResult.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(task, out);
    }

    public static final void toConsole(SolverTask task) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(SolverTask.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(task, System.out);
    }

    public static final void toConsole(SolverResult result) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(SolverResult.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(result, System.out);
    }

    public static final File toFile(SolverResult task, String path) throws JAXBException, FileNotFoundException {
        File outfile = new File(path);
        OutputStream out = new FileOutputStream(outfile);
        JAXBContext jc = JAXBContext.newInstance(SolverResult.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(task, out);
        return outfile;
    }
	
	/*
	public static final File toFile(NetSetup task, String path) throws JAXBException, FileNotFoundException {
		File outfile = new File(path);
		OutputStream out = new FileOutputStream(outfile);
		JAXBContext jc = JAXBContext.newInstance(NetSetup.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(task, out);
		return outfile;
	}
	
	public static final NetSetup toNetSetup(File infile) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(NetSetup.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		return (NetSetup) unmarshaller.unmarshal(infile);
	}
	*/
}
