import org.hyperdiary.gedcomx.GedcomxTransformer
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.{ BeforeAndAfterAll, TryValues }

import java.nio.file.{ Files, Paths }

class GedcomxTransformerSpec extends AnyFreeSpec with Matchers with TryValues with BeforeAndAfterAll {

  private val outputFilePath = Paths.get("WaltonFamilyTreeXml.gedx")

  "Transforming JSON to XML" - {
    "should succeed" in {
      val transformer = new GedcomxTransformer
      val gedcomUrl = getClass.getResource("WaltonFamilyTree.gedx")
      transformer.jsonToXml(gedcomUrl.getPath, outputFilePath.toString).isSuccess mustBe true
    }

    "should fail" in {
      val transformer = new GedcomxTransformer
      val gedcomUrl = getClass.getResource("Unknown.gedx")
      transformer.jsonToXml(gedcomUrl.getPath, "WaltonFamilyTreeXml.gedx").isFailure mustBe true
    }
  }

  override def afterAll(): Unit =
    Files.delete(outputFilePath)

}
