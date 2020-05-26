package org.bois.parser

class BlocksParser {
    var namespace: String? = null
    var bracketsCount = 0
    var bracketsClosed = true
    var blocksBuffer: HashMap<String, ParsedClass> = HashMap()
    fun createBlocks(input: List<String>): HashMap<String, ParsedClass> {
        var startedDocComment = false
        val commentBlocks = HashMap<String, ParsedClass>()
        var block = ArrayList<String>()
        var headerType: HeaderType? = null
        var recentHeaderName: String? = null

        loop@ for (line in input) {
            val parents = ArrayList<String>()
            var headerString: String? = null

            if (line != null) {
                calculateBrackets(line)
                val isNamespace = Regex("""\s*namespace\s+.*$""").matches(line)

                // Если в строке содержится namespace записываем его название
                // скобки после namespace не должны учитываться в подсчете открывающих и закрывающих скобок
                if (isNamespace) {
                    changeBracketCount('-')
                    this.namespace =
                        line.substring(0, line.length).replace("{", "").replace(Regex("""\s*namespace\s+"""), "")
                }

                // Поиск Комментариев
                when {
                    Regex("""\s*///\s*.*$""").matches(line) || Regex("""\s*/\*\*\s*.*$""").matches(line) -> {
                        if (!startedDocComment) {
                            startedDocComment = true
                        }
                        block.add(line.trim() + System.lineSeparator())
                    }

                    //Если комментарий многострочный
                    Regex("""\s*\*\s*.*$""").matches(line) -> {
                        block.add(line.trim() + System.lineSeparator())
                    }

                    //Если обычный комментарий
                    Regex("""\s*//\s*.*$""").matches(line) -> {
                        continue@loop
                    }

                    else -> {
                        // Поиск объявления класса,интерфейса,...
                        if (bracketsClosed) {
                            for (header in HeaderType.values()) {
                                val headerIndex = Regex("""\s*$header\s*""").containsMatchIn(line)
                                if (headerIndex) {
                                    headerString = line
                                    headerType = header
                                    recentHeaderName = cutNameFromHeader(headerString, header.toString())
                                    break
                                }
                            }

                            if (startedDocComment) {
                                if (headerString == null) {
                                    headerString = line.trim()
                                }
                                val parsedBlock =
                                    ParsedBlockData(block, headerString, recentHeaderName, this.namespace, headerType)

                                val mapElement = commentBlocks[recentHeaderName]
                                if (mapElement == null) {
                                    if (recentHeaderName != null) {
                                        val initList = ArrayList<ParsedBlockData>()
                                        val parsedClass = ParsedClass(recentHeaderName, headerType, namespace, initList)
                                        parsedClass.docs = parsedBlock.docs
                                        commentBlocks[recentHeaderName] = parsedClass
                                    }
                                } else {
                                    mapElement.insideBlocks.add(parsedBlock)
                                }
                                block = ArrayList()
                                startedDocComment = false
                            }
                            println(Trimmer.trimLine(block))
                            block = ArrayList()
                            startedDocComment = false
                        }
                    }
                }
            }
            continue
        }
        blocksBuffer = commentBlocks
        return commentBlocks
    }

    fun blocksByNamespace(): HashMap<String, ArrayList<ParsedClass>> {

        val sortedMap = HashMap<String, ArrayList<ParsedClass>>()
        var lastNamespace: String? = null
        for ((key, value) in blocksBuffer) {
            if (value.namespace != null) {
                if (sortedMap[value.namespace] == null) {
                    sortedMap[value.namespace] = ArrayList<ParsedClass>()
                }
                sortedMap[value.namespace]?.add(value)
            }
        }
        return sortedMap
    }

    fun calculateBrackets(line: String) {
        var openBracketPos = line.indexOf("{")
        var closeBracketPos = line.lastIndexOf("}")
        // Подсчет всех скобок
        while (openBracketPos >= 0 && closeBracketPos >= 0) {
            openBracketPos = line.indexOf("{", openBracketPos + 1)
            closeBracketPos = line.indexOf("}", closeBracketPos + 1)
            when {
                closeBracketPos != -1 -> {
                    changeBracketCount('-')
                }
                openBracketPos != -1 -> {
                    changeBracketCount('+')
                }
            }
        }
    }


    /** Функция считает скобки и определяет закрылась ли их последовательность
     * Нам нужно это для того, чтобы точно знать что найденные строки "class,interface..."
     * точно являются объявлением, а не просто словом в какой то строке.
     */
    private fun changeBracketCount(operation: Char) {
        when (operation) {
            '+' -> bracketsCount++
            '-' -> bracketsCount--
        }
        if (bracketsCount == 0) {
            bracketsClosed = true
        }
    }

    private fun cutNameFromHeader(header: String, headerType: String): String {
        val nameExp = Regex("""${headerType}\s*.*[:{$]?""")
        val name = nameExp.find(header)?.value ?: ""
        val splittedName = name.split("[\\s:{]".toRegex()).filter { str -> str.isNotEmpty() }
        val resultName: String
        resultName = when (splittedName.size) {
            0 -> ""
            else -> splittedName[1]
        }
        return resultName
    }

}