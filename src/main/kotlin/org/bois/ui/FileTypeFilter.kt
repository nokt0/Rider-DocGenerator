package org.bois.ui

import java.io.File
import javax.swing.filechooser.FileFilter

class FileTypeFilter(private val extension: String, private val description: String) :
    FileFilter() {
    override fun accept(file: File): Boolean {
        return if (file.isDirectory) {
            true
        } else file.name.toLowerCase().endsWith(extension)
    }

    override fun getDescription(): String {
        return description + String.format(" (*%s)", extension)
    }

}