package org.bois.fileWorker

import org.bois.FileName
import org.bois.parser.IParser
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*


class FileWorker(override val parser: IParser) : IFileWorker {

    private val docFolderName = "Rider_Documentation"

    override fun generateRecursive(directory: File) {
        val files = directory.listFiles()
        for(f in files){
            if(f.isDirectory){
                generateRecursive(f)
            }
            if(f.isFile && f.toString().endsWith("cs")){
                parser.parseFile(f)
            }
        }
    }

    fun deleteDirectory(directoryToBeDeleted: File): Boolean {
        val allContents = directoryToBeDeleted.listFiles()
        if (allContents != null) {
            for (file in allContents) {
                deleteDirectory(file)
            }
        }
        return directoryToBeDeleted.delete()
    }

    @Throws(IOException::class)
    private fun copyFolder(sourceFolder: File, destinationFolder: File) {
        //Check if sourceFolder is a directory or file
        //If sourceFolder is file; then copy the file directly to new location
        if (sourceFolder.isDirectory) {
            //Verify if destinationFolder is already present; If not then create it
            if (!destinationFolder.exists()) {
                destinationFolder.mkdir()
                println("Directory created :: $destinationFolder")
            }

            //Get all files from source directory
            val files = sourceFolder.list()

            //Iterate over all files and copy them to destinationFolder one by one
            for (file in files) {
                val srcFile = File(sourceFolder, file)
                val destFile = File(destinationFolder, file)

                //Recursive function call
                copyFolder(srcFile, destFile)
            }
        } else {
            //Copy the file content from one place to another
            Files.copy(
                sourceFolder.toPath(),
                destinationFolder.toPath(),
                StandardCopyOption.REPLACE_EXISTING
            )
            println("File copied :: $destinationFolder")
        }
    }

    override fun moveToFolder(
        outPath: File,
        namespaces: HashMap<FileName, String>,
        classes: HashMap<FileName, String>,
        index: String
    ) {
        val path = outPath.toPath()
        var assetsPath = "C:\\Users\\berns\\OneDrive\\Documents\\Code\\Rider-DocGenerator\\src\\main\\resources\\html_templates\\assets"
        if(assetsPath.startsWith("/")){
            assetsPath = assetsPath.substring(1)
        }
        val pathFile = File("$path/$docFolderName")
        createFolder(pathFile)
        if(pathFile.listFiles().size != 0){
            deleteDirectory(pathFile)
        }

        var folder = File("$path/$docFolderName")
        createFolder(folder)
        folder = File("$path/$docFolderName/assets")
        createFolder(folder)
        copyFolder(File(assetsPath),folder)
        //Files.copy(Paths.get(assetsPath), folder.toPath(), StandardCopyOption.REPLACE_EXISTING)
        val writer = BufferedWriter(FileWriter("$path/$docFolderName/index.html"))
        writer.write(index)
        createFiles(namespaces, path, "namespaces")
        createFiles(classes, path, "classes")
        writer.close()
    }

    fun createFolder(folder : File){
        if(!folder.exists()){
            folder.mkdir()
        }
    }

    private fun createFiles(filesMap: HashMap<FileName, String>, path: Path, subfolder: String = "") {
        for ((file, html) in filesMap) {
            val sub = File("$path/$docFolderName/$subfolder")
            if(!sub.exists()){
                sub.mkdir()
            }
            val writer = BufferedWriter(FileWriter("$path/$docFolderName/$subfolder/$file.html"))
            writer.write(html)
            writer.close()
        }
    }
}