package org.bois.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.uiDesigner.core.AbstractLayout
import com.intellij.util.ui.GridBag
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import org.jetbrains.annotations.SystemIndependent
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.io.File
import javax.swing.JComponent
import javax.swing.JFileChooser
import javax.swing.JPanel

class PathDialogWrapper(val currentPath: String?) : DialogWrapper(true) {

    val panel = JPanel(GridBagLayout())
    val sourceCodePath = FilePicker("CS Source Code Path", "Browse", JFileChooser.DIRECTORIES_ONLY)
    val outDocumentationPath = FilePicker("Documentation out Folder", "Browse", JFileChooser.DIRECTORIES_ONLY)

    init {
        init()
        title = "Choose a path"
    }

    override fun createCenterPanel(): JComponent? {


        sourceCodePath.setMode(sourceCodePath.MODE_OPEN)
        outDocumentationPath.setMode(sourceCodePath.MODE_OPEN)
        if(currentPath != null){
            sourceCodePath.fileChooser.currentDirectory = File(currentPath)
            outDocumentationPath.fileChooser.currentDirectory = File(currentPath)
        }

        val gb = GridBag()
            .setDefaultInsets(Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
            .setDefaultWeightX(1.0)
            .setDefaultFill(GridBagConstraints.HORIZONTAL)

        panel.preferredSize = Dimension(600, 150)

        panel.add(label(""), gb.nextLine().next().weightx(0.2))
        panel.add(sourceCodePath, gb.nextLine().next().weightx(0.8))
        panel.add(label(""), gb.nextLine().next().weightx(0.2))
        panel.add(outDocumentationPath, gb.nextLine().next().weightx(0.8))

        return panel
    }

    private fun label(text: String): JComponent {
        val label = JBLabel(text)
        label.componentStyle = UIUtil.ComponentStyle.SMALL
        label.fontColor = UIUtil.FontColor.BRIGHTER
        label.border = JBUI.Borders.empty(0, 5, 2, 0)

        return label
    }

    override fun doOKAction() {
        sourceCodePath.selectedFilePath
        outDocumentationPath.selectedFilePath
    }

}
