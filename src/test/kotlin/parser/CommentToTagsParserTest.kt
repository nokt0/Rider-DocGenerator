package parser

import org.bois.parser.CommentToTagsParser
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader
import java.lang.StringBuffer
import java.nio.file.Paths

internal class CommentToTagsParserTest {
    @Test
    fun example() {
        val reader =
            BufferedReader(FileReader("/Users/napenshe/IdeaProjects/IDEA PROJECT/Rider-DocGenerator/src/test/kotlin/parser/sourceCode.cs"))
        val parser = CommentToTagsParser(reader)
        val str = StringBuffer("<c> Hello World <c>")
        parser.parse(str)
        println(parser.tags)
    }

    @Test
    fun createBlocks() {
        val reader =
            BufferedReader(FileReader("src/test/kotlin/parser/sourceCode.cs"))
            //BufferedReader(FileReader("src/test/kotlin/parser/sourceHierarchy.cs"))
        val parser = CommentToTagsParser(reader)
        val result = parser.createBlocks()
        println(result)
        println()
        parser.treePrint()
    }

}