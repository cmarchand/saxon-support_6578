package top.marchand.saxon.s6578;

import net.sf.saxon.Configuration;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SchematronCompiler {


  public static final String SCHEMATRON_LOCATION = "io/xspec/xspec/impl/src/schematron";
  public static final String SCHUT_TO_XSLT_XSL = "schut-to-xslt.xsl";

  public XsltExecutable compileFromDirectoryUsingFile() {
    XsltCompiler xsltCompiler = createCompiler();
    File fileToCompile = getFileToCompile();
    try {
      return xsltCompiler.compile(fileToCompile);
    } catch (SaxonApiException e) {
      throw new RuntimeException(e);
    }
  }
  public XsltExecutable compileFromDirectoryUsingSource() {
    XsltCompiler xsltCompiler = createCompiler();
    File fileToCompile = getFileToCompile();
    try {
      Source source = new StreamSource(new FileInputStream(fileToCompile));
      source.setSystemId(fileToCompile.toURI().toString());
      return xsltCompiler.compile(fileToCompile);
    } catch (SaxonApiException | FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  public XsltExecutable compileFromJarUsingSource() {
    XsltCompiler xsltCompiler = createCompiler();
    URI uri = getUriToCompile();
    try {
      Source source = new StreamSource(uri.toURL().openStream());
      source.setSystemId(uri.toString());
      return xsltCompiler.compile(source);
    } catch (IOException | SaxonApiException e) {
      throw new RuntimeException(e);
    }
  }

  private URI getUriToCompile() {
    File targetDirectory = new File("target");
    File packedDirectory = new File(targetDirectory, "dependencies-packed");
    File jarFile = new File(packedDirectory, "xspec.jar");
    String jarUri = jarFile.toURI().toString();
    String resourceUri = "jar:"+jarUri+"!/"+SCHEMATRON_LOCATION+"/"+SCHUT_TO_XSLT_XSL;
    try {
      return new URI(resourceUri);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  private File getFileToCompile() {
    File targetDirectory = new File("target");
    File unpackedDirectory = new File(targetDirectory, "dependencies-unpacked");
    File schematronDirectory = new File(unpackedDirectory, SCHEMATRON_LOCATION);
    File ret = new File(schematronDirectory, SCHUT_TO_XSLT_XSL);
    return ret;
  }

  private XsltCompiler createCompiler() {
    return createProcessor().newXsltCompiler();
  }

  private Processor createProcessor() {
    return new Processor(new Configuration());
  }

}
