import java.awt.Color;

public class PhotoMagic {
    public static Picture transform(Picture pic, LFSR lfsr) {
        Picture out = new Picture(pic.width(), pic.height());
        for (int col = 0; col < pic.width(); col++) {
            for (int row = 0; row < pic.height(); row++) {
                Color old = pic.get(col, row);
                Color n = new Color(
                        old.getRed() ^ lfsr.generate(8),
                        old.getGreen() ^ lfsr.generate(8),
                        old.getBlue() ^ lfsr.generate(8));
                out.set(col, row, n);
            }
        }
        return out;
    }

    public static void main(String[] args) {
        LFSR l = new LFSR("01101000010100010000", 16);

        Picture pipe = new Picture("data/pipe.png");
        pipe.show(); // shows pipe successfully

        Picture encrypted = transform(pipe, l);
        encrypted.show(); // encrypts pipe successfully

        l = new LFSR("01101000010100010000", 16);
        transform(encrypted, l).show(); // decrypts pipe successfully
    }
}
