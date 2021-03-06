package org.bois.ui

import java.awt.FlowLayout
import java.awt.event.ActionEvent
import javax.swing.*


class FilePicker(private val textFieldLabel: String, private val buttonLabel: String,private val jfileMode: Int) : JPanel() {
    private val label: JLabel
    private val textField: JTextField
    private val button: JButton
    val fileChooser: JFileChooser = JFileChooser()
    private var mode = 0

    private fun buttonActionPerformed(evt: ActionEvent) {
        if (mode == MODE_OPEN) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.text = fileChooser.selectedFile.absolutePath
            }
        } else if (mode == MODE_SAVE) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.text = fileChooser.selectedFile.absolutePath
            }
        }
    }

    fun addFileTypeFilter(extension: String, description: String) {
        val filter = FileTypeFilter(extension, description)
        fileChooser.addChoosableFileFilter(filter)
    }

    fun setMode(mode: Int) {
        this.mode = mode
    }

    val selectedFilePath: String
        get() = textField.text

    val MODE_OPEN = 1
    val MODE_SAVE = 2


    init {
        fileChooser.fileSelectionMode = jfileMode
        layout = FlowLayout(FlowLayout.CENTER, 5, 5)

        // creates the GUI
        label = JLabel(textFieldLabel)
        textField = JTextField(30)
        button = JButton(buttonLabel)
        button.addActionListener { evt -> buttonActionPerformed(evt) }
        add(label)
        add(textField)
        add(button)
    }
}