import org.bois.CommentToTagsParser
import org.junit.Test
import java.lang.StringBuffer

internal class JUnit5ExampleTest {
    @Test
    fun example() {
        val cttp = CommentToTagsParser();
        var str  = StringBuffer("<c> Hello World <c>")
        cttp.parse(str)
        println(cttp.tags)


    }

}