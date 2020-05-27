package org.bois.htmlGenerator

import org.jsoup.nodes.Document

interface IPageCreator {
    val page: Document
    fun htmlPage(): String
}