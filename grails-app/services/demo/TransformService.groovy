package demo

import grails.gorm.transactions.Transactional

import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

@Transactional
class TransformService {

    String birds() {
        InputStream xsl = System.getResourceAsStream('/birds.xsl')
        InputStream xml = System.getResourceAsStream('/birds.xml')

        TransformerFactory transformerFactory = TransformerFactory.newInstance()
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsl))

        OutputStream baos = new ByteArrayOutputStream()
        transformer.transform(new StreamSource(xml), new StreamResult(baos))

        baos.toString()
    }
}
