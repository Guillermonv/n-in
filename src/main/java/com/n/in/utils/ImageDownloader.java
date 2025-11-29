package com.n.in.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ImageDownloader {

   /*
    convert imagen url like this into a image and save on folder tmp
    https://images.unsplash.com/photo-1703783419681-e3247591ba18?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w4MzU5MTh8MHwxfHNlYXJjaHwxfHx1bmEtYmFuZGVyYS1henVsLWNvbi11bmEtcGFsb21hLXktbGEtcGF6LWVuLWVsbGF8ZXN8MHx8fHwxNzY0MjU2ODcyfDA&ixlib=rb-4.1.0&q=80&w=1080
    */
    public static String downloadImageToTmp(String imageUrl, String fileNameWithoutExt) {

        try {
            // Carpeta tmp en el root del proyecto
            File tmpDir = new File("tmp");
            if (!tmpDir.exists()) {
                tmpDir.mkdirs();
            }

            // Leer imagen desde URL
            BufferedImage image = ImageIO.read(new URL(imageUrl));
            if (image == null) {
                throw new RuntimeException("Cannot decode image: " + imageUrl);
            }

            // Crear archivo final "downloaded_image.jpg" sin duplicar extensión
            File output = new File(tmpDir, fileNameWithoutExt + ".jpg");

            // Guardar imagen como JPEG
            ImageIO.write(image, "jpg", output);

            return output.getAbsolutePath();

        } catch (Exception e) {
            throw new RuntimeException("Error downloading image: " + e.getMessage(), e);
        }
    }
}
