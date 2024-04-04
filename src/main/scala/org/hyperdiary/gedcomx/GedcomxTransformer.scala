package org.hyperdiary.gedcomx

import org.gedcomx.Gedcomx
import org.gedcomx.fileformat.{ GedcomxFile, GedcomxOutputStream, JaxbXmlSerialization }

import java.io.{ File, FileOutputStream }
import java.util.jar.JarFile
import scala.jdk.CollectionConverters.*
import scala.util.Try

class GedcomxTransformer {

  def jsonToXml(jsonPath: String, xmlPath: String): Try[Unit] =
    for {
      entries <- read(jsonPath)
      result  <- write(entries, xmlPath)
    } yield result

  private def write(gxList: List[Gedcomx], xmlPath: String): Try[Unit] = Try {
    val file = new File(xmlPath)
    val out = new GedcomxOutputStream(new FileOutputStream(file), new JaxbXmlSerialization)
    gxList.foreach(out.addResource)
    out.close()
  }

  private def read(gedxPath: String): Try[List[Gedcomx]] = Try {
    val file = new File(gedxPath)
    val gxFile = new GedcomxFile(new JarFile(file))
    val entries = gxFile.getEntries.asScala
    entries.map { entry =>
      gxFile.readResource(entry).asInstanceOf[Gedcomx]
    }.toList
  }

}
