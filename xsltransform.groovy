#!/usr/bin/env groovy
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import java.io.*;

String scripts = new File(getClass().protectionDomain.codeSource.location.path).parent
TransformerFactory factory = TransformerFactory.newInstance();
Source xslt = new StreamSource(new File(scripts + '/src/main/resources/jsf/xslt/style.xsl'));
Transformer transformer = factory.newTransformer(xslt);

new File(scripts + '/src/main/resources/jsf/xml').traverse {
    Source xml = new StreamSource(new File(it.path))
    File out = new File(scripts + '/src/main/webapp/resources/jsf/xml/' + it.name)
    if (!out.exists()) {
        new File(out.parent).mkdirs()
    }
    transformer.transform(xml, new StreamResult(out));
}