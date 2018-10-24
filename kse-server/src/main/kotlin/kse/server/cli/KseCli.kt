package kse.server.cli

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import mu.KotlinLogging
import picocli.CommandLine
import picocli.CommandLine.*
import java.io.File
import java.io.PrintStream
import java.net.URL

class KseCli(
    private val out: PrintStream,
    private val defaultConfigUrl: () -> URL,
    private val runServer: (Config) -> Unit
) {
    private val logger = KotlinLogging.logger {}

    fun processArgs(args: Array<String>) {
        val cli = CommandLine(KseServer()).setUnmatchedArgumentsAllowed(true)
        val commands = cli.parse(*args)  // KseServer is always #0
        if (commands.size < 2) {
            cli.usage(out)
        } else {
            val cmd = commands[1]
            if (cmd.isUsageHelpRequested) {
                cmd.usage(out)
            } else {
                if (cmd.unmatchedArguments.isNotEmpty()) {
                    out.println("Unmatched arguments: ${cmd.unmatchedArguments}")
                }
                val kseCmd = cmd.getCommand<Any>()
                when (kseCmd) {
                    // run commands
                    is PrintDefaultConfig -> {
                        out.println(defaultConfigUrl().readText())
                    }
                    is RunServer -> {
                        val cfg = if (kseCmd.configFile == null) {
                            logger.info { "Using builtin config file" }
                            ConfigFactory.parseURL(defaultConfigUrl())
                        } else {
                            ConfigFactory.parseFile(kseCmd.configFile)
                        }
                        runServer(cfg.resolve())
                    }
                }
            }
        }
    }
}

@Command(
    name = "kse-server",
    subcommands = [PrintDefaultConfig::class, RunServer::class]
)
class KseServer {
    @Mixin var helpOption = HelpOption()
}

class HelpOption {
    @Option(names = ["-h", "--help"], usageHelp = true, description = ["Show usage"])
    var usageHelpRequested: Boolean = false
}

// ---- Commands

@Command(
    name = "print-default-config",
    description = ["Prints the default (built in) config file contents"]
)
class PrintDefaultConfig {
    @Mixin var helpOption = HelpOption()
}

@Command(
    name = "run-server",
    description = ["Runs the server"]
)
class RunServer {
    @Mixin var helpOption = HelpOption()

    @Option(
        names = ["-c", "--config"],
        description = ["Path to config file", "If not specified a default config will be used"]
    )
    var configFile: File? = null
}
