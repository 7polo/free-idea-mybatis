package com.polo.fileiconpatcher;

import com.intellij.ide.FileIconPatcher;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.xml.XmlDoctype;
import com.intellij.psi.xml.XmlFile;
import com.polo.common.MyBatisIcon;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author bqy
 * @create 2018-01-15 19:58
 * 文件icon展示
 **/
public class MybaitsFileIconPatcher implements FileIconPatcher {

    private static final String TYPE_OTHER = "";
    private static final String TYPE = "mybatis";
    private static final String TYPE_CONFIG = "configuration";
    private static final String TYPE_MAPPER = "mapper";


    @Override
    public Icon patchIcon(Icon icon, VirtualFile virtualFile, int i, @Nullable Project project) {

        if (project != null && virtualFile != null && !virtualFile.isDirectory()) {
            PsiFile xmlFile = PsiManager.getInstance(project).findFile(virtualFile);
            String type = fileContentType(xmlFile);
            return matchIcon(icon, type);
        }
        return icon;
    }

    private Icon matchIcon(Icon icon, String type) {

        if (TYPE_CONFIG.equals(type)) {
            return MyBatisIcon.mybatisIcon();
        } else if (TYPE_MAPPER.equals(type)) {
            return MyBatisIcon.mapperIcon();
        }
        return icon;
    }

    /**
     * 如果是xml 则判断文件是配置文件还是 mapper
     *
     * @param psiFile
     * @return
     */
    private String fileContentType(PsiFile psiFile) {
        String type = TYPE_OTHER;

        FileType fileType = psiFile.getFileType();

        if ("xml".equalsIgnoreCase(fileType.getDefaultExtension())) {
            if (psiFile instanceof XmlFile) {
                XmlFile xmlFile = (XmlFile) psiFile;
                XmlDoctype doctype = xmlFile.getDocument().getProlog().getDoctype();
                if (doctype != null) {
                    if (doctype.getPublicId().toLowerCase().indexOf(TYPE) != -1) {
                        String tagName = xmlFile.getRootTag().getName().toLowerCase();
                        xmlFile.clearCaches();
                        return tagName;
                    }
                }
            }
        }
        return type;
    }
}
