import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

public class ImageComparison {

    public static void main(String[] args) {
        try {
            // Load the original image
            BufferedImage originalImage = ImageIO.read(new File("input.jpeg"));

            // Load the two images to be compared
            BufferedImage image1 = ImageIO.read(new File("SA_decryptedImage.jpeg"));
            BufferedImage image2 = ImageIO.read(new File("SA_ICM_decryptedImage.jpeg"));

            // Create a table model
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("X");
            model.addColumn("Y");
            model.addColumn("Original Pixel");
            model.addColumn("SA Decrypted");
            model.addColumn("SA ICM Decrypted");

            // Iterate over the pixels in the images
            for (int y = 0; y < originalImage.getHeight(); y++) {
                for (int x = 0; x < originalImage.getWidth(); x++) {
                    // Get the pixel values of the original image
                    int originalPixel = originalImage.getRGB(x, y);

                    // Get the pixel values of the two images
                    int pixel1 = image1.getRGB(x, y);
                    int pixel2 = image2.getRGB(x, y);

                    // Add the pixel values to the table model
                    model.addRow(new Object[]{x, y, originalPixel, pixel1, pixel2});
                }
            }

            // Create a table from the table model
            javax.swing.JTable table = new javax.swing.JTable(model);

            // Display the table
            javax.swing.JFrame frame = new javax.swing.JFrame("Image Comparison");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.add(new javax.swing.JScrollPane(table));
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}