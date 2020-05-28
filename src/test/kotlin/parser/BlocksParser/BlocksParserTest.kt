package parser.BlocksParser

import org.bois.parser.BlocksParser
import com.google.gson.*
import org.bois.htmlGenerator.IndexPageCreator
import org.bois.htmlGenerator.NamespacePageCreator
import org.bois.parser.TreeParser
import java.io.*
import java.nio.charset.Charset
import java.nio.file.Files
import org.junit.jupiter.api.Test

class BlocksParserTest {
    var blocksParser: BlocksParser = BlocksParser()

    @Test
    fun testBlocksParsingFromFragment() {
        val fragment = "namespace test{\n" +
                "/// <summary>\n" +
                "/// Class level summary documentation goes here.\n" +
                "/// </summary>\n" +
                "/// <remarks>\n" +
                "/// Longer comments can be associated with a type or member through\n" +
                "/// the remarks tag.\n" +
                "/// </remarks>\n" +
                "public class TestClass : TestInterface, TestInterface1\n" +
                "{\n" +
                "    /// <summary>\n" +
                "    /// Store for the Name property.\n" +
                "    /// </summary>\n" +
                "    private string _name = null;\n" +
                "}\n" +
                "}\n"
        val splited = fragment.split('\n').filter { it -> it.isNotEmpty() }
        var result = blocksParser.createBlocks(splited)
        var n = System.lineSeparator()
        var expected = arrayListOf<String>(
            "private string _name = null;" + n +
                    "/// <summary>" + n +
                    "/// Store for the Name property." + n +
                    "/// </summary>" + n,
            "public class TestClass : TestInterface, TestInterface1" + n +
                    "/// <summary>" + n +
                    "/// Class level summary documentation goes here." + n +
                    "/// </summary>" + n +
                    "/// <remarks>" + n +
                    "/// Longer comments can be associated with a type or member through" + n +
                    "/// the remarks tag." + n +
                    "/// </remarks>" + n
        )

        for ((key, value) in result) {
            for (item in value.insideBlocks) {
                val testStr = item.toString()
                if (!expected.contains(testStr)) {
                    assert(false)
                }
            }
        }
    }

    @Test
    fun testBlocksParsingFromFile() {
        val gson = Gson()
        val testFilePath = "kotlin\\parser\\BlocksParser\\sourceCode.cs"
        val expected = gson.fromJson<BlocksParserTestData>(
            FileReader("kotlin\\parser\\BlocksParser\\Expected.json"),
            BlocksParserTestData::class.java
        )
        val reader = BufferedReader(FileReader(testFilePath))

        val input = File(testFilePath)
        val list: List<String> =
            Files.readAllLines(input.toPath(), Charset.defaultCharset())
        blocksParser.createBlocks(list)
        val result = blocksParser.blocksByNamespace()
        val treeParser = TreeParser()
        val tree =  treeParser.createTree(list)
        for((key,value) in result ){
            value.forEach { it -> it.createParentsAndChildren(tree) }
        }
        val generator = NamespacePageCreator("namespace1")

        result["test"]?.let { generator.create(it) }

        val writter =
            BufferedWriter(FileWriter("C:\\Users\\berns\\OneDrive\\Рабочий стол\\bootstrap-doc-generator\\namespace.html"))
        writter.write(generator.htmlPage())
        writter.close()
    }
}