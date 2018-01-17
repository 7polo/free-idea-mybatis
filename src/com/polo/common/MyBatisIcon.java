package com.polo.common;

import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.ImageUtil;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author bqy
 * @create 2018-01-17 18:48
 **/
public class MyBatisIcon {

    private static double imgScale = 0.55;
    private static int xDev = 3;
    private static int yDev = 2;

    private static Icon MYBATIS = null;
    private static Icon MYBATIS_MAPPER = null;

    private static Icon getXmlIcon() {
        return FileTypeManager.getInstance().findFileTypeByName("XML").getIcon();
    }

    /**
     * 根据路径获取Image
     *
     * @param path
     * @return
     */
    private static Image getImage(String path) {
        return new ImageIcon(FileFileMapper.pathUrl(path)).getImage();
    }

    /**
     * 获取一个合适的缩放比
     *
     * @param img
     * @param s
     * @param width
     * @param height
     * @return
     */
    private static double computeScale(Image img, double s, int width, int height) {
        double xSacle = (width * s) / img.getWidth(null);
        double ySacle = (height * s) / img.getHeight(null);
        return xSacle > ySacle ? xSacle : ySacle;
    }

 /*   public static Icon mybatisLogo(Icon icon) {
        if (MYBATIS == null) {
            MYBATIS = IconUtils.puzzleIcon(icon, FileFileMapper.MYBATIS, 0.55);
        }
        return MYBATIS;
    }

    public static Icon mybatisMapper(Icon icon) {
        if (MYBATIS_MAPPER == null) {
            MYBATIS_MAPPER = IconUtils.puzzleIcon(icon, FileFileMapper.MYBATIS_MAPPER, 0.55);
        }
        return MYBATIS_MAPPER;
    }*/

    /**
     * 创建带logo的图标组合
     *
     * @param imgLogo
     * @return
     */
    public static Icon createIcon(Image imgLogo, double s) {
        Icon baseIcon = getXmlIcon();
        int width = baseIcon.getIconWidth() + xDev;
        int height = baseIcon.getIconHeight() + yDev;
        double scale = computeScale(imgLogo, s, width, height);
        //缩放
        imgLogo = ImageUtil.scaleImage(imgLogo, scale);
        BufferedImage resImg = UIUtil.createImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics graphics = resImg.getGraphics();
        //绘制底图
        graphics.drawImage(IconLoader.toImage(baseIcon), 0, 0, null);
        int x = width - imgLogo.getWidth(null);
        int y = height - imgLogo.getHeight(null);
        //绘制logo
        graphics.drawImage(imgLogo, x, y, null);
        graphics.dispose();
        return new ImageIcon(resImg);
    }

    public static Icon createIcon(String path, double s) {
        return createIcon(getImage(path), s);
    }

    public static Icon mybatisIcon() {

        if (MYBATIS == null) {
            MYBATIS = createIcon(FileFileMapper.MYBATIS, 0.55);
        }
        return MYBATIS;
    }

    public static Icon mapperIcon() {

        if (MYBATIS_MAPPER == null) {
            MYBATIS_MAPPER = createIcon(FileFileMapper.MYBATIS_MAPPER, 0.55);
        }
        return MYBATIS_MAPPER;
    }

}
