package org.bois.parser

class ParsedClass(val type: HeaderType, val namespace: String, val methods: List<ParsedMethod>,val docComment : Tag ){
    var parents : List <ParsedClass> =  ArrayList <ParsedClass>()
    var children : List <ParsedClass> = ArrayList <ParsedClass>()

    fun createParentsAndChildren(tree: InheritTree){
        tree.map
    }
}