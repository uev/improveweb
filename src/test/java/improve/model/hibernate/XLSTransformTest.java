package improve.model.hibernate;

import junit.framework.TestCase;
import org.junit.Test;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

/**
 * Created by uev on 02.11.16.
 */
public class XLSTransformTest extends TestCase {

    @Test
    public void testTransform() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(this.getClass().getClassLoader().getResource("xsl/text.xsl").getFile()));
        Transformer transformer = factory.newTransformer(xslt);
        Source text = new StreamSource(new File(this.getClass().getClassLoader().getResource("xsl/text.xml").getFile()));
        transformer.transform(text, new StreamResult(new File("/tmp/output.xml")));

    }
}