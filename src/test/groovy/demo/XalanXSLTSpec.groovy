package demo

import spock.lang.Specification

import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

class XalanXSLTSpec extends Specification {

    void "test XSLT"() {
        given:
        InputStream xsl = System.getResourceAsStream('/birds.xsl')
        InputStream xml = System.getResourceAsStream('/birds.xml')

        and:
        TransformerFactory transformerFactory = TransformerFactory.newInstance()
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsl))

        when:
        def baos = new ByteArrayOutputStream()
        transformer.transform(new StreamSource(xml), new StreamResult(baos))
        def result = baos.toString()

        then:
        result.contains '<?xml version="1.0" encoding="UTF-8"?><BirdInfo>\n' +
                'Order is:  TINAMIFORMES\n' +
                '\tFamily is:  TINAMIDAE\n' +
                '              Great Tinamou. Tinamus major'
    }
}
