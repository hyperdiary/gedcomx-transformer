package org.hyperdiary.gedcomx

import cats.implicits.*
import com.monovore.decline.{ CommandApp, Opts }

import scala.util.{ Failure, Success }

object GedcomxTransformerApp extends CommandApp(
      name = "GEDCOM Transformer",
      header = "Transforms a GEDCOM X JSON file to XML",
      main = {
        val inputPath =
          Opts.option[String]("input", short = "i", help = "Input JSON file path")

        val outputPath = Opts.option[String]("output", short = "o", help = "Output XML file path")

        (inputPath, outputPath).mapN { (inPath, outPath) =>
          val transformer = new GedcomxTransformer()
          transformer.jsonToXml(inPath, outPath) match {
            case Success(_)     => println("Transformation completed successfully!")
            case Failure(error) => error.printStackTrace()
          }
        }
      }
    )
