package org.bois.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import org.bois.ui.PathDialogWrapper
import org.jetbrains.annotations.NotNull


public class GenerateDocAction : AnAction() {

    override fun update(e: AnActionEvent) {

    }

    override fun actionPerformed(event: AnActionEvent) {
        PathDialogWrapper().showAndGet()
    }
}