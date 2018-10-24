package kse.server.cli

import kse.server.Application
import org.apache.commons.text.StringEscapeUtils.escapeJava
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.*

class KseCliTest {

    @Test
    fun `cli helps`() {
        val baos = ByteArrayOutputStream()
        val out = PrintStream(baos)
        val kseCli = KseCli(
            out = out,
            defaultConfigUrl = Application::defaultConfigUrl,
            runServer = { out.println("runServer") }
        )

        kseCli.processArgs(arrayOf("--help"))
        var output = escapeJava(baos.toString("UTF-8"))
        println(output)
        assertTrue("Expected Usage, got: ${output.substring(0, 10).trimIndent()}") {
            output.startsWith("Usage: kse-server")
        }
        baos.reset()

        kseCli.processArgs(arrayOf("run-server", "--help"))
        output = escapeJava(baos.toString("UTF-8"))
        println(output)
        assertTrue("Expected Usage, got: ${output.substring(0, 10).trimIndent()}") {
            output.startsWith("Usage: kse-server run-server")
        }
        baos.reset()
    }
}