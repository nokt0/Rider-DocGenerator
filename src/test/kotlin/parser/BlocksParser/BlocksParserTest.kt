package parser.BlocksParser

import org.bois.parser.BlocksParser
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader
import com.google.gson.*
import org.junit.Before
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files

class BlocksParserTest {
    var blocksParser: BlocksParser = BlocksParser()

    @Test
    fun TestBlocksParsingFromFragment() {
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

        for((key,value) in result){
            for(item in value.insideBlocks){
                var testStr = item.toString()
                if(!expected.contains(testStr)){
                    assert(false);
                }
            }
        }
    }

    @Test
    fun TestBlocksParsingFromFile() {
        val gson = Gson()
        val testFilePath = "src/test/kotlin/parser/BlocksParser/sourceCode.cs"
        val expected = gson.fromJson<BlocksParserTestData>(
            FileReader("src/test/kotlin/parser/BlocksParser/Expected.json"),
            BlocksParserTestData::class.java
        )
        val reader = BufferedReader(FileReader(testFilePath))

        val input = File(testFilePath)
        val list: List<String> =
            Files.readAllLines(input.toPath(), Charset.defaultCharset())
        val result = blocksParser.createBlocks(list);
        println(result)
    }
}