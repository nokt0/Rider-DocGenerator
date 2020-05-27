import org.junit.jupiter.api.Test

class `Timmy's check` {
    @Test
    fun check() {
        var a: ArrayList<String> = ArrayList()
        a.add("Timmy")
        a.add("Turner")

        a.zipWithNext { a, b -> a.plus(b) }

        println(a)

        var b: ArrayList<Int> = ArrayList()
        b.add(1)
        b.add(2)

        var d : List<Int> = b.zipWithNext { a, b -> a + b }
        println(d)
    }
}